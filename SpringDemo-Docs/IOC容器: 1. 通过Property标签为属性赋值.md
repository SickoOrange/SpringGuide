###  Spring的配置文件加载路径：

Spring提供了两种方式来获取Spring配置文件的方法：

- 通过类路径加载

  ```java
  ClassPathXmlApplicationContext
  ```

- 通过文件系统磁盘路径加载

  ```java
  FileSystemXmlApplicationContext
  ```

### IOC 对象创建：通过Property标签为基本类型属性赋值

Spring主要通过读取xml中的bean标签，然后将对应的bean标签里面的bean声明通过反射的方式在IOC容器中创建，之后我们便可以通过bean标签中的id来获取这个bean的实例对象！

假设我们现在这里有一个Laptop Bean对象，定义如下：

```java
package com.huawei.yinya;

public class Laptop {

    private String name;
    private int price;
    private String model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}
```

在spring的配置文件中bean的配置如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="macbookpro" class="com.huawei.yinya.Laptop">
        <!--通过property标签为Bean中的属性进行赋值-->
        <property name="name" value="macbookpro"/>
        <property name="model" value="2018"/>
        <property name="price" value="15000"/>
    </bean>
    
     <bean id="lenovo" class="com.huawei.yinya.Laptop">
        <!--通过property标签为Bean中的属性进行赋值-->
        <property name="name" value="lenovo"/>
        <property name="model" value="2018"/>
        <property
</beans>
```

1. ##### 通过bean id来获取这个macbook的实例对象：

```java
// 声明一个classpathj conext对象 告诉spring 应该从类路径下来加载配置文件
ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
// 通过id 来获取对应的实例
Laptop macbookpro = (Laptop) ctx.getBean("macbookpro");
// 打印实例   
System.out.println(macbookpro);

打印结果： Laptop{name='macbookpro', price=15000, model='2018'}
```

2. ##### 通过类型来获取实例对象, 该类型在配置文件中只能有一个定义 

```java
/**
 * 从容器中拿到 通过Bean的类型来获取
 */
@Test
public void test2() {
    Laptop macbookpro = (Laptop) ctx.getBean(Laptop.class);
    System.out.println(macbookpro);
}
打印结果： Laptop{name='macbookpro', price=15000, model='2018'}

// 注意使用Bean的类型来获取实例，如果配置文件中定义了多个id不同，但是类型相同的Bean对象，则获取会失败，
// 具体见注意事项
```

3. ##### 通过Bean的id跟类型来获取bean class相同的多个不同实例

```java
    /**
     * 从容器中拿到 通过Bean的id跟类型来获取bean class相同的多个不同实例
     */
    @Test
    public void test3() {
        Laptop macbookpro = (Laptop) ctx.getBean("macbookpro", Laptop.class);
        Laptop lenovo = (Laptop) ctx.getBean("lenovo", Laptop.class);

        System.out.println(macbookpro);
        System.out.println(lenovo);
    }
```

##### 注意事项：

- 容器中的对象在Spring的IOC容器创建的时候就已经完成了配置文件的解析跟对象的实例化

  ```java
  ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
  ```

  无需等到我们调用getBean()方法，再去创建！

- IOC容器中创建的对象都是单例，无论调用多少次getBean，返回的都是同一个对象 

- 如果试图从IOC容器中获取一个不存在的对象，Spring就会保如下异常：

  ```xml
  org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'macbookpro1' available
  ```

  在这里我试图获取一个id=macbookpro1的对象，但是在配置文件中并没有定义该对象

- 如果使用类型来获取Bean的对象，在配置文件中该类型的对象定义只能存在一个，如果定义了多个，则会发生如下异常：

  ```java
  org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.huawei.yinya.Laptop' available: expected single matching bean but found 2: macbookpro,lenovo
  // 这里定义了两个bean，一个id是macbookpro 一个id是lenovo，他们都属于Laptop，所以获取失败！
  // 在这里只能
  ```

  

- 在xml配置文件中为Bean的属性进行赋值，底层是利用java的反射来实现的，会调用Bean中的setXXX()方法，所以Bean的定义一定要加上属性的get/set方法，所以Bean中的get/set方法一定要跟Bean中的属性字段要一一对应！

