[TOC]

# 1.Spring

## 1.Spring环境准备

本次spring的学习基于spring6，spring6要求最低的JDK版本为JDK17

+ 安装JDK17版本
+ 修改Maven的编译版本为JDK17



## 2.Spring启示录

### 1.OCP开闭原则

什么是OCP开闭原则？

+ 七大开发原则中最基本的原则
+ 是最核心，最基本的原则，其他六个原则都是为这个原则服务



开闭原则的的核心是什么？

+ 只要你在扩展系统功能的时候，没有修改之前写好的代码，那么就符合OCP原则
+ 反之，扩展系统功能时修改了以前写好的代码，那么这个设计就是失败的，违背了OCP原则
+ 这就是对扩展开放，对修改关闭



### 2.DIP依赖倒置原则

先来看看我们之前编写的MVC架构的项目结构

![1](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/1-1677478801391-1.png)



在上面的项目中，UserAction依赖了具体的UserServiceImpl，而UserServiceImpl依赖了具体的UserDaoImplForMySQL这有什么问题？

+ 就目前来说，上层的运作是依赖下层的，凡是上依赖下的，就违背了依赖倒置原则



如何解决这个问题，依赖倒置原则的核心是什么？

+ 面向接口编程，面向抽象编程



### 3.IoC控制反转思想

什么是控制反转？

+ IoC(Inversion of Control)



反转了什么事情？

+ 不再在程序中使用硬编码的方式来new对象
+ 不再在程序中使用硬编码的方式来维护对象间的关系



### 4.Spring的引入

Spring架构实现了IoC的思想，或者说Spring是实现了IoC思想的一种容器，它可以完成下面的事情：

+ 帮你new对象
+ 帮你维护对象之间的关系



实现控制反转的方式有很多，其中最重要的就是：

+ 依赖注入Dependency Injection(DI)



实现依赖注入有两种常用方式：

+ set注入
+ 构造器注入



依赖注入中的“依赖”和“注入”是什么意思？

+ 依赖指的是对象A与对象B之间的关系
+ 注入是一种手段--数据传递行为，使得这两个对象之间能产生关系



**控制反转**是思想，**依赖注入**是控制反转思想的体现



### 5.Spring的八大模块

下面是Spring的八大模块

![1](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/1-1677481713160-1.png)



### 6.Spring的特点

轻量

+ Spring本身极小，能在1MB以内的jar包发布，处理Spring的开销可以忽略不计
+ 非侵入式，Spring应用中的对象不依赖Spring中的类

控制反转

+ Spring通过一种称作控制反转(loC)的技术促进了松耦合。当应用了loC，一个对象依赖的其它对象会通过被动的方式传递进来，而不是这个对象自己创建或者查找依赖对象。你可以认为loC与NDI相反——不是对象从容器中查找依赖，而是容器在对象初始化时不等对象请求就主动将依赖传递给它

面向切面

+ Spring提供了面向切面编程的丰富支持，允许通过分离应用的业务逻辑与系统级服务(例如审计 (auditng)和事务(transaction)管理)进行内聚性的开发。应用对象只实现它们应该做的——完成业务逻辑——仅此而已。它们并不负责(甚至是意识)其它的系统级关注点，例如日志或事务支持。

容器

+ Spring包含并管理应用对象的配置和生命周期，在这个意义上它是一种容器，你可以配置你的每个bean如何被创建——基于一个可配置原型(prototype)，你的bean可以创建一个单独的实例或者每次需要时都生成一个新的实例——以及它们是如何相互关联的。然而，Spring不应该被混同于传统的重量级的EJB容器，它们经常是庞大与笨重的，难以使用

框架

+ Spring可以将简单的组件配置、组合成为复杂的应用。在Spring中，应用对象被声明式地组合，典型地是在一个XML文件里。Spring也提供了很多基础功能(事务管理、持久化框架集成等等)，将应用逻辑的开发留给了你。



所有Spring的这些特征使你能够编写更干净、更可管理、并且更易于测试的代码。它们也为Spring中的各种模块提供了基础支持



### 7.第一个Spring程序

在上面的Spring介绍中，我们知道spring能帮助我们生成bean类，下面是具体的操作步骤：

