### IOC 容器：@Autowired自动装配功能 

### 利用@Autowired注解来达到自动装配的一个典型的使用场景

```java
@Repository
public class UserDao {

    public void save(Master master) {
        System.out.println("user 已经保存到数据库：" + master.getName());
    }
}
```

```java
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void insertUser(Master master) {
        System.out.println("即将保存master到数据库");
        userDao.save(master);
    }
}
```

```java
  @Test
    public void test1() {
        UserService userService = (UserService) context.getBean("userService");
        userService.insertUser(new Master("yinya"));
    }
```

输出：

即将保存master到数据库
user 已经保存到数据库：yinya



可以看到利用@Autowired可以自动识别我们的UserDao这个Bean，并将其通过自动装配赋值到UserService类中的userDao这个变量中！它回去容器中寻找变量需要的对应的组建！

### @Autowired自动赋值的原理

```java
    @Autowired
    UserDao userDao;
```

首先Spring会按照类型去IOC容器中寻找对应的组件

其实这个过程就等价于：

```java
userDao=context.getBean(UserDao.class);
```

按照类型查找有多种情况：

- 找到对应的组件，直接装配赋值
- 没有找到对应组件：抛出异常NoSuchBeanException
- 按照类型查找有多个UserDao的Bean，Spring会继续按照变量名(userDao)进行查找， 也就是组件的默认变量名，如果都没有匹配上，就会抛出异常！

针对第三种情况，按照类型跟**默认变量名**都不能发在IOC容器中找到指定的组建的话，这个时候可以使用一个注解叫做：@Qualifier("vairantName")

@Qualifier的作用就是告诉Spring不要用默认变量名userDao去IOC容器中寻找组件，而是用我指定的变量名!

这个注解主要使用的场景就在于如果需要装在的Bean在IOC容器中有多个的话，你可以使用@Qualifier指定去加载一个特定的Bean对象！

使用@Qualifier按照特定的bean name去IOC寻找组件依旧会有两种情况：

- 找到就自动装配
- 找不到，就报错，抛出异常！

也就是说 @Autowired标注的自动装配的属性默认一定会装配上，否则找不到就会报错！

**如果我们不想要在找不到的时候 不抛出异常**，而是装配null的话，可以使用

```java
@Autowired(require=false)
```



### @Autowired的作用域：

通常我们使用Autowired注解都是写在成员变量上，通过看一下@Autowired的源码：

```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
```

我们可以发现这个注解的作用域基本包括了所有的情况：

ElementType.CONSTRUCTOR 构造器

ElementType.METHOD 方法

ElementType.PARAMETER 方法变量

ElementType.FIELD 成员字段

ElementType.ANNOTATION_TYPE 注解类型

以方法来举例子：

```java
@Autowired
public void insertMaster(Master master){
    .....
}
// 1. 如果方法上还有@Autowired的话，那么这个方法的每一个参数都是自动赋值。
// 2. 这个方法会在方法所在的Bean创建的时候自动运行，并将这些值自动注入，
// 3. 当然这个方法坐在的Bean必须也是Spring的一个组件！
// 4. 自动注入只对引用类型有效，对基本类型无效。
// 5. 如果指定bean的id去自动注入，可以这么写：
public void insertMaster(@Qualifier("customMaster")Master master){
    .....
}

```

### 三种不同的自动装配注解：

Spring提供的自动装配注解常规的有三种：

@Autowired： Spring提供，最强大

@Resource： J2ee提供

@Inject：

@Resource拓展性更加强大，因为他是java支持的，但是@Autowired只能在Spring容器中使用！如果切换到另外一个容器框架，只能使用@Resource注解！