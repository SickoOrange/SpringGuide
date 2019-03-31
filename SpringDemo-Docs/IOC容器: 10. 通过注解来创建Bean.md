### IOC 容器：通过注解来创建Bean 

通常如果我们要创建一个Bean，我们需要在配置文件中通过XML标签进行配置，这是通过配置的模式！这个模式有一个缺点就是，如果Bean特别的多的话，那么我们需要在XML配置文件中写上大量的代码，但是现在Springt提供的注解功能可以快捷的将Bean加入到IOC容器中！这种注解模式可以是我们不使用配置文件的方式来将Bean注册到IOC容器中。

Spring原生提供了四个注解

- Controller：控制器层
- Service：业务层
- Repository：数据库层
- Component：通用注解

##### 注：如果一个组件被标记了以上注解，那么这个类的所有子类都会默认继承这个注解成为IOC容器中的组件

某个类上添加上任意以上注解中的一个都可以该类作为Bean加入到IOC容器中。

以上四个注解其实没有本质的区别，只不过开发人员可以在开发的时候根据类的行为来区分添加，这几个注解其实也指明了当前类的功能。 Spring底层并不会对这些注解进行区分，所以你乱加都无所谓！

 使用注解的模式来讲Bean添加进IOC容器需要在Spring的配置中配置以下语句：

```xml
<!--annotation.xml-->
<context:component-scan base-package="com.huawei.yinya"/>
```

并且指定包的路径，Spring就会去该包下去扫描所有注解！

使用注解功能需要以来Spring的aop jar包！

```java
@Repository
public class UserService {
}
```

```java
public class AnnotationTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("annotation.xml");
    @Test
    public void test(){
        // 基于注解的行为，bean的id就是类名的驼峰形式
        // 如果想自定义Bean的id，可以直接这么写：
        // @Repository("testService")
        // @Scope(value="prototype") 可以指定Bean是否是单例
        UserService dbService = (UserService) context.getBean("userService");
        System.out.println(dbService);

    }
}
```

使用注解加入到容器中的组建跟使用配置加入到容器的组建的行为都是一模一样的！

下面在扫描包的时候可以指定要扫描的类跟不需要扫描的类，也是通过XML的标签来配置！

```xml
 <context:component-scan base-package="com.huawei.yinya.annotation">
        <!--type="annotation" 按照注解进行排除，标了注定注解的值将会被排除-->
        <!--expression表示注解的全类名-->
        <context:exclude-filter type="annotation"
                                expression="org.springframework.stereotype.Repository"/>
    </context:component-scan>
```

type的规则其实很多：

- annotation：按照注解进行排除

- assignable： 按照类来进行排除
- aspectj：aspectj表达式
- custom：自定义的TypeFilter的实现类，实现match方法，自己决定哪些来使用，哪些不使用
- regex：通过正则表达式来排除

```xml
<context:include-filter type="...." expression="...."/>
<!--与exclude功能正好相反，比如你只想要repository注解标志的类-->

<!--在使用include的时候需要禁用Spring的默认扫描规则！-->
<context:component-scan base-package="com.huawei.yinya" use-default-filter="false"/>
```

