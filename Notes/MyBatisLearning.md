[TOC]

# 1.MyBatis

## 1.MyBatis入门

### 1.JDBC的不足？

+ SQl语句是写死在Java代码中的，违背了OCP原则
+ 传值十分繁琐，结果集封装麻烦



### 2.ORM思想

+ ORM(Object-Relational Mapping)是一种编程技术，它将关系数据库的数据映射到对象上。它允许开发人员使用面向对象的方法来操作数据库，而不需要编写大量的SQL代码

![ORM](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/ORM.png)



### 3.了解MyBatis

MyBatis本质

+ MyBatis本质上就是对JDBC的封装，通过MyBatis完成CRUD，负责持久化层，是一个持久化层架构
+ MyBatis本来是Apache的一个开源项目iBatis，后来迁移到Google Code并更名为MyBatis，2013年迁移到GitHub
+ MyBatis框架是一个的ORM框架，MyBatis也是一个半自动的ORM框架，因为MyBatis中的SQL语句还是需要程序员自己编写
+ Hibernate框架是一个全自动的ORM框架，它的SQL语句可以自己生成



MyBatis的特点

+ 灵活的SQL映射：MyBatis可以自动生成SQL，也可以自定义SQL语句。用户可以结合XML文件或注解来实现SQL映射。
+ 接口式编程：MyBatis通过接口和XML配置文件来实现数据库访问，避免了硬编码的SQL语句。
+ 动态SQL：MyBatis支持动态SQL，可以根据不同的条件生成不同的SQL语句。
+ 缓存机制：MyBatis提供了一个简单的缓存机制，可以有效提高查询性能。
+ 多种数据库支持：MyBatis支持多种关系型数据库，如MySQL、Oracle、Microsoft SQL Server等。
+ 简单易用：MyBatis的配置和使用方法都比较简单，易于理解和上手。
+ 插件机制：MyBatis支持插件机制，可以通过插件扩展功能。

这些特点使得MyBatis在Java持久层开发中非常方便和实用，是很多开发人员的首选



### 4.第一个MyBatis程序开发

+ 首先准备数据库表：汽车表t_car:
    + id bigint primary key auto_increment
    + car_num varchar
    + brand varchar
    + guide_price decimal
    + product_time char
    + car_type varchar
+ 随便在数据库中插入两条数据
+ 引入依赖
    + MyBatis依赖
    + mysql-connector依赖
+ 编写MyBatis核心配置文件：`mybatis-config.xml`
    + 实际上这个文件名不一定写为`mybatis-config`，但是一般约定叫做这个名字
    + 文件位置也是不固定的，但是一般约定放在`resources`文件夹下
    + 主要是配置数据库连接信息等
+ 编写`XxxxMapper.xml`文件
    + 在这个文件中专门编写SQL语句
    + 一般来说一个表会对应一个Mapper文件
    + 在`mybatis-config.xml`文件中添加`mapper`
+ 编写mybatis程序
    + 在MyBatis中负责执行SQL语句的对象是SqlSession，他是Java程序和数据库之间的一次会话，想要获取SqlSession对象需要首先获取SqlSessionFactory对象，然后生成SqlSession对象，但是获取SqlSessionFactory对象需要首先获取SqlSessionFactoryBuilder
    + SqlSessionFactoryBuilder --> SqlSessionFactory --> SqlSession
+ 第一个程序中的细节
    + MyBatis中的SQL语句可以省略`;`
    + Resources.getResourceAsStream方法，凡是遇到加载resource资源时，一般情况下都是从根路径下查找
    + 资源文件一般放在放在类路径中，可以提高程序的可移植性



### 5.MyBatis中的事务管理机制

在第一个MyBatis程序中，我们可以发现，我们对于数据库的修改是需要我们手动进行提交的，所以MyBatis是默认开启了事务机制

+ 在mybatis-config.xml文件中，我们可以通过以下的配置进行mybatis的事务管理

    + `<transactionManager type="JDBC">`

+ type属性有两个

    + JDBC
    + MANAGED

+ 使用`JDBC`时，其实是默认使用了JDBC中的默认事务代码，在事务的过程中就会创建`JdbcTransaction`对象进行事务管理

    ```java
    SqlSession ss = SqlSessionFactory.openSession(); // == conn.setAutoCommit(false)
    ss.commit(); // == conn.commit()
    ```

    + 如果我们使用了`openSession(true)`，实际上就会使用JDBC的默认--自动提交事务，但是这种方式是不建议的，因为没有开启事务

+ 使用`MANAGED`，是让MyBatis不再负责事务管理，会交给其他容器来处理，如`spring`



