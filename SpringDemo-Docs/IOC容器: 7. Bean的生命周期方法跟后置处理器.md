### IOC 对象创建：Bean的生命周期方法

在xml配置bean的时候 我们可以为Bean指定两个特殊的方法，一个是初始化方法，可以做一些Bean的初始化工作，另一个是destory方法，可以在Bean销毁的时候做一些后续处理工作！

```xml
<!--bean的生命周期方法-->
    <bean id="master" class="com.huawei.yinya.Master" init-method="masterInit" destroy-method="masterDestroy"/>
<!--我们只需要指定init-method跟destory-method标签即可-->
```

这两个方法的调用流程如下：

Bean的构造器 ———> Bean的init method方法 —————> Bean的destory method方法

##### 注意：

Bean的destory方法通常是在容器销毁的时候才会调用！

```java
ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring3.xml");
```

在Spring中默认的ClassPathXmlApplicationContext是没有close方法关闭容器的，但是在顶级父类ApplicationContext的一级子类 ConfigurableApplicationContext 可以实现销毁容器！

只需要调用：

```java
 ctx.close();
```



#### IOC 对象创建：Bean的后置处理器

1. Bean 后置处理器允许在调用初始化方法前后对 Bean 进行额外的处理.

2. Bean 后置处理器对 IOC 容器里的所有 Bean 实例逐一处理, 而非单一实例. 其典型应用是: 检查 Bean 属性的正确性或根据特定的标准更改 Bean 的属性.
3. 对Bean 后置处理器而言, 需要实现 BeanPostProcessor 接口. 

简单的给Master类实现一个后置处理器：

```java
package com.huawei.yinya;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MasterPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return o;
    }
}

```



```xml
 <!--bean的生命周期方法-->
    <bean id="master" class="com.huawei.yinya.Master" init-method="masterInit" destroy-method="masterDestroy"/>
    <!--配置后置处理器-->
    <bean class="com.huawei.yinya.MasterPostProcessor"/>
```

控制台打印内容如下：

> master constructor
> postProcessBeforeInitialization
> master init
> postProcessAfterInitialization
> master destory

可以看到后置处理器的调用在调用Bean的构造器之后，init方法前后调用的，如果Bean没有初始化方法，则依旧会在构造器调用之后调用！