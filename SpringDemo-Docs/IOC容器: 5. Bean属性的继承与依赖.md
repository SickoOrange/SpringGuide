### IOC 对象创建：Bean的属性与继承

##### Bean之间属性的继承

```xml
 
   <!--Bean属性的继承-->
    <bean id="laptop" class="com.huawei.yinya.Laptop">
        <property name="name" value="lenovo"/>
        <property name="model" value="2018"/>
        <property name="price" value="5000"/>
    </bean>

	<!--Bean属性的继承 通过parent标签实现-->
    <!--使用parent标签表示当前的laptopcopy中的属性都继承与laotop-->
    <!--但是 laptopcopy跟laptop之间并没有直接的关系，也没有继承关系-->
    <bean id="laptopcopy1" class="com.huawei.yinya.Laptop" parent="laptop">
    </bean>

    <!--通过parent标签继承的属性如果想直接更改，可直接使用property标签-->
    <bean id="laptopcopy2" class="com.huawei.yinya.Laptop" parent="laptop">
        <property name="name" value="razer"/>
    </bean>

```



注意如果我们使用另外一个标签来制定laotop这个bean只能被继承，不能被获取实例的话，我们只需要这么做

```xml
 <bean id="laptop" class="com.huawei.yinya.Laptop" abstract="true">
```

用abstract限定之后，你将不能在通过任何方法从IOC容器中获取到这个实例！laotop这个bean只能在配置文件中被其他的bean所继承，也就是属性的继承！强行获取这个Bean会抛出BeanAbstractException异常！



##### Bean之间的依赖性

通常情况下Bean的创建顺序是按照配置文件的顺序自上而下来创建的！通常我们会有一些特定的需求，比如在创建某个Bean之前，我们需要创建另外的一个或者几个Bean！这就是Bean之间的依赖性。Spring在创建Bean的时候可以通过 depends-on来显示的制定Bean的先后创建顺序

```xml
<bean id="master" class="com.huawei.yinya.master" depends-on="laptop">
<bean id="laptop" class="com.huawei.yinya.Laptop">
```

如果没有depends-on这个标签，那么master 跟 laptop之间Spring首先会创建master，然后在来创建laptop！

现在显示的通过depends-on标签制定了双方之间的依赖，那么Spring就会先去创建laptop，然后再去创建master

这就是所谓的Bean之间的依赖性