### 6.日志管理

MyBatis集成了日志组件，让我们调试起来更方便

下面是常见的日志组件：

+ SLFJ4
+ LOG4J
+ LOG4J2
+ STDOUT_LOGGING



使用STDOUT_LOGGING日志框架

+ MyBatis框架已经实现了STDOUT_LOGGING这个标准，我们直接开启即可，只需要在`mybatis-config.xml`文件中的`<settings>`中添加下面的内容

    ```xml
    <setting name="logImpl" value="STDOUT_LOGGING">
    ```



使用SLF4J日志框架

+ 使用MyBatis中没有集成的日志框架时，第一步肯定是要引入依赖，这里我们使用`logback`框架，这个框架实现了`SLF4J`这个标准，所以首先在maven中引入`logback`依赖
+ 然后引入`logback`所需的配置文件，这个文件必须叫做`logback.xml | logback-test.xml`，不能是其他名字，并且，这个文件必须放到根路径下

----

## 2.使用MyBatis完成CRUD

### 1.insert操作

首先来回顾一下在JDBC中是怎么完成的

+ 首先编写SQL语句

    ```java
    String sql = "insert into t_user(id,username,pwd) values(?,?,?)";
    ps = conn.preparedStatement(sql);
    
    ps.setString();
    ps.executeUpdate();
    ```

+ 可以看到SQL语句中，有`?`作为占位符，我们需要通过`setString`方法来替换占位符



在MyBatis中，这个流程就和JDBC有所不同了，有两种方式

+ 第一种，使用Map传值

    + 首先在`XxxMapper.xml`中编写SQL语句

        ```xml
        <insert id="insertCar">
        	insert into t_car(id,car_num,brand,guide_price,product_time,car_type)
            values(#{carPrice},#{brand},#{guidePrice},#{productTime},#{carType})
        </insert>
        ```

    + 然后开始编写MyBatis代码

        ```java
        Map<String, Object> map = new HashMap<>();
        map.put("carNum", "1005");
        map.put("brand", "BYD");
        map.put("guidePrice", 30.0);
        map.put("productTime", "2024-01-01");
        map.put("carType", "Electronic");
        
        int count = sqlSession.insert("insertCar", map);//map is passed in as a parameter
        ```

    + 运行代码测试，运行成功，从这个例子中我们不难发现，在MyBatis中作为占位符的是`#{}`，括号里面需要我们填入的是map中的key，同时把map作为参数传入insert方法，这样就能实现对SQL语句的修改

+ 第二种，使用pojo，bean传值

    + 同样的在配置文件中编写SQL语句然后注册，但是先不填括号内的东西

    + 编写一个bean类，提供`setter,getter,toString`方法，有参和无参构造方法

        ```java
        public class Car {
            private Long id;
            private String brand;
            private Integer carNum;
            private Double guidePrice;
            private String productTime;
            private String carType;
            public Car() {}
            public Car(Long id, Integer carNum, String brand, Double guidePrice, String productTime, String carType) {
                this.id = id;
                this.carNum = carNum;
                this.brand = brand;
                this.guidePrice = guidePrice;
                this.productTime = productTime;
                this.carType = carType;
            }
            //setters and getters...
        }
        ```

    + 然后填写配置文件中的占位符

        ```xml
        <insert id="insertCar">
            insert into t_car(car_num,brand,guide_price,product_time,car_type)
            values(#{carNum},#{brand},#{guidePrice},#{productTime},#{carType})
        </insert>
        ```

    + 然后编写MyBatis代码

        ```java
        sqlSession.insert("insertCar", new Car(null ,1006, "BYD-1", 35.0, "2022-01-01", "Electronic"));
        ```

    + 测试代码，运行成功，从这个例子中我们知道，这次在括号内需要填入的是我们传入的bean的属性值



从上面两个例子，我们已经大致了解了使用MyBatis实现insert语句的方法，但是还有一点疑问，为什么占位符内要填的是属性？

+ 这里直接给出结论，占位符中填写的字符与属性无关，而是与get方法有关，MyBatis实际上使用了反射机制来调用get方法，从而获取传入的bean的值，我们在括号内填入的字符其实就是`getXxx`去掉`get`后把`Xxx`的首字母小写后得到的字符串`xxx`，所以使用bean类进行传值时，要正确的提供get方法



### 2.delete操作

+ 首先在配置文件中编写SQL语句

    ```xml
    <delete id="deleteById">
        delete from t_car where id = #{id}
    </delete>
    ```

+ 然后编写MyBatis代码

    ```java
    SqlSession sqlSession = SessionUtil.getSqlSession();
    sqlSession.delete("deleteById",10);
    sqlSession.commit();
    ```

