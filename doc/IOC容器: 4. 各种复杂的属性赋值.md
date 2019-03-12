### IOC 对象创建：为各种复杂的属性赋值

之前说的都是对Bean的中简单的七种基本类型的变量进行赋值，我们可以使用Property标签，构造器标签，p名称空间来实现，下面针对Bean中比较复杂的属性，如下：

- 引用对象
- list集合
- map集合
- set集合
- Propertis属性

对于这些属性的赋值，Spring IOC也给我们提供了独特的标签来实现！

下面我们依旧使用Laptop这个对象来举例，来说说为上述几种比较复杂的属性赋值是怎么实现的！我们需要修改一下Laptop对象来定义这些复杂的属性！

```java
public class ComplexLaptop {

    private String name;
    private int price;
    private String model;

    // 电脑只有一个主人
    private Master master;

    // 假设一个电脑也有多个使用的人
    private List<Master> masters;

     // 随便定义一个map 数据结构
    private Map<String, Object> map;

    // properties 属性
    private Properties properties;
}
```

```java
public class Master {
    // 主人的名字
    private String name;
}

```



##### 引用对象赋值：

- 使用ref标签来引用容器中的master赋值

  ```xml
      <!--为成员变量赋null，这里需要用专门的null标签来赋值-->
      <bean id="macbookpro1" class="com.huawei.yinya.ComplexLaptop">
          <property name="name">
              <!--使用null标签来为属性赋null值-->
              <null></null>
          </property>
          <property name="master" ref="master">
          </property>
      </bean>
  
  	<!-- IOC 容器中 定义的master 对象-->
      <bean id="master" class="com.huawei.yinya.Master">
          <property name="name" value="yinya"/>
      </bean>
  
   		<!--使用ref标签引用IOC容器中的master-->
          <!--该master是一个单例，如果外部对master对象修改了，该影响也会-->
          <!--反馈到macbookpro1中持有的master引用对象上-->
          <!--因为他们共享一个master单例-->
  ```

  

- 使用嵌套bean标签赋值

  ```xml
    <bean id="macbookpro2" class="com.huawei.yinya.ComplexLaptop">
          <property name="name">
              <null/>
          </property>
          <!--使用bean标签创建master master=new Master() 不是单例-->
          <property name="master">
              <bean class="com.huawei.yinya.Master">
                  <property name="name" value="yinya"/>
              </bean>
          </property>
      </bean>
  ```

  

##### List属性赋值

```xml
  <bean id="user2" class="com.huawei.yinya.Master">
        <property name="name" value="zhuqi"/>
    </bean>
    <!--为list<Master> 赋值-->
    <bean id="macbookpro3" class="com.huawei.yinya.ComplexLaptop">

        <!--使用list标签创建 list =new Arraylist<Master>()-->
        <property name="masters">
            <list>
                <!--使用bean标签创建前一个user-->
                <bean id="user1" class="com.huawei.yinya.Master" p:name="zhouyangkai"/>
                <!--使用ref标签引用一个ioc容器中的user-->
                <ref bean="user2"/>
            </list>
        </property>
    </bean>

<!--注意所有在内部使用bean标签创建的bean都是零时的bean，这些bean都不会被ioc容器管控！也不会被ioc容器所保存，如果你尝试着去获取这些内部bean，Spring会包NoSuchBeanDefinition异常-->

```

获取这个bean 打印结果可以看到：

```java
	ComplexLaptop{name='null', price=0, model='null', master=null, masters=[Master{name='zhouyangkai'}, Master{name='zhuqi'}], map=null}
```

list列表用有两个使用者 zhouyangkai 跟zhuqi


##### Map属性赋值 

```xml
  <!--为Map<String,Object> 赋值-->
    <bean id="macbookpro4" class="com.huawei.yinya.ComplexLaptop">
        <!--使用map标签创建 list =new Hashmap<String,Object>()-->
        <property name="map">
            <map>
                <!--value为字符串-->
                <entry key="string" value="master"/>
                <!--value为int-->
                <entry key="int" value="18"/>
                <!--value 是一个IOC容器bean-->
                <entry key="outterBean" value-ref="user2"/>
                <!--value 是一个通过bean标签创建的bean-->
                <entry key="innerBean">
                    <bean class="com.huawei.yinya.Master">
                        <property name="name" value="yinya"/>
                    </bean>
                </entry>
            </map>
        </property>
    </bean>
```

这里的map是一个<String,Object>类型的集合，所以在这里测试了四种不同类型的数据，分别是value为String，Integer，IOC 容易bean，跟通过bean标签创建bean这四种情况！当然value还可以是嵌套的list或者map！

我们通过代码获取这个map来打印看一下，是否赋值是正确的：

```java
 /**
     * 为map属性赋值
     */
    @Test
    public void test4() {
        ComplexLaptop macbookpro = (ComplexLaptop) ctx.getBean("macbookpro4");
        System.out.println(macbookpro.getMap());
    }
// 打印结果
//{string=master, int=18, outterBean=Master{name='zhuqi'}, innerBean=Master{name='yinya'}}
```



##### Properties属性赋值

```xml
  <!--为properties属性赋值-->
    <bean id="macbookpro5" class="com.huawei.yinya.ComplexLaptop">
        <!--使用props标签创建 list =new Hashmap<String,Object>()-->
        <property name="properties">
            <props>
                <prop key="hello">value</prop>
                <prop key="nihao">china</prop>
            </props>
        </property>
    </bean>
    <!--打印properties结果如下-->
 	<!--{nihao=china, hello=value}-->
```



##### 使用util名称空间来创建集合bean，方便各种bean引用

使用util名称空间创建统一的集合bean，这样可以给其他bean来引用，就可以避免每一次都要重复去写大量创建map的配置代码

```xml
   <!--使用util名称空间来创建map集合，共其他人引用 默认 LinkedHashMap-->
    <util:map id="refMap">
        <!--value为字符串-->
        <entry key="string" value="master"/>
        <!--value为int-->
        <entry key="int" value="18"/>
        <!--value 是一个IOC容器bean-->
        <entry key="outterBean" value-ref="user2"/>
        <!--value 是一个通过bean标签创建的bean-->
        <entry key="innerBean">
            <bean class="com.huawei.yinya.Master">
                <property name="name" value="yinya"/>
            </bean>
        </entry>
    </util:map>

<!--通过ref标签来引用这个refmap集合-->
<property name="map" ref="refMap">
```

同样使用util名称空间还可以创建如下：

- util list
- util set
- util propertis



##### 为级联属性赋值

```xml
    <!--为级联属性赋值 所谓的级联属性就是属性的属性，比如Laptop中Master的name-->
    <!--master.name相对于Laptop就是一个级联属性-->
    <bean id="macbookpro6" class="com.huawei.yinya.ComplexLaptop">
        <property name="master" ref="master"/>
        <!--现在master中name由yinya变成了zhouyangkai-->
        <!--级联属性赋值 也会修改IOC容器中master的属性！-->
        <!--因为这里使用的是ref引用，引用的直接是地址！-->
        <property name="master.name" value="zhouyangkai"/>
    </bean>
```

