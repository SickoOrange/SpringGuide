### IOC 容器：在单元测试中使用@Autowired自动注入 

先来看看下面一个常规的单元测试写法：

```java
public class AnnotationTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");

   @Autowired
    UserDao userDao;

    @Test
    public void test() {
        System.out.println(userDao);
    }
}
```

我们在运行这个测试的时候，会发现这个测试运行的结果就是没有办法能够自动注入userDao，原因就是@Autowired必须要跟

@Component，@Repository这些注解一起使用才可以，因为我们这里AnnotationTest并不是Spring IOC容器中的组件，所以理所当然，即使你使用@Autowired注解也没有办法能够获取到IOC容器中的userDao组件！

那既然这样我们在AnnotationTest上加一个注解是不是就可以解决这个问题呢？

```java
@Component
public class AnnotationTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");

   @Autowired
    UserDao userDao;

    @Test
    public void test() {
        System.out.println(userDao);
    }
}
```

我们再一次运行，会发现这个Test依旧没有办法运行，会进入无限创建的死循环中！

```tex
三月 24, 2019 9:35:21 下午 org.springframework.context.support.ClassPathXmlApplicationContext prepareRefresh
信息: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@2fa2143d: startup date [Sun Mar 24 21:35:21 CST 2019]; root of context hierarchy
三月 24, 2019 9:35:21 下午 org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
信息: Loading XML bean definitions from class path resource [annotation.xml]
```

要了解原因的话，我们就需要了解一下单元测试的原理！

首先我们运行一个test()这个测试方法，单元测试JUnit会创建一个AnnotationTest对象，在创建对象的过程中，会初始化其成员变量：

```java
ApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");
```

来启动IOC容器，IOC容器启动会扫描扫有的组件，而AnnotationTest正好时Componnet组件，IOC容器就会又为AnnotationTest创建对象，以创建对象就又会启动一个IOC容器么，这样就会形成一个死循环，知道程序运行崩溃！

为了解决这个问题，可以使用Spring的单元测试包！

```java
@ContextConfiguration(locations = "classpath:annotation.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class AnnotationTest {
   @Autowired
    UserDao userDao;

    @Test
    public void test() {
        System.out.println(userDao);
    }
}

// @ContextConfiguration(locations = "classpath:annotation.xml")
// 加载Spring的配置文件
// @RunWith(SpringJUnit4ClassRunner.class)
// 指定JUnit使用Spring的Test驱动

// 这样就可以直接使用@Autowired来自动装配，不需要在使用context.getBean();
```