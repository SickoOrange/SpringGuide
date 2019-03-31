xml配置

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <context:component-scan base-package="com.huawei.yinya.AOP"/>
    <aop:aspectj-autoproxy/>
</beans
```



```java
@Aspect
@Component
public class AspectProtoTypeClass {

    @Before("execution(* com.huawei.yinya.AOP.EsightPerformanceMonitor.*(..))")
    public static void methodStart() {
        // System.out.println("动态代理：开始执行方法:" + method.getName() + " 参数：" + Arrays.asList(args));
        System.out.println("动态代理：开始执行方法:");
    }

    @AfterReturning("execution(* com.huawei.yinya.AOP.EsightPerformanceMonitor.*(..))")
    public static void methodReturning() {
        //  System.out.println("动态代理：方法正常执行结束:" + method.getName() + " 结果：" + args);
        System.out.println("动态代理：方法正常执行结束:");

    }

    @AfterThrowing("execution(* com.huawei.yinya.AOP.EsightPerformanceMonitor.*(..))")
    public static void methodThrowing() {
        // System.out.println("动态代理：方法执行出现异常:" + method.getName() + " Exception cause：" + args.getCause());
        System.out.println("动态代理：方法执行出现异常:");

    }

    @After("execution(* com.huawei.yinya.AOP.EsightPerformanceMonitor.*(..))")
    public static void methodFinish() {
        //  System.out.println("动态代理：方法最终执行结束" + method.getName());
        System.out.println("动态代理：方法最终执行结束");
    }
```

```java
   @Test
    public void aopTest(){
        // 需要用接口类型来获取，不要用实现类的类型去获取
        PerformanceMonitor aop = ctx.getBean(PerformanceMonitor.class);
        aop.dataAnalyze(Lists.newArrayList());
    }

// 输出结果：
动态代理：开始执行方法:
动态代理：方法最终执行结束
动态代理：方法正常执行结束

//这里需要注意就是方法的输出顺序，不是常规的 开始执行--正常执行结束--最终执行结束
```

```java
				System.out.println(aop);
        System.out.println(aop.getClass());
// com.huawei.yinya.AOP.EsightPerformanceMonitor@424e1977
// class com.sun.proxy.$Proxy13 本质上aop这个class是个代理对象，因为Spring的AOP底层就是java的动态代理，容器中保存的其实是PerformanceMonitor它的接口的代理对象Proxy ，而不是本类型EsightPerformanceMonitor对象 ，所以如果你去拿一个而不是本类型EsightPerformanceMonitor对象对象去容器中查找是不可能找到的，虽然我们给EsightPerformanceMonitor加了@Service这个注解，其实IOC容器为EsightPerformanceMonitor创建的是代理对象，
// "execution(* com.huawei.yinya.AOP.EsightPerformanceMonitor.*(..))" 
//@Aspect
//@Component
// 如果没有切面的话，那么IOC就不会在创建代理对象了  
// 代理对象跟被代理对象唯一能产生关联的地方就在于接口！ 所以需要拿接口来获取 
```

##### JointPoint获取方法参数以及方法信息

在切入方法的时候可以获取方法的详细信息！

在切入方法的参数内加上JoinPoint就可以了！

```java
   @Before("execution(* com.huawei.yinya.AOP..*.*(..))")
    public static void methodStart(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();

        System.out.println("动态代理：开始执行方法:" + methodName + " 参数：" + Arrays.asList(args));
    }
```

在方法结束的还是拿到返回结果，以及出现异常的时候获取异常信息

```java
   // 在注解上通知Spring 使用object来接受方法的返回结果
    @AfterReturning(value = "execution(* com.huawei.yinya.AOP..*.*(..))", returning = "object")
    public static void methodReturning(JoinPoint joinPoint, Object object) {
        String name = joinPoint.getSignature().getName();
        System.out.println("动态代理：方法正常执行结束:" + name + " 结果：" + object);

    }

