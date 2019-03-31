### IOC 对象创建：通过P名称空间标签为基本类型属性赋值

在java中使用package主要是为了防止两个具有相同名字的类重复的问题，引入了包Package的概念，如果两个类具有相同的类型，但是在不同的包下，就可以区别这个两个类，如下：

```java
// yinya的Laptop
com.huawei.yinya.Laptop
// zhouyangkai的Laptop
com.huawei.zhouyangkai.Laotop
```

那么在xml结构的文件中也会存在如下的问题，xml中同一个标签也会有重复的情况下，这里就引入的名称空间的概念。

```xml

 <!--不引入标签，无法区别两个name标签-->
  <name>macbookpro</name>
  <master>
      <name>yinya</name>
  </master>
</Laotop>

 <!--引入标签，通过a，b两个标签来区别两个name标签 -->
<Laotop>
  <a: name>macbookpro</name>
  <master>
      <b: name>yinya</name>
  </master>
</Laotop>
```

给XML配置文件"减肥"的另一个选择就是使用p名称空间，从 2.0开始，Spring支持使用[名称空间](http://writeblog.csdn.net/apa.html)的可扩展配置格式。这些名称空间都是基于一种XML Schema定义。事实上，我们所看到的所有`bean`的配置格式都是基于一个 XML Schema文档。

使用p名称空间赋值配置如下：

在使用之前，需要在配置文件中头部导入对应的名称空间：

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

```xml
  <!--通过p名称空间进行赋值-->
    <bean id="macbookpro1" class="com.huawei.yinya.Laptop"
          p:name="macbookpro"
          p:model="2018"
          p:price="20000">
    </bean>
```



注意： p名称空间只能为一些简单的属性进行赋值，如果是一些比较复杂的属性就没有办法使用


