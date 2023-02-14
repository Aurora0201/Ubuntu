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
