### IOC容器

IOC其实就可以抽象的看作一个容器，来帮助我们去管理所有的组件。

而**容器的本质其实就是一个Map结构的数据类型，所有的组件都以特定的键值对的形式保存在这个Map中**

IOC能够帮我们提供一下功能：

- 依赖注入(DI) 一个@Autowired帮你自动注入所有的组件
- 某个组件需要使用Spring提供的所有功能，必须要将自己加入到IOC容器中
- 可以只用XML配置文件或者注解来将IOC加入到容器中



### AOP(Aspect Oriented Programmning) 面向切面编程

在程序**运行期间**，将某段代码**动态的切入**到**某一个指定方法**的**指定位置**运行！



### Spring的模块划分

![屏幕快照 2019-03-31 下午6.18.02](https://ws4.sinaimg.cn/large/006tKfTcgy1g1mczri1xaj30xg0mudm9.jpg)