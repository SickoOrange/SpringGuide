## 简单的性能监控日志追踪模块

首先我们要实现一个简单的性能监控的日志追踪记录器

目的就是用来性能监控工作的时候对数据采集，数据分析跟数据展示的的每一个步骤都进行监控，以便来确保每一步都能够运行正确！ 

```java
/**
 * 性能监控
 */
public interface PerformanceMonitor {

    /**
     * 数据采集
     */
    public List<Integer> dataCollect(List<String> rawData);

    /**
     * 数据分析
     */
    public int dataAnalyze(List<Integer> data);

    /**
     * 数据展示
     */
    public String dataReport(int result);

}
```

现在的目的就是要给我们的ESightPerformanceMonitor中的每一个方法的每一个步骤都加一个简单日志以便追踪方法的每一步的运行日志！

```java
public class ESightPerformanceMonitor implements PerformanceMonitor {


    private List<String> rawData = ImmutableList.of("1", "2", "3", "4", "5");

    public List<Integer> dataCollect(List<String> rawData) {
        System.out.println("开始数据采集,元数据 -->" + rawData.toString());
        List<Integer> data = rawData.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("数据采集完成，并完成转换 -->" + data.toString());
        return data;
    }

    public int dataAnalyze(List<Integer> data) {
        System.out.println("开始数据分析，-->" + data.toString());
        Optional<Integer> sum = data.stream().reduce((i1, i2) -> i1 + i2);
        assert sum.isPresent();
        System.out.println("数据分析结束 结果--->" + sum.get());
        return sum.get();
    }

    public String dataReport(int result) {
        System.out.println("开始数据展示，reuslt int=" + result);
        String str = "" + result;
        System.out.println("结束数据展示，result string=" + str);
        return str;
    }
}

```



如果采用硬编码的方式将所有的日志记录都写在方法里面，这样日志记录的逻辑将跟我们的业务核心逻辑造成严重的耦合！如果你要修改记录的话，需要针对每一处的代码中进行修改！工作量是很大的，特别是对于一个大型的系统来说，这种带来的浪费时间的工作量也会严重的影响开发效率！

而我所希望的就是业务的核心逻辑跟辅助的日志功能不能耦合，也就是说有没有一种办法可以是我们在方法中之保留业务的核心逻辑，不引入任何的辅助代码，比如日志代码！更简单的来说

就是我想删除掉所有的System.out.println方法而只保留核心的业务代码！

答案当然是肯定的，这就是Spring AOP带来的强大的功能，不在业务类引入冗余的业务代码！但是确有能够实现在业务代码运行期间动态的加入日志记录的模块用来实现跟踪业务 方法内部运行的日志记录的功能！

Spring的AOP的实现内部实现其实就是动态代理！

所以我们可以先使用动态代理来实现上述的功能！然后在引入Spring AOP来实现！



### JDK 动态代理实现日志追踪模块

对于Java的动态代理这里不再赘述，但是这一块内容需要你能够了解Java的动态代理的最基本用法！  

现在我们删除所有的日志代码：

```java
public class ESightPerformanceMonitor implements PerformanceMonitor {
public List<Integer> dataCollect(List<String> rawData) {
        return rawData.stream().map(Integer::valueOf).collect(Collectors.toList());
    }

    public int dataAnalyze(List<Integer> data) {
        Optional<Integer> sum = data.stream().reduce((i1, i2) -> i1 + i2);
        assert sum.isPresent();
        return sum.get();
    }

    public String dataReport(int result) {
        return "" + result;
    }
}
```

我们再来创建EsightPerformanceMonitor的代理对象：

```java
public class EsightPerformanceMonitorProxy {


    /**
     * 为被代理对象创建其动态代理proxy对象
     *
     * @param performanceMonitor 被代理对象
     */
    public static PerformanceMonitor getProxyHandler(final PerformanceMonitor performanceMonitor) {

        // 被代理对象的 类加载器
        ClassLoader classLoader = performanceMonitor.getClass().getClassLoader();
        // 被代理对象 所实现的所有接口clazz对象
        Class<?>[] interfaces = performanceMonitor.getClass().getInterfaces();
        // 方法执行器， 执行被代理对象里面的具体的某个方法
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              // 注意： method.invoke(target, args);//invoke方法中的对象要从外部引入
              // 因为如果传进来使用的话调用invoke方法会陷入死循环。
              
                Object invoke = null;
                try {
                    System.out.println("动态代理：开始执行方法:" + method.getName() + " 参数：" + Arrays.asList(args));
                    invoke = method.invoke(performanceMonitor, args);
                    System.out.println("动态代理：方法正常执行结束:" + method.getName() + " 结果：" + invoke);
                } catch (Exception e) {
                     System.out.println("动态代理：方法执行出现异常:" + method.getName() + " Exception cause：" + e.getCause());
                }finally {
                    System.out.println("动态代理：方法最终执行结束"+method.getName());
                }
              // 利用反射执行方法 并方法的执行结果
                return invoke;
            }
        };


        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        // 返回代理对象
        return (PerformanceMonitor) proxy;
    }
}
```

下面进行测试看看我们的代理对象是否能正确的工作：

```java
    @Test
    public void test() {

        List<String> rawData = ImmutableList.of("1", "2", "3", "4", "5");

        PerformanceMonitor eSightPerformanceMonitor = new EsightPerformanceMonitor();

        // 获取代理对象
        PerformanceMonitor proxyHandler = EsightPerformanceMonitorProxy.getProxyHandler(eSightPerformanceMonitor);

          // 执行
        List<Integer> collectResult = proxyHandler.dataCollect(rawData);
        System.out.println("=====================");
        int analyzeResult = proxyHandler.dataAnalyze(collectResult);
        System.out.println("=====================");
        String report = proxyHandler.dataReport(analyzeResult);
        System.out.println("=====================");
    }

 // 测试结果输出如下：
动态代理：开始执行方法:dataCollect 参数：[[10, 20, 30, 40, 50]]
动态代理：方法正常执行结束:dataCollect 结果：[1, 2, 3, 4, 5]
动态代理：方法最终执行结束dataCollect
=====================
动态代理：开始执行方法:dataAnalyze 参数：[[1, 2, 3, 4, 5]]
动态代理：方法正常执行结束:dataAnalyze 结果：15
动态代理：方法最终执行结束dataAnalyze
=====================
动态代理：开始执行方法:dataReport 参数：[15]
动态代理：方法正常执行结束:dataReport 结果：report:15
动态代理：方法最终执行结束dataReport
=====================
```



OK，至此一切都运行正常，有了动态代理，我可以绝对的保证我的业务代码层面是干净的，不会引入一句冗余的代码，所有的日志记录代码被移除到了动态代理类中，并且在动态代理类中对方法执行的任意一点时间点都进行了日志追踪，即使在调用方法期间出现了异常 也能够正确的捕获异常并且进行日志记录！



动态代理的缺点：

1. 写法比较复杂
2. **要实现动态代理的被代理类必须要实现接口，否则无法实现动态代理**

### 