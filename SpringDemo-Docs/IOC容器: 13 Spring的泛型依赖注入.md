---
typora-copy-images-to: ./image/屏幕快照 2019-03-24 下午10.32.22.png
typora-root-url: ./image
---

### IOC 容器：Spring的泛型依赖注入

首先看一下下面这个例子：

我们有一个BookService跟BookDao两个类：

```java
@Service
public class BookService {

    @Autowired
    MyBookDao bookDao;

    public void save() {
        bookDao.save();
    }
}

@Repository
public class MyBookDao extends BaseDao<Book> {
    public void save() {
        System.out.println("保存图书");
    }
}

```

还有一个UserService跟UserDao两个类：

```java
@Service
public class UserService {
    @Autowired
    MyUserDao userDao;

    public void save() {
        userDao.save();
    }
}
@Repository
public class MyUserDao extends BaseDao<User> {
    public void save() {
        System.out.println("保存用户");
    }
}

```

两个Dao类都是BaseDao的子类：

```java
public abstract class BaseDao<T> {
    public abstract void save();
}
```



下面我们来测试一下：

```java

public class GenericServiceTest {
    ApplicationContext ioc = new ClassPathXmlApplicationContext("generic.xml");

    @Test
    public void test(){
        BookService bookService = ioc.getBean(BookService.class);
        UserService userService = ioc.getBean(UserService.class);
        bookService.save();
        userService.save();
    }
}
// 程序打印如下：
// 保存图书
// 保存用户
```



现在我们对Service类进行改造，提取其中的公共方法抽取到一个公共Service类中：

```java
@Service
public class BookService extends GenericService<Book> {
}

@Service
public class UserService extends GenericService<User>  {

}

public class GenericService<T> {

    @Autowired
    BaseDao<T> baseDao;

    public void save() {
        System.out.println("自动注入dao类型："+baseDao);
        baseDao.save();
    }
}

```

在运行原来的测试，会发现如下效果：

```java
// 自动注入dao类型：com.huawei.yinya.generic.dao.MyBookDao@41ee392b
// 保存图书
// 自动注入dao类型：com.huawei.yinya.generic.dao.MyUserDao@1e67a849
// 保存用户

```

我们在调用

```java
bookService.save();
userService.save();
```

这两个方法的时候，会去调用GenericService中的方法，而在GenericService中，会自动替我们注入使用BookService还是DaoService！

这里值得注意的时候，在之前我们说@Autowired跟@Service等注解必须要成对使用，否则无法完成自动注入的功能，但是在这里GenericService并不是Spring容器中的组件，只不过它的两个子类BookService跟UserService是容器中的组件，但是却也完成了对BaseDao的自动注入！

##### 在这里需要注意的是：

##### 只要是继承关系，那么子类是IOC容器里的组件，那么父类里面的@Autowired就可以正常工作！

##### 在父类GenericService中通过@Autowired为BaseDao注入了对应的Dao的组件，那么同样这个BaseDao也不是IOC容器中的组件，只不过它的两个子类 BookDao跟UserDao是IOC容器里面的两个组件！这就是泛型依赖注入的效果

而这里就需要关注的一点就是：

为什么调用bookService的save方法，容器给我们调用的就是bookDao，而调用userService中的save方法，我们调用的而是userDao，Spring注入的是BaseDao，但是Spring又是如何知道我们什么时候该调用哪一个子类的dao呢？

![屏幕快照 2019-03-24 下午10.32.22](/屏幕快照 2019-03-24 下午10.32.22.png)

其实查找就是依赖泛型实现的：

以BookService举例子，BookService的泛型为Book，所以在调用BookService的save方法时，其实调用的就是GenericService的save方法，而在GenericService使用@Autowired注解为BaseDao<T> 进行自动注入的时候，注入的类型其实就是：

BaseDao<Book>这个类型，而我们可以发现BaseDao<Book>是一个抽象类，所以在注入的时候就将BookDao这个组件注册给了BaseDao<Book>。这就是为什么Spring能够区分到底什么时候需要调用BookDao的方法，什么时候调用UserDao的方法

为了验证这一点，我们可以看一下bookService拿到的类型到底是什么：

```java
System.out.println(bookService.getClass());
System.out.println(bookService.getClass().getSuperclass());
System.out.println(bookService.getClass().getGenericSuperclass());
// class com.huawei.yinya.generic.service.BookService
// class com.huawei.yinya.generic.service.GenericService
// com.huawei.yinya.generic.service.GenericService<com.huawei.yinya.generic.bean.Book>


// 这也更加验证在Spring中是根据带有泛型的父类的类型来确定子类的类型
// 这就是Spring的泛型依赖注入的特点
```

##### Spring的泛型依赖注入的特点

```tex
一句话简单总结泛型依赖注入就是：

Spring首先根据泛型T来确定父类GenericService<T>的类型，然后再来确定子类的类型：

比如T是Book，

那么首先Spring就会去找GenericService<Book>这个组件，而

BookService extend GenericService<Book> 

所以最终Spring会找到BookService这个组件，这也间接验证了为什么Spring不要求GenericService是IOC容器中的组件，但是要求其子类BookService是IOC容器中的组件的原因，因为最终Spring会在经历一系列的搜索后去IOC容器中BookService这个组件，而不是GenericService这个组件！
```