+ 引入依赖，在spring6以上的版本，maven中央仓库已经把组件分开，这次我们引入`spring-context`依赖

    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.0.5</version>
    </dependency>
    
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    ```

+ 编写bean类

    ```java
    /**
     * @author binjunkai
     * @since 1.0
     */
    public class User {
    }
    
    /**
     * @author binjunkai
     * @since 1.0
     */
    public class UserDaoImplForMySQL {
    }
    
    ```

+ 编写spring配置文件，使用IDEA自带的模板即可

    ```java
    <bean id="userBean" class="com.framework.spring.bean.User"/>
    <bean id="userBeanDao" class="com.framework.spring.dao.impl.UserDaoImplForMySQL"/>
    ```

    **需要注意的是**：这个文件一般约定叫做`spring|bean`，也可以随意取名，在文件中我们使用`bean`标签来指定我们编写的类，属性id为这个bean的唯一标识符，属性class是类的全限定引用

+ 使用spring创建对象

    ```java
    //get spring application context
    ApplicationContext applicationContext = new ClassXmlApplicationContext("spring.xml");
    
    //new object 
    Object userBean = applicationContext.getBean("userBean");
    ```
    
    在上面的代码中，`ApplicationContext`是一个接口，其实就是spring容器，`ClassXmlApplicationContext`是它的一个实现类，需要传入spring配置文件，然后就是创建对象，使用`getBean`方法即可创建对象，参数是bean的id



### 8.第一个程序中的细节

创建对象时底层是如何运作的？

+ 实际是使用了Java的反射机制来创建对象，调用了对象的无参构造方法，如果没有无参构造，那么会报错



创建好的对象存储到了哪里？

+ 存到了一个`Map<String, Object>`集合中



配置文件可以有多个么？

+ 可以，`ClassXmlApplicationContext`可以有多个参数，只要传入正确的路径即可



使用getBean方法时，传入了错误的（不存在的）id，会怎么样？

+ 会抛出异常



想调用对象的方法，可以怎么做？

+ 直接对得到的对象进行强转
+ getBean传入参数`Bean.class`



关于ApplicationContext的细节

+ 这个接口的超级父接口是BeanFactory，同时也是spring容器的顶级接口，也就是说spring底层IoC容器实际使用了工厂模式
+ IoC容器的实现：XML解析 + 工厂模式 + 反射机制



对象创建的时机

+ 实际上，对象不是在调用`getBean`方法时创建的，而是在`new ClassXmlApplicationContext("spring.xml")`时就已经创建，调用getBean方法只是从容器中拿到创建好的对象



### 9.启用Log4j2日志框架



## 3.注入初步

### 1.set注入初步

依赖注入的第一个实现方法，使用set方法注入

+ 首先准备两个类

    ```java
    /**
     * Bean
     */
    public class UserDao {
        public void insert() {
            System.out.println("Insert a item into DB!");
        }
    }
    
    public class UserService {
        private UserDao userDao;
    
        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }
    
        public void insert() {
            userDao.insert();
        }
    }
    ```

    我们使用service来调用Dao实现类

+ 编写spring配置文件

    ```xml
    <bean id="userDaoBean" class="com.framework.spring.dao.UserDao">
    
    </bean>
    
    <bean id="userServiceBean" class="com.framework.spring.service.UserService">
        <property name="userDao" ref="userDaoBean"/>
    </bean>
    ```

    在bean标签的下一级标签`<property>`标签，就是实现set注入的关键，先来解释一下这个标签是什么意思：首先我们在UserService的bean标签下编写了一个property标签，这代表我们要对UserService类中的`属性`使用set方法赋值，标签中的`name`填写的必须是`set方法的名字除去"set"剩下的字段然后首字母小写得到的字段`，`ref`填写的必须是`需要赋值的类的beanid`，一般情况下使用标准的set方法时，name填写属性名即可

+ 编写测试类

    ```java
    public void DITest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDaoBean = applicationContext.getBean("userDaoBean", UserDao.class);
        UserService userServiceBean = applicationContext.getBean("userServiceBean", UserService.class);
    
        userServiceBean.insert();
    }
    ```



**总结**：实现set注入使用的是类中的set方法，一般情况下使用IDEA生成的符合bean规范的set方法时会方便很多



### 2.构造方法注入初步

上面学习了set注入，下面学习构造方法注入

+ 对UserService类进行一些改动

    ```java
    public class UserService {
        private UserDao userDao;
        private VipDao vipDao;
    
        public UserService(UserDao userDao, VipDao vipDao) {
            this.userDao = userDao;
            this.vipDao = vipDao;
        }
    
        public void insert() {
            userDao.insert();
            vipDao.insert();
        }
    }
    ```

    我们移除了set方法，并提供了有参构造方法

+ 修改spring配置文件

    ```xml
    <bean id="userDaoBean" class="com.framework.spring.dao.UserDao"/>
    <bean id="vipDaoBean" class="com.framework.spring.dao.VipDao"/>
    
    <bean id="userServiceBean" class="com.framework.spring.service.UserService">
        <constructor-arg index="0" ref="userDaoBean"/>
        <constructor-arg index="1" ref="vipDaoBean"/>
    </bean>
    
    <bean id="userServiceBean1" class="com.framework.spring.service.UserService">
        <constructor-arg name="userDao" ref="userDaoBean"/>
        <constructor-arg name="vipDao" ref="vipDaoBean"/>
    </bean>
    ```

    可以看到我们有两种配置构造方法注入的方式，使用`index`属性时，表示的是构造器参数的下标，使用`name`属性时，使用的是构造参数的名字

+ 使用上面的测试代码



**总结**：貌似`name`属性已经弃用，优先使用`index`



## 4.set注入专题

### 1.注入内部bean

其实刚刚我们学习的set注入方法就是注入外部bean，现在我们来学习一下注入内部bean：

+ 编写spring配置文件

    ```xml
    <bean id="userServiceBean2" class="com.framework.spring.service.UserService">
        <property name="userDao">
            <bean class="com.framework.spring.dao.UserDao"></bean>
        </property>
    </bean>
    ```

    这就相当于在bean的内部再定义了一个bean，不过这种方法不常用，认识即可



### 2.简单类型的注入

在上面的学习中我们使用的都是引用数据类型，现在我们先来学习对于spring框架来说，什么是简单数据类型：

+ 所有的基本数据类型和包装类
+ 字符串类型
+ 数字类型
+ 枚举类型
+ Class类型
+ Date类型
+ Temporal时区类型
+ URI类型
+ URL类型
+ Locale语言类型

**以上的类型对于spring架构来说都是简单类型，但是在实际开发中一般不会把Date类型单做简单类型**



下面来学习简单类型的注入

+ 先编写spring配置文件

    ```xml
    <bean id="simpleTypeBean" class="com.framework.spring.simple.SimpleType">
        <property name="a" value="1"/>
        <property name="b" value="2"/>
        <property name="c" value="c"/>
        <property name="character" value="C"/>
        <property name="clazz" value="java.lang.String"/>
        <property name="string" value="ZhangSan"/>
    </bean>
    ```

    可以看到，跟引用数据类型不同的是，不再使用`ref`属性进行注入，而是直接使用`value`属性进行注入



**特别的**，对于Date类型来说，虽然它可以使用value的方式进行注入，但是需要使用标准的Date格式，非常的麻烦，所以在实际的开发中，我们一般把他当做引用数据类型来处理



### 3.数组类型的注入

下面来学习数组类型的注入：

+ 先准备一个类

    ```java
    public class DaYe {
        private String[] aiHao;
        private Woman[] women;
    
        @Override
        public String toString() {
            return "DaYe{" +
                    "aiHao=" + Arrays.toString(aiHao) +
                    ", women=" + Arrays.toString(women) +
                    '}';
        }
    
        public void setAiHao(String[] aiHao) {
            this.aiHao = aiHao;
        }
    
        public void setWomen(Woman[] women) {
            this.women = women;
        }
    }
    ```

+ 首先是简单类型的数组的注入

    + 编写配置文件

        ```xml
        <bean id="DaYeBean" class="com.framework.spring.dao.DaYe">
                <property name="aiHao">
                    <array>
                        <value>smoke</value>
                        <value>drink</value>
                        <value>haircut</value>
                    </array>
                </property>
            </bean>
        ```

+ 引用数据类型的数组注入

    + 编写配置文件

        ```xml
        <bean id="woman1" class="com.framework.spring.dao.Woman">
            <constructor-arg index="0" value="WangJie"/>
        </bean>
        
        <bean id="woman2" class="com.framework.spring.dao.Woman">
            <constructor-arg index="0" value="ZhangYi"/>
        </bean>
        
        <bean id="DaYeBean" class="com.framework.spring.dao.DaYe">
            <property name="aiHao">
                <array>
                    <value>smoke</value>
                    <value>drink</value>
                    <value>haircut</value>
                </array>
            </property>
            <property name="women">
                <array>
                    <ref bean="woman1"/>
                    <ref bean="woman2"/>
                </array>
            </property>
        </bean>
        ```

**总结**：基础类型数组的注入比较简单，直接使用array和value标签注入即可，引用数据类型则先要注册bean，然后通过array和ref标签来注入



### 4.List和Set类型的注入

List和Set使用set注入与数组的注入大差不差：

+ 准备类

    ```java
    public class ListAndSet {
        private List<String> name;
        private Set<Woman> women;
    
        @Override
        public String toString() {
            return "ListAndSet{" +
                    "name=" + name +
                    ", women=" + women +
                    '}';
        }
    
        public void setName(List<String> name) {
            this.name = name;
        }
    
        public void setWomen(Set<Woman> women) {
            this.women = women;
        }
    }
    ```

+ 编写配置文件

    ```xml
    <bean id="woman1" class="com.framework.spring.dao.Woman">
        <constructor-arg index="0" value="WangJie"/>
    </bean>
    
    <bean id="woman2" class="com.framework.spring.dao.Woman">
        <constructor-arg index="0" value="ZhangYi"/>
    </bean>
    
    <bean id="listAndSet" class="com.framework.spring.dao.ListAndSet">
        <property name="name">
            <list>
                <value>ZhangSan</value>
                <value>ZhangSan</value>
                <value>ZhangSan</value>
            </list>
        </property>
        <property name="women">
            <set>
                <ref bean="woman1"/>
                <ref bean="woman2"/>
            </set>
        </property>
    </bean>
    ```

    可以看到，之前使用array的地方切换成了list|set，同样的基础类型使用value标签，引用类型使用ref标签



### 5.Map类型的注入

下面学习Map类型的注入：

+ 在上面的类中添加

    ```java
    private Map<String, String> map;
    private Properties properties;
    ```

+ 在配置文件中补充

    ```xml
    <property name="map">
        <map>
            <entry key="123" value="123"/>
        </map>
    </property>
    <property name="properties">
        <props>
            <prop key="123">123</prop>
        </props>
    </property>
    ```

    发现与上面的List和Set大差不差，只要记住这种模式即可



### 6.null值与空字符串的注入

null与空字符串的注入十分简单：

+ null有两种注入方式

    + 如果不在配置文件中对属性进行注入，则默认为null

    + 如果想要手动的注入为null，则输入下面的内容

        ```xml
        <property name="">
            <null/>
        </property>
        ```

+ 空串的注入也有两种

    + 直接将value值设置为空串即可

    + 手动注入的输入下面的内容

        ```xml
        <property name="">
            <value/>
        </property>
        ```

        

### 7.特殊字符的注入

当我们遇到了XML中的特殊字符，`& < > / ? :`等字符时，有下面两种方法：

+ 使用规定好的转义字符，需要查表

+ 使用`<![CDATA[特殊字符]]>`

    ```xml
    <property name="name">
    	<value><![CDATA[2 < 3]]></value>
    </property>
    ```



## 5.注入的简化

### 1.P命名空间注入

p命名空间的出现是为了简化set注入的操作：

+ 要使用p命名空间，要先在配置文件中添加命名空间

    ```xml
    xmlns:p="http://www.springframework.org/schema/p"
    ```

+ 对属性的注入，`p:属性名="属性值"`

    ```xml
    <bean id="name" class="com..." p:name="ZhangSan" p:age="3" p:birth-ref="time"/>
    <bean id="time" class="java.util.Date"/>
    ```

**需要注意：**p命名空间仍然是基于set注入的，必须提供set方法



### 2.C命名空间注入

c命名空间注入的出现是为了简化构造方法注入：

+ 要使用c命名空间首先要引入命名空间

    ```xml
    xmlns:c="http://www.springframework.org/schema/c"
    ```

+ 对属性的注入是，`c:_下标="值"`

    ```xml
    <bean id="name" class="com..." c:_0="ZhangSan" c:_0-ref="beanId".../>
    ```

**需要注意：**c命名空间是基于构造注入的



### 3.util命名空间让配置复用

util命名空间的出现是为了简化配置操作，使配置信息能复用：

+ 首先要引入util命名空间

    ```xml
    xmlns:util="http://www.springframework.org/schema/util"
    
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
    http://www.springframework.org/schema/util  http://www.springframework.org/schema/util/spring-util.xsd"
    ```

+ 下面是一个简单的例子

    ```xml
    <util:properties id="properties">
        <prop key="123">123</prop>
    </util:properties>
    
    <bean id="property" class="com.framework.spring.dao.DataSource">
        <property name="properties" ref="properties"/>
    </bean>
    ```

    这样就实现了配置文件的复用



### 4.基于名字的自动装配

在上面我们学习了手动的注入，下面学习自动装配：

+ 只要在配置文件中这样写

    ```xml-dtd
    <bean id="userDao" class="com.framework.spring.dao.UserDao"/>
    <bean id="vipDao" class="com.framework.spring.dao.VipDao"/>
    
    <bean id="userService" class="com.framework.spring.service.UserService" autowire="byName">
    ```

**注意：**自动装配也是基于set注入实现的，而且此时bean的id也不能随便写，被注入的bean的名字(id)要与set方法名`去掉set剩下的部分首字母小写得到的字符串`一致，所以在符合命名规范的前提下，bean的id是类名的首字母小写即可



### 5.引入外部属性文件

要引入外部的属性文件，需要使用context命名空间，下面是步骤：

+ 先引入context命名空间

    ```xml
    xmlns:context="http://www.springframework.org/schema/context"
    
    xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd"
    ```

+ 在配置文件中使用下面的内容引入外部文件

    ```xml
    <context:property-placeholder location="jdbc.properties"/>
    <bean id="dataSource" class="com.framework.spring.dao.DataSource">
        <property name="driver" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    ```

    在context:property-placeholder标签中，location属性是从根目录下进行读取的，与MyBatis中一致，都是使用$占位符来读取properties文件中的元素

**注意：**在win系统中，使用`${username}`可能会引用环境变量中的变量，所以我们在属性文件中加上前缀来区分命名



## 6.Bean的作用域

### 1.Bean的单例和多例

在上面的学习过程中，我们一直学习的是单例的bean，也就是说自始至终都只有一个对象被创建，现在我们学习多例的情况：

+ 要使用多例，我们只需要在bean标签中将scope属性设置为`protoType`即可

    ```xml
    <bean id="dataSource" class="com.framework.spring.dao.DataSource" scope="prototype">
        
    </bean>
    ```

    这样在每次调用getBean方法时，都会创建一个新的对象

**注意：**在单例的情况下，在创建ApplicationContext时对象就会被创建并放入容器中，但是在多例的情况下，只有在调用getBean方法时，才会创建对象，spring默认的情况下是单例



### 2.Bean的其他作用域

bean除了单例和多例，其实还有其他一些域，包括但不限于`request, session...`，但是他们一般都只用于web框架中



## 7.GoF之工厂模式

GoF中有23种设计模式，这些设计模式并不只对于Java一门语言，而是对于所有的编程来说都是十分有意义的，当然除了GoF23种设计模式之外，还有一些其他的设计模式比如JavaEE的DAO，MVC设计模式，本节我们将主要学习GoF中的工厂设计模式

### 1.工厂设计模式

spring底层架构大量使用了工厂创建模式，工厂设计模式是为了解决对象创建问题的，工厂模式主要：

+ 简单工厂模式：**不属于23种设计模式之一，简单工厂模式是工厂方法模式的一种特殊实现，又叫做静态工厂方法模式**
+ 工厂方法模式：是23种设计模式之一
+ 抽象工厂模式：是23种设计模式之一



### 2.简单工厂模式

简单工厂模式的角色包括三个:

+ 抽象产品角色
+ 具体产品角色
+ 工厂类角色



优点：

+ 消费端-客户，生成端-工厂，他们两者是分离的，客户不需要关心生产的细节

缺点

+ 如果说我们需要对生成的类进行扩展，那么工厂类的代码也需要修改，违背了OCP原则
+ 工厂类承担的责任太大，不能出现问题，一旦出现问题，系统无法正常运行



### 3.工厂方法模式

工厂方法模式角色有四个：

+ 抽象产品角色
+ 具体类产品角色
+ 抽象工厂类角色
+ 具体工厂类角色

它与简单工厂模式的不同就是改变了一个工厂生产所有产品的现状，一个产品对应一个工厂



优点：

+ 扩展性高，当我们想要生产新的产品时，只要添加新的工厂就行了，符合OCP原则
+ 屏蔽了产品的细节，调用者只关心产品的接口

缺点

+ 每次增加产品时，都会增加产品类和工厂类，使得类中的工厂数量成倍的增加，一定程度上增加了系统的复杂程度，这不是什么好事