    @AfterThrowing(value = "execution(* com.huawei.yinya.AOP..*.*(..)))", throwing = "error")
    public static void methodThrowing(JoinPoint joinPoint, Exception error) {
        System.out.println("动态代理：方法执行出现异常:" +joinPoint.getSignature().getName()+ " Exception cause：" + error.getCause());
    }
```

方法是参数列表在底层是Spring在底层使用反射来调用，所以方法的参数顺序要保持一致，不能乱写

#### CGLib代理

Spring AOP强大的地方在于，在做切面的时候一个class就算没有接口，也可以实现代理，只不过底层使用的不再是动态代理，而是CGLib实现的代理！

现在这里有一个实现类，EsightPerformanceMonitorNoInterface去除了继承的接口，现在我们在运行测试方法

依旧可以正常运行！

```java
 EsightPerformanceMonitorNoInterface noInterface = ctx.getBean(EsightPerformanceMonitorNoInterface.class);
        noInterface.dataCollect(Lists.newArrayList());
        System.out.println(noInterface);
        System.out.println(noInterface.getClass());
```



#### execution表达式

##### execution的标准写法如下：

```tex
execution(方法修饰符 方法返回值 方法全类名(方法参数))
```

##### 支持通配符模式

```tex
* 
1. 匹配一个或者多个字符
execution(* com.huawei.yinya.AOP.Esight*.*(..)) 匹配Esight开头的所有类 
2. 匹配任意的方法参数（避免方法重载） 
(int,*) 第一个参数int类型 第二个参数任意类型，参数有且只有两个
3. 匹配一层路径，如果是以*开始的，那就是所有
com.huawei.yinya.*
只能匹配yinya下的第一层包的路径
com.huawei.yinya.impl 可以匹配
com.huawei.yinya.impl.impl2 不能匹配

比如这里*. 就是所有包下
.. 
匹配任意多个参数或者任意类型的参数
1）(..) 匹配任意的参数
2) 匹配任意的路径
com.huawei.yinya.. 表示任意多层路径
com.huawei.yinya..PerformanceMonitor 
yinya包下的任意包下的PerformanceMonitor
```

##### 方法修饰符要么精确 要么不写，不能使用 * 代替，如果不写，实际上也只能切private方法

问题：Spring AOP如何切private protected default方法？

#### Execution表达式最模糊的写法：

```tex
execution(* *.*(..)) 或 execution(* *(..))
都代表任意包下任意类的任意方法
```

##### Execution的一些小结

```tex
在使用spring框架配置AOP的时候，不管是通过XML配置文件还是注解的方式都需要定义pointcut"切入点"

例如定义切入点表达式 execution(* com.sample.service.impl..*.*(..))

execution()是最常用的切点函数，其语法如下所示：

 整个表达式可以分为五个部分：

 1、execution(): 表达式主体。

 2、第一个*号：表示返回类型，*号表示所有的类型。

 3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。

 4、第二个*号：表示类名，*号表示所有的类。

 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。 
```



##### 可重用切入点

```java
 // 可重用的切入点表达式，使用@Pointcut注解
    @Pointcut("execution(* com.huawei.yinya.AOP..*.*(..)))")
    public void reusedPointCut() {
    }

  @Before("reusedPointCut()")
    public static void methodStart(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();
        // 获取方法的名字
        String methodName = joinPoint.getSignature().getName();

        System.out.println("动态代理：开始执行方法:" + methodName + " 参数：" + Arrays.asList(args));
    }


    // 在注解上通知Spring 使用object来接受方法的返回结果
    @AfterReturning(value = "reusedPointCut()", returning = "object")
    public static void methodReturning(JoinPoint joinPoint, Object object) {
        String name = joinPoint.getSignature().getName();
        System.out.println("动态代理：方法正常执行结束:" + name + " 结果：" + object);

    }

    @AfterThrowing(value = "reusedPointCut()", throwing = "error")
    public static void methodThrowing(JoinPoint joinPoint, Exception error) {
        System.out.println("动态代理：方法执行出现异常:" + joinPoint.getSignature().getName() + " Exception cause：" + error.getCause());
    }

    @After("reusedPointCut()")
    public static void methodFinish(JoinPoint joinPoint) {
        System.out.println("动态代理：方法最终执行结束: " + joinPoint.getSignature().getName());
    }
```