+ 测试，运行成功



这里可以说的是，当通配符中只有一个时，括号内可以随意，但是最好还是见名知意



### 3.update操作

+ 先编写SQL语句

    ```xml
    <update id="updateCar">
        update t_car set 
            id = #{id}
            car_num = #{carNum}
            brand = #{brand}
            guide_price = #{guidePrice}
            product_time = #{productTime}
            car_type = #{carType}
        where id = #{id}
    </update>
    ```

+ 编写代码

    ```java
    sqlSession.update("updateCar", new Car(11L, 1007, "BYD-H", 25.0, "2022-12-15", "Electronic"));
    ```

+ 运行测试，成功



### 4.select操作

只查询一条记录

+ 同样先编写SQL语句

    ```xml
    <select id="selectById" resultType="com.javaframework.mybatis.bean.Car">
        select
            id,
            car_num carNum,
            brand,
            guide_price guidePrice,
            product_time productTime,
            car_type carType
        from t_car where id = #{id}
    </select>
    ```

+ 然后编写代码

    ```java
    Object obj = sqlSession.selectOne("selectById",1);
    ```

+ 运行测试成功



查询多条记录

+ 编写SQL语句

    ```xml
    <select id="selectAll" resultType="com.javaframework.mybatis.bean.Car">
        select
            id,
            car_num carNum,
            brand,
            guide_price guidePrice,
            product_time productTime,
            car_type carType
        from t_car
    </select>
    ```

+ 编写代码

    ```java
    List<Object> cars = sqlSession.selectList("selectAll");
    cars.forEach(car -> System.out.println(car));
    ```

+ 运行测试成功



通过上面两个例子，我们大致了解了select操作如何通过MyBatis框架去完成，但是还有几个细节要补充：

+ 首先是使用`select`语句时，我们要先定义返回的类型，这个类型必须是完整的参考--包名.类名
+ Java和MySQL中分别有自己的命名规范，当我们得到返回值时，MyBatis框架在把查询到的数据封装为bean的时候，会使用set方法，所以当出现不同的名字时，会导致存入`null`值，所以在编写SQL语句时，我们要重命名列名，保持和bean中的一致



对于整个MyBatis框架来说，每个CRUD操作都有属于自己的Id，但是当我们在两个`Mapper`中有同名的情况时，这个时候就需要命名空间--`namespace`来进行区分了

+ 在每个`Mapper`的标签中都有一个属性`namespace`，这个标签就是来定义下面的SQL语句是属于哪个命名空间的，所以当不同的命名空间中，即使存在相同的Id，只要通过命名空间进行调用就可以区分，完整的调用方法是`namespace.id`



---

## 3.MyBatis核心配置文件

### 1.MyBatis配置信息

在MyBatis中，`mybatis-config.xml`文件除了配置数据库连接信息，还能影响MyBatis的行为，配置文档的顶层结构如下

