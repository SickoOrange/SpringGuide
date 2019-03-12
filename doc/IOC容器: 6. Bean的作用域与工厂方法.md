### IOC 对象创建：Bean的作用域与工厂方法

##### Bean的作用域：单实例，多实例

在Spring中Bean的作用域默认是单实例，Spring也为我们提供了四种Bean的作用域！

- prototype: 多实例
- singleton:  单实例
- request:  web环境下，同一个请求会创建一个Bean实例
- session:  web环境下，同一个session会创建一个Bean实例



下面主要针对单实例跟多实例说一下吧，毕竟后面两个基本上没人用！

Spring在容器启动的过程中就会去加载跟创建所有的Bean对象，在容器启动完成之后这些Bean的对象也将被保存在容器中！

Spring的多实例有点特殊，因为它不是在容器启动的过程中去创建的，而是在**获取的时候创建**的！

多实例每次获取都会创建一个新的对象！

在Spring 中制定一个Bean是多实例只需要通过 scope标签就可以！

```xml
 
   <!--Bean的作用域-->
    <bean id="laptop" class="com.huawei.yinya.Laptop" scope="prototype">
        <property name="name" value="lenovo"/>
        <property name="model" value="2018"/>
        <property name="price" value="5000"/>
    </bean>

```



##### Bean的工厂方法

Spring中IOC创建Bean对象使用反射的方法来创建，在工厂中就直接用工厂方法来创建

常规的工厂方法一般来说分两种：

- 静态工厂：不需要创建工厂对象，只需要调用工厂里的静态方法即可
- 实例工厂：需要创建一个工厂对象，然后在调用工厂方法里面的方法即可
- FactoryBean：Spring的默认接口工厂，实现了FactoryBean的类都是一个默认的Spring的工厂类，Spring可以直接调用



```xml
  <!--静态工厂配置 需要制定class为工厂类，以及工厂的方法跟参数-->
    <bean id="xiaomi" class="com.huawei.yinya.LaptopStaticFactory" factory-method="createLaotop">
        <constructor-arg value="xiaomi"/>
    </bean>

```



```xml
  <!--实例工厂配置 需要先配置工厂bean，然后在配置要创建的实例Bean-->
  <!--在实例Bean中制定工厂的bean跟工厂方法以及参数-->
    <bean id="instanceFactory" class="com.huawei.yinya.LaptopInstanceFactory"/>
    <bean id="huawei" class="com.huawei.yinya.Laptop" factory-bean="instanceFactory" factory-method="createLaotop">
        <constructor-arg value="huawei"/>
    </bean>

```

要注意两种工厂方法的配置是有区别的！实例工厂的配置比较麻烦，需要配置两个Bean，一个工厂Bean，还有一个实例Bean。静态工厂配置相对比较简单

```java
  /**
     * 工厂方法
     */
    @Test
    public void test8() {
        // 静态工厂获取实例
        Laptop xiaomi = (Laptop) ctx.getBean("xiaomi");
        System.out.println(xiaomi);

        // 实例工厂获取实例
        Laptop huawei = (Laptop) ctx.getBean("huawei");
        System.out.println(huawei);
    }

```



如果要使用FactoryBean工厂的话，在容器启动的时候不会创建对象，而是在调用的时候才会创建。需要首先创建一个FactoryBean实例如下：

```java
package com.huawei.yinya;

import org.springframework.beans.factory.FactoryBean;

public class LaptopFactoryBeanImpl implements FactoryBean<Laptop> {
    public Laptop getObject() throws Exception {

        Laptop laptop = new Laptop();
        laptop.setName("factoryBeanLaptop");
        return laptop;
    }

    public Class<?> getObjectType() {
        return Laptop.class;
    }

    public boolean isSingleton() {
        return true;
    }
}

```

然后在配置就可以了!

```xml
 <!--FactoryBean工厂配置-->
    <bean id="factoryBeanLaptop" class="com.huawei.yinya.LaptopFactoryBeanImpl"/>
```

