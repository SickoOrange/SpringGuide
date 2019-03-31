### IOC 容器：基于XML的自定义对象自动装配

```xml
   <!--所谓的自动装配也就是说为Bean中的自定义对象进行自动赋值-->
    <!--常规情况下为Bean的自定义对象赋值可以只用引用赋值等方法，但是都需要手动指定-->
    <!--但是Spring IOC容器给我们提供了一种自动装配赋值的方法 可以自动的为macbook中的Master-->
    <!--对象进行赋值-->
    <!--这就是autowired标签-->
    <!--autowire标签一共有五种取值-->
    <!--1. default/no 默认不自动装配赋值-->
    <!--2. byName 按照属性名自动装配，比如macbook中含有一个master的自定义属性-->
    <!--Spring回去IOC容器自动寻找id为master的bean，找不到就不自动装配-->
    <!--3. byType 按照type类型自动装配，前提是IOC容器只能含有一个Master对象的bean，-->
    <!--因为按照类型查找不能配置多个类型相同的bean-->
    <!--4. constructor 按照构造器进行自动装配-->
    <bean id="macbook" class="com.huawei.yinya.ComplexLaptop" autowire="byName">
        <property name="name" value="macbookPro"/>
    </bean>

    <!--bean的生命周期方法-->
    <bean id="master" class="com.huawei.yinya.Master">
        <property name="name" value="yinya"/>
    </bean>
```

其中按照构造器的自动装配比较的复杂，首先它需要Bean对象有一个有参数的构造器，

比如：

```java
public ComplexLaptop(Master master){
    super();
}
```

装配的原则如下：

1. 首先按照有参构造器的参数的类型进行匹配，如果IOC容器中有该类型的bean，则进行自动装配
2. 如果按照参数类型在IOC容器中找到多个相同类型的bean对象
   - 以构造器参数名进行匹配，找不到就装配一个null

##### 所有的自定义对象，list map都可以进行自动装配！但是仅限于对自定义的类型的对象有效