+ [configuration（配置）](https://mybatis.net.cn/configuration.html)          

    - [properties（属性）](https://mybatis.net.cn/configuration.html#properties)

    - [settings（设置）](https://mybatis.net.cn/configuration.html#settings)

    - [typeAliases（类型别名）](https://mybatis.net.cn/configuration.html#typeAliases)

    - [typeHandlers（类型处理器）](https://mybatis.net.cn/configuration.html#typeHandlers)

    - [objectFactory（对象工厂）](https://mybatis.net.cn/configuration.html#objectFactory)

    - [plugins（插件）](https://mybatis.net.cn/configuration.html#plugins)

        - environments（环境配置）

            + environment（环境变量）                  

                - transactionManager（事务管理器）

                - dataSource（数据源）

    - [databaseIdProvider（数据库厂商标识）](https://mybatis.net.cn/configuration.html#databaseIdProvider)

    - [mappers（映射器）](https://mybatis.net.cn/configuration.html#mappers)



### 2.environment环境配置

在`mybatis-config.xml`中，我们可以看到有`environments|environment`标签，这个标签是用来配置数据库的环境的，一个数据库就对应一个环境

+ 首先来看大标签`environments`，他有一个属性`defalut`，这个属性表示默认使用哪个环境，也就是说当不指定使用哪个环境的情况下使用默认环境
+ 下来有一个标签`environment`，有一个属性`id`，这个属性表示这个环境的id
+ 在下来有一个标签`transactionManager`，[上文](#5.MyBatis中的事务管理机制)提过
+ 下来有一个标签`dataSource`，这个是数据源的意思，所有为程序提供connection对象的，叫做数据源
    + 他实际上是JDK中的规范--javax.sql.DataSource，只要实现了这个接口的，就得到了数据源，在MyBatis中，数据库的连接池就可以叫做数据源，常见的数据源有：
        + druid
        + c3p0
        + dbcp
    + 这个标签有一个属性`type`，用来指定数据源类型，就是指定用什么方式获取Connection对象，有三个取值：
        + POOLED 使用MyBatis集成的连接池
        + UNPOOLED 不使用连接池，每次都新建连接对象
        + JNDI 集成第三方的连接池
    + JNDI是一个规范--Java命名目录接口，大部分的web容器都实现了这个接口：Tomcat,Jetty,WebLogic,WebSphere



### 3.properties属性配置

在`configuration`标签下有一个`properties|property`标签，这个是可以用来配置一些必须的属性，其实就是Java中的property集合

第一种用法

+ 在configuration标签下，创建下面的配置信息

    ```xml
    <configuration>
    	<properties>
            <!-- 添加配置信息 -->
        	<property key="name" value="root"/>
            <property key="pwd" value="root1234"/>
        </properties>
        
        <environment>
        	...
            ${name} 
            ${pwd}
        </environment>
        
    </configuration>
    ```

+ 从上面的例子不难看出，当我需要使用重复使用的属性时，可以通过配置属性来复用，让程序看起来更加简洁，获取`value`的方法就是`${key}`

第二种方法

+ 这次我们直接在`resources`文件夹中编写一个`.properties`文件，然后编写下面的配置信息

    ```xml
    <configuration>
    	<propeties resource="xxx.properties"/>
    </configuration>
    ```

+ 同样，我们就可以在配置文件中引入我们刚刚设置好的配置信息



需要补充的点

+ 如果存在多个资源文件，访问属性时最好使用`${文件名.key}`

---

## 4.手写MyBatis框架



要了解MyBatis框架是如何运作的，以及他的原理，我们最好就是手写一个MyBatis的框架，我们姑且称为GodBatis

### 1.解析XML文件

要实现MyBatis框架，第一步就是实现怎么去使用Java代码去解析XML文件，因为我们程序的配置信息都会写在配置文件中，这里我们使用dom4j框架来解析XML文件

第一步

+ 导入dom4j的依赖，在maven中导入下面两个依赖才能让dom4j框架正确运行

    ```xml
    <dependency>
        <groupId>org.dom4j</groupId>
        <artifactId>dom4j</artifactId>
        <version>2.1.3</version>
    </dependency>
    
    <dependency>
        <groupId>jaxen</groupId>
        <artifactId>jaxen</artifactId>
        <version>1.2.0</version>
    </dependency>
    ```



第二步

+ 要使用dom4j框架，我们需要了解怎么去解析XML文件，在XML中，我们一般使用`xpath`来对XML中的标签进行匹配，下面给出一些实际案例
    + 选择根节点：`/`
    + 选择所有元素：`//*`
    + 选择元素名为 `book` 的所有元素：`//book`
    + 选择元素名为 `book`，并且有属性 `category` 值为 `web` 的所有元素：`//book[@category='web']`
    + 选择元素名为 `book`，并且有子元素 `title` 的所有元素：`//book[title]`
    + 选择元素名为 `book`，并且有子元素 `title`，并且 `title` 的文本值包含字符串 `Java` 的所有元素：`//book[title[contains(text(), 'Java')]]`
+ 完整的代码

```java
// 创建 SAXReader 对象
SAXReader reader = new SAXReader();
// 获取流
InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("example.xml");
// 解析 XML 文件，一个document就相当于一个XML文件
Document document = reader.read(is);
//获取root元素
Element root = document.getRootElement();

//从根路径获取标签，比如说我想要默认的environment标签
//先获取environments的default属性值
String xpath = "//enviroments";
Element environments = (Element)root.selectNode(xpath);
String defaultEnvironment = environments.attributeValue("default");

//获取default environment
xpath = "//environment[@id='" + defaultEnvironment + "']";
Element environment = (Element)root.selectNode(xpath);
```



### 2.解析mybatis-config.xml

那么首先就是要解析我们的核心配置文件，这里面我们主要目的就是要获取我们数据库连接配置，事务的管理，使用的连接池类型，SQL语句Mapper等等



### 3.解析Mapper.xml

解析映射文件的主要目的就是为了获得SQL语句，同时将SQL语句中的`#{}`替换为`?`，因为最终使用的就是，这里就需要用到字符串的替换和正则表达式

---

## 5.在web应用中使用MyBatis

### 1.准备数据库表

本次实验模拟银行转账，使用`mvc`数据库下的`t_act`表，t_act表有三个字段`id bigint primary key auto_increment,actno varchar(255),balance decimal(10,2)`



### 2.使用maven搭建web环境

首先肯定是先搭建项目的环境，本次开发web项目需要使用到的组件：mybatis，mysql-connector，servlet-api，tomcat9

+ 新建module-->Maven Archetype-->webapp，等待生成完成后会得到一个webapp的基本框架，但是需要注意的是，`web.xml`文件的版本过低，需要复制一段较新的替换

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0">
    
    </web-app>
    ```

+ 添加依赖，在`pom.xml`文件中添加以下的依赖

    ```xml
    <!--    mysql dependency-->
        <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
        <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>8.0.30</version>
        </dependency>
        <!--    mybatis dependency-->
        <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.11</version>
        </dependency>
    <!--    logback dependency-->
        <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
          <version>1.2.11</version>
        </dependency>
    <!--    servlet dependency-->
          <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
          <dependency>
              <groupId>javax.servlet</groupId>
              <artifactId>javax.servlet-api</artifactId>
              <version>4.0.1</version>
              <scope>provided</scope>
          </dependency>
    ```

+ 刷新maven项目后初步搭建完成



### 3.搭建MVC架构

本次项目的包名为`com.java.framework`，并以此为基础搭建项目结构

+ 在framework包下新建以下的包
    + util 工具包
    + exception 异常包
    + web 表示层包
    + service 业务逻辑层包
        + impl 实现包
    + dao 数据访问对象包
        + impl 实现包
    + bean 数据封装包



层与层之间使用接口调用降低耦合性



### 4.编写业务代码

从用户的角度出发开始编写代码，养成良好的习惯

+ 首先从`index.jsp`开始编写，只需要提供表单提交的功能即可
+ 表单的数据会提交到表示层，也就是`AccountServlet`中进行数据处理，然后调用service层的接口，进行业务逻辑的处理
+ 先编写service层的接口`AccountService`，然后编写实现类，实现`transfer(fromActno,toActno,money)`方法
+ service层会去访问数据库进行数据的查询操作，数据库的查询操作要通过`DAO`，所以先编写`DAO`类的接口`AccountDao`，然后编写实现类，实现`select,update`方法
+ 实现的过程中，我们需要通过`mybatis`连接数据库，所以开始编写`SqlSessionUtil`，提供两个方法`getSqlSession,close`，其中要使用`ThreadLocal`来存储`SqlSession`对象来实现事务管理的功能，`close`负责`SqlSession`对象的关闭和线程解绑
+ 实现查询时，要通过`Account`对象来实现，所以这里先编写`Account`类，封装对应的三个字段，提供`setter,getter,toString`方法
+ 然后是`mybatis`的配置，首先创建`mybatis-config.xml,AccountMapper.xml`文件，然后在核心配置文件中配置数据库的连接信息，在`Mapper`中编写SQL语句
+ 最后进行项目的测试即可

### 5.项目总结

本次项目中出现了诸多的问题

+ Mapper中的SQL语句错误问题，会导致报错
+ 转账功能的失败，可能存在的问题在于，一个是首先我们在`util`中使用了`ThreadLocal`，那么在`DAO`中我们不能把`SqlSession`关闭
+ 在`mybatis`中传参只有两种方式，传入一个bean类或者一个map集合，只有在参数只有一个的情况下才能直接传值



可以改进的地方

+ DAO类中的代码重复率高，如何解决这个问题？
    + 使用动态代理

---

## 6.在MyBatis中使用代理机制

### 1.代理机制概述

在MyBatis中，实际上实现了代理机制，这意味着我们不用再手动的去实现DAO类，我们只需要编写DAO的接口然后让MyBatis去实现即可，下面简述代理机制的原理



Javassist包

在MyBatis中实际封装了一个包Javassist，这个包是利用Java的反射机制去动态的生成代码实际上是一个代码生成工具，这样我们就不用去实现接口而是让MyBatis去动态的生成接口的实例，大大减少了程序员的工作量



### 2.Javassist初步

下面通过简单的代码来了解Javassist的使用，Javassist的使用总结起来只有下面几步：

+ 获取类池
+ 创建实现类
+ 创建接口
+ 往实现类中添加接口
+ 获取接口中的方法
+ 实现接口中的方法
+ 将实现的方法添加到实现类
+ 将实现类转换为类对象
+ 从类对象创建实例



下面给出部分代码

创建方法`public Object generate(Class daoInterface)`，并获取接口和类

```java
//get class pool
ClassPool pool = ClassPool.getDefault();
//get implement class
CtClass ctClass = pool.makeClass(daoInterface.getName() + "Impl");
//get interface
CtClass ctInterface = pool.makeInterface(daoInterface.getName());
//implement the interface
ctClass.addInterface(ctInterface);
```



完成上面的步骤后就可以开始实现接口中的抽象方法了

```java
//implement the abstract methods
//get the methods
Method[] methods = daoInterface.getDeclaredMethods();
Arrays.stream(methods).forEach(method -> {
    //get parameters
    Parameter[] parameters = method.getParameters();
    try {
        //splice code
        StringBuilder methodCode = new StringBuilder();
        methodCode.append("public ");
        //get type of return
        methodCode.append(method.getReturnType().getName() + " ");
        //get method name
        methodCode.append(method.getName());
        //add arguments
        methodCode.append("(");
        for (int i = 0; i < parameters.length; i++) {
            methodCode.append(parameters[i].getType().getName() + " arg" + i);
            if (i != parameters.length - 1) {
                methodCode.append(",");
            }
        }
        methodCode.append(")");
        //splice method body
        methodCode.append("{");
        methodCode.append("System.out.println(123);");
        methodCode.append("}");
        //make method
        CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
        //add method to ctClass
        ctClass.addMethod(ctMethod);
    } catch (CannotCompileException e) {
        throw new RuntimeException(e);
    }
});

```



完成上面的步骤后，就可以创建类的实例了

```java
//create instance
Object obj = null;
try {
    //cast ctClass to Class
    Class<?> aClass = ctClass.toClass();
    //create instance
    obj = aClass.newInstance();
} catch (CannotCompileException | InstantiationException | IllegalAccessException e) {
    throw new RuntimeException(e);
}
return obj;        
```



以上就是Javassist的简单实现了，在MyBatis中的实现其实更为复杂，通过一个enum类来识别xml文件中的各种标签，然后通过id来实现SQL语句



### 3.使用MyBatis中的代理机制

在MyBatis的实际使用中，其实并不用像上面那么麻烦，因为MyBatis中已经封装好了，使用也非常的简单，但是有以下几点要求：

+ `Mapper.xml`文件中的`namespace`必须为`DAO`接口的全限定名称
+ 接口中的`方法名`必须为`SQL语句的id`
+ 以后存放接口的包不叫做`DAO`了，而是叫做`mapper`，这个是一般约定



使用方法也非常简单，在`service`层中使用`getMapper`方法即可

```java
private XxxMapper xxxMapper = SqlSession.openSession().getMapper(XxxMapper.class);
```

获得Mapper代理对象后，我们就能和之前一样使用CRUD了

---

## 7.MyBatis中的一些技巧

### 1.关于占位符的使用

虽然在上面的所有例子中我们都使用了`#{}`来作为占位符，但是实际上`${}`也可以作为占位符，下面来阐述他们两者的区别

+ 如果使用了`#{}`作为占位符，那么实际上就会使用`PreparedStatement`作为SQL语句的载体
+ 如果使用了`${}`作为占位符，那么就会使用`Statement`作为SQL语句的载体，有SQL注入的风险



那么在什么时候使用这些占位符合适呢，下面就有几种情况

+ 一般情况下，尽量使用`#{}`实际上来说都可以应付我们的需求
+ 当我们需要往SQL语句中传入保留字时需要使用`${}`，如我们需要实现查询结果的排序需要传入`desc`和`asc`这个两个参数，
+ 需要动态的修改查询的表名需要使用`t_${abc}`
+ 需要批量删除，使用`in(${1,2,3})`子句时



### 2.别名机制

在写SQL查询语句时，每次都要指定返回的类型，并且要写全限定名称，这十分的繁琐，所以MyBatis中有别名机制，下面做一个简单的演示

```xml
<configuration>
    <typeAlias>
    	<!-- 指定别名为car -->
        <typeAlias type="com.java.framework.bean.Car" alias="car"/>
        <!-- 自动别名，此时car的任意大小写形式(car/Car/cAr/caR)都是合法的别名-->
        <typeAlias type="com.java.framework.bean.Car"/>
        <!-- 包别名，包下的所有类都可以用类简名-->
        <typeAlias type="com.java.framework.bean"/>
    </typeAlias>
</configuration>
```

从上面的例子可以看出，别名机制可以大大方便我们的开发



### 3.mapper的配置

在之前的配置中，我们一般都会手动的配置mapper，但是在这一节中我们会介绍一种新的形式

```xml
<mappers>
	<package name="com.java.framework.mapper"/>
</mappers>
```

上面的语句表示，会从指定的包下寻找与接口名字相同的`mapper`文件，但是使用这种方式有下面的要求：

+ 首先接口的名字必须与mapper文件的名字一致
+ 然后他们的位置必须都位于同一个包下



把他们放在同一个包下的方式也很简单，在`resources`文件夹下，创建`com/java/framework/mapper`的文件夹，并将mapper文件都放到文件夹中，这里还有一个小技巧，在`resources`中创建多重文件夹时要使用`create directory -> com/java/framework/mapper`



### 4.IDEA配置模板文件

在IDEA中我们可以设置一些模板文件，类似于VSC中的用户代码片段，可以十分快捷的生成所我们所需的配置文件配置的方式也十分简单，下面提供给两种方法：



+ 在项目栏的任意位置==alt + ins== -> Edit File Templates 即可进入模板文件编辑页面，点击`+`即可设置自定义的代码
+ 进入Settings -> Editor -> File and Code Templates 即可进入模板文件编辑界面



### 5.查询时参数设置

在上面的例子中我们都知道，我们能通过实例对象和map集合给SQL语句传入参数，下面就对上面进行一些补充：

+ 其实在SQL语句的标签中有一个`paramType`属性是用来指定参数的类型的，但是MyBatis框架中有自动推测参数的机制，是通过接口中的参数类型推测出来的，我们可以[查询](https://mybatis.net.cn/configuration.html#typeAliases)它的取值

+ 在占位符的大括号里其实不止可以指定`key|field`，还可以指定`javaType=String,jdbcType=VARCHAR`

    ```xml
    <insert id="" resultType="" paramType="">
    	select * from t_student = where id = #{id,javaType=Long,jdbcType=bigint}
    </insert>
    ```

    

使用多参数

在上面我们只出现了传入一种参数的情况，那么我们想要传入多个参数该怎么办呢？

+ MyBatis中提供了多参数的方法，底层中会自动创建一个map，key为字符串`argx|paramx`，value为我们传入的值

使用方法为

+ 在占位符中输入的字符串为`arg0,arg1,... or param1,param2,...`，那么MyBatis就会自动的将对应的参数传入

+ 或者使用注解的方式，在接口的抽象方法中使用注解来确定对应的名字

    ```java
    Student selectByNameAndSex(@Param("name") String name, @Param("sex") Character sex)
    ```

    注意，使用注解后，在占位符中只能写对应的名字



注解源码解析

+ 在使用注解后，实际上我们会往底层传入一个数组`Object[] args`，数组中存储我们设置的名字，在底层中还会有一个map，key是下标，value是args的元素，如果是使用默认的参数，则还会有另一个map负责处理
+ 实际上底层就是使用了map来处理参数的占位符



### 6.查询时返回结果

+ 在上面的例子中我们在查询时返回的结果一般只有两种情况，返回一个类的实例或者返回一个List，并且遇到了一些问题，比如说当数据库中的字段名与bean类的属性名没有一一对应时，我们需要对返回的列名进行重命名，下面我们会对上面的例子进行一定的补充

+ 虽然在上文只使用了实例和List两种方式，但是List既可以接收一个也可以接收多个结果，所以以后我们一般不会再使用单个实例的情况



使用Map接收

使用map接收时需要在标签中指定属性`resultType="map"`

+ 当我们没有合适的bean类来接收时，可以让MyBatis返回一个`Map<String, Object>`，map的key为字段名，value为值，但是此时只能接收一条结果

+ 返回`List<Map<String, Object>>`，在上一条的基础上可以接收多条结果

+ 使用`Map<Type, Map<String, Object>>`，返回一个大Map，同样可以接收多条结果，但是使用时有几个条件

    + 在接口中使用注解来指定第一个Map的key为表中的哪个字段，一般为表中的主键
    + 第一个Map的key的类型需要手动输入，与指定的key类型相对应
    + 第一个Map的key必须为表中的字段

    ```java
    @MapKey("id")
    Map<Long,Map<String, Object>> selectByXxx();
    ```



结果映射

在上面我们已经提到过，我们会使用别名来让查询后的结果来与bean类属性一一对应，在实际的开发中我们可以使用多种方式来解决这个问题：

+ 使用列别名
+ 使用resultMap
+ 开启自动驼峰命名映射



使用resultMap

+ 先在`mapper`文件中定义`resultMap`标签，并定义属性与数据库列名的映射

+ 然后在每个SQL语句的标签中使用`resultMap`属性来确定使用哪个Map映射

    ```xml
    //id -> identifier  type -> the bean class
    <resultMap id="carMap" type="Car">
        //usually, the primary key will be set in id tag
    	<id property="id" column="id"/>
        //Set in the result tag only when the column name does not correspond to the property
        <result property="carNum" column="car_num"/>
    </resultMap>
    
    <select id="selectByXxx" resutlMap="carMap">
    	select * from t_car
    </select>
    ```



使用驼峰命名自动映射

使用这种方法前提是：

+ 属性名遵循Java命名规范，首字母小写，后面每个单词的开头大写
+ 列名遵循SQL命名规范，全部小写，单词直接使用`_`隔开

在配置文件中开启该功能，在核心配置文件中如下配置

```xml
<settings>
	<setting name="mapUnderscoreToCamelCase" value="true"/>
</settings>
```

----

## 8.动态SQL

想象这样一个需求，在我们购买商品时，可以设置多种过滤条件，那么在数据库的视角，就是可以动态的设置多个where条件，动态SQL就是为了解决这种类似的问题而出现的

### 1.if标签

+ 首先我们来了解一下if标签，if标签的形式如下

    ```xml
    where
        <if test="brand != null and brand != ''">
            ...
        </if>
    ...
    ```

    if标签中有一个属性`test`，当test的值为true时，if标签内的语句会被拼接在外部的语句中

+ 使用时需要注意

    + test中的变量为属性名或者`argx|paramx`
    + 条件之间使用`and|or`连接

### 2.where标签

+ 先来了解一下where子句

    ```xml
    <where>
    	<if test="">...</if>
        <if test="">and ...</if>
        <if test="">and ...</if>
    </where>
    ```

    where标签一般都会配合if标签使用，当有一个或者多个if标签成立时才会自动的在前面加上`where`

+ 使用时需要注意

    + 当有多个if标签时，我们需要在标签中语句的前面加上`and|or`来连接多个条件，此时where标签可以自动的将前面多余的`and|or`删除，但是无法将后面多余的`and|or`删除，所以我一般在前面加上`and|or`

### 3.trim标签

+ 下面是trim标签的形式

    ```xml
    <trim prefix="" suffix="" prefixOverrides="" suffixOverrides="">
    	
    </trim>
    ```

+ 可以注意到，trim标签有四个属性`prefix,suffix,prefixOverrides,suffixOverrides`，这里简单介绍一下

+ prefix属性，prefix属性的值会被添加到整段话的前面

+ prefixOverrides属性，会将开头的与前缀相同的字段去除

### 4.set标签

+ 下面是set标签的形式

    ```xml
    <set>
        <if test="">...,</if>
        <if test="">...,</if>
        <if test="">...,</if>
    </set>
    ```

+ set标签一般用于update语句中，与if语句配合使用，会动态的生成`set`，当我们想对其中的一些列修改时，其他列传入的null值会被过滤掉，同时还会自动去掉末尾的`,`

### 5.控制标签

流程控制标签包含三个

+ choose
+ when
+ otherwise

+ 他们的结构如下

    ```xml
    <choose>
    	<when test=""></when>
        <when test=""></when>
        <when test=""></when>
        <otherwise></otherwise>
    </choose>
    ```

    他们就像Java中的流程控制语句，只会选择一个分支执行

### 6.foreach标签

foreach标签的作用就相当于是遍历循环，对数组或者集合进行遍历，每次生成一个数字填入，并且会自动的去掉多余的末尾符号

+ 下面是foreach的结构

    ```xml
    delete form t_car where id in (
    	<foreach collection="" item="id" separator="," open="" close="">
    		#{id}		
    	</foreach>
    )
    ```

+ 可以看到，foreach标签中有五个属性，分别是

    + collection
        + 指定需要遍历的集合或者数组，在没有使用`@Param`的情况下，只能使用`array|arg0`，或者使用自己指定的名字
    + item
        + 遍历元素的名字，与下面占位符中的名字一致
    + separator
        + 用来分隔每次遍历的元素的符号
    + open
        + foreach语句以什么开始
    + close
        + foreach语句以什么结束



## 9.高级映射

在实际应用中，我们使用的数据库不会只有一张表，一般都会使用多表进行联合查询，之前都是一个对象对应一个数据记录，现在可能会存在下面两种情况：

+ 多对一
+ 一对多



先来讲一下`多对一`的情况

现在我们给出两张表，t_stu(sid,sname,cid)，t_clazz(cid,cname)，t_stu表中的`cid`是外键

![1](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/1-1677240142225-1.png)

因为我们查询的是学生，有很多个学生是一个班级的，学生与班级是多对一的关系，那么我们应该在Student中设置一个Clazz属性，用来表示一个学生的班级，那么实现这种多对一的关系我们三种方式：

123123123
