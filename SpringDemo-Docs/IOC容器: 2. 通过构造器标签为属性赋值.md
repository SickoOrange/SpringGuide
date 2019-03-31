### IOC 对象创建：通过构造器标签为基本类型属性赋值

使用构造器标签来为对象赋值，需要Bean对象中有一个有参构造器！下面我们在Laptop类中添加一个有参数的构造器，如下：

```java
  public Laptop(String name, int price, String model) {
        this.name = name;
        this.price = price;
        this.model = model;
    }
```

xml配置如下：

```xml
 <!--使用构造器进行赋值，推荐使用方式-->
    <bean id="acer" class="com.huawei.yinya.Laptop">
        <constructor-arg name="name" value="acer"/>
        <constructor-arg name="model" value="2018"/>
        <constructor-arg name="price" value="4000"/>
    </bean>

    <!--可以省略name属性，那么定义的时候需要严格的按照构造器参数来进行赋值-->
    <!--如果赋值顺序没有按照构造器参数的顺序，会产生赋值错误，甚至不能赋值的问题-->
    <bean id="acer1" class="com.huawei.yinya.Laptop">
        <constructor-arg value="acer1"/>
        <constructor-arg value="2018"/>
        <constructor-arg value="4000"/>
    </bean>

    <!--可以省略name属性，那么定义的时候需要严格的按照构造器参数来进行赋值-->
    <!--可以使用index来显示的指定赋值的顺序，0代表构造器的第一个参数，以此类推-->
    <bean id="acer2" class="com.huawei.yinya.Laptop">
        <constructor-arg value="acer1" index="0"/>
        <constructor-arg value="2018" index="1"/>
        <constructor-arg value="4000" index="2"/>
    </bean>

  <!--可以省略name属性，如果不严格的按照构造器参数来进行赋值，又有多个构造器重载的情况-->
    <!--Type标签主要用于指定给当前赋值的字段具体类型-->
    <!--这种方式不推荐使用，比较复杂，而且依旧存在随机赋值的可能性-->
    <bean id="acer3" class="com.huawei.yinya.Laptop">
        <constructor-arg value="acer1" index="0" type="java.lang.String"/>
        <constructor-arg value="2018" index="1" type="java.lang.Integer"/>
        <constructor-arg value="4000" index="2" type="java.lang.String"/>
    </bean>
```