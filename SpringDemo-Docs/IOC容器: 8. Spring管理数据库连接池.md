### IOC 容器：基于XML的自定义对象的自动装配 

Spring容器中所有管理跟维护的组建默认都是单实例，而在我们开发中最适合用单实例的就是数据库的连接池的管理！对于数据库的连接获取主要是从数据库连接池中获取，所以保证这个连接池在整个应用中都需要是单实例的话，用Spring的IOC容器来管理是最合适不过的了！ 这里主要简单的来介绍一下如何用Spring来创建管理数据库的连接池！

这里主要是拿C3P0来举例！

##### 在xml中配置一个连接池：

```xml
  <!--在容器中配置一个单实例的连接池-->
    <!--获取连接池就只需要getBean就可以了-->
    <bean id="comboPooledDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="root"/>
        <property name="password" value="123456"/>
        <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/testdb"/>
        <property name="driverClass" value="com.mysql.jdbc.Driver"/>
    </bean>
```

获取这个连接池：

```java
  @Test
    public void test1() throws SQLException {
        DataSource comboPooledDataSource = (DataSource) ctx.getBean("comboPooledDataSource");
        System.out.println("connection: "+comboPooledDataSource.getConnection());

    }
```



##### 使用外部配置文件来存储连接池关键属性：

将所有需要的连接属性都保存在resources目录下的datasource.properties文件中，Spring会为我们读区这个配置文件，并且用doller符来用引用这些属性值

```properties
jdbc.username=root
password=123456
jdbcUrl=jdbc:mysql://localhost:3306/testdb
driverClass=com.mysql.jdbc.Driver 
```

xml配置如下：

```xml
  <!--告诉Spring去加载外部配置文件-->
    <context:property-placeholder location="classpath:dbconfig.properties"/>

    <!--在容器中配置一个单实例的连接池-->
    <!--获取连接池就只需要getBean就可以了-->
    <!--注意配置文件中的username不可以为username，因为username是Spring中的一个默认关键字，如果配置文件中写了username-->
    <!--Spring会获取不到正确的属性值-->
    <!--为了区分，我们在配置文件中写成jdbc.username以示区别-->
    <bean id="propertiesDataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${password}"/>
        <property name="jdbcUrl" value="${jdbcUrl}"/>
        <property name="driverClass" value="${driverClass}"/>
    </bean>
```

