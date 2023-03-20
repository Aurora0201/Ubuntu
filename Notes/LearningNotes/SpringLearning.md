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



### *7.第一个Spring程序

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

+ 实际上，在单例的情况下，对象不是在调用`getBean`方法时创建的，而是在`new ClassXmlApplicationContext("spring.xml")`时就已经创建，调用getBean方法只是从容器中拿到创建好的对象



### 9.启用Log4j2日志框架

启用log4j2日志框架十分简单：

+ 首先是引入依赖

    ```xml
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-slf4j2-impl</artifactId>
        <version>2.19.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.19.0</version>
    </dependency>
    ```

    引入上面两个依赖即可

+ 把配置文件放入根路径下

+ 在需要记录日志的类中创建一个logger，调用logger的记录方法即可

    ```java
    private final Logger logger = LoggerFactory.getLogger(Class<?> clazz)
        
    logger.info|debug|trace...(String s)
    ```

    

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



## 8.Bean专题

### 1.Bean的实例化

在spring中提供了多种实例化bean的方法：

+ 单例模式下，在spring配置文件中配置bean会直接创建对象

    + 这种方法我们在上面已经使用很多次了

+ 通过简单工厂模式实例化bean

    + 首先我们需要一个简单工厂类，提供一个静态方法来得到对象

        ```java
        public class StarFactory {
            public static Star get() {
                return new Star();
            }
        }
        ```

    + 然后编写配置文件，通过`factory-method`来确定通过哪个静态工厂方法来获得对象

        ```xml
        <bean id="star" class="com.framework.spring.bean.StarFactory" factory-method="get"/>
        ```

+ 通过工厂方法模式实例化bean

    + 首先，我们要准备一个工厂方法类，提供一个实例方法来获得对象

        ```java
        public class InstanceStarFactory {
            public Star get() {
                return new Star();
            }
        }
        ```

    + 编写配置文件，配置factory-bean和factory-method

        ```xml
        <bean id="starFactory" class="com.framework.spring.bean.InstanceStarFactory"/>
        <bean id="star1" factory-bean="starFactory" factory-method="get"/>
        ```

        可以看到，我们不用再配置bean类的class属性，这个方法创建的过程是先创建了一个工厂bean，然后通过工厂bean获取到对象

+ 通过工厂Bean接口来获取对象

    + 这个方法的出现是为了简化第三种方法的配置过程，但是我们这次要在工厂具体类中实现`FactoryBean`接口，所以我们在配置文件中就不用在指定factory-bean和factory-method了，下面我们对上面的`InstantiationStarFatory`进行一个改造

        ```java
        public class InstanceStarFactory implements FactoryBean<Star> {
            @Override
            public boolean isSingleton() {
                return FactoryBean.super.isSingleton();
            }
        
            @Override
            public Star getObject() throws Exception {
                return new Star();
            }
        
            @Override
            public Class<?> getObjectType() {
                return null;
            }
        }
        ```

        可以看到，这个接口中有三个方法，其中`isSingleton`是有默认实现的，当这个函数返回true就是单例，否则就是多例，`getObject`就是我们要手动的返回一个对象

    + 然后就是配置文件的编写

        ```xml
        <bean id="star1" class="com.framework.spring.bean.InstanceStarFactory"/>
        ```

        只要这一句话，工厂bean就会自动的创建，只要我们调用getBean方法即可



### *2.BeanFactory和FactoryBean的区别

**BeanFactory：**BeanFactory是Spring IoC容器的顶级对象，翻译为Bean工厂，是生产Bean的地方，本质是**工厂**

**FactoryBean：**FactoryBean翻译过来就是工厂bean，是一个工厂的bean对象，能够辅助spring生产对象的bean，本质是**bean**



在Spring中bean可以分为两类：

+ 普通bean
+ 工厂bean
    + 可以用来生产bean的bean



### 3.FactoryBean的应用

之前我们说过，虽然Date对spring来说是简单类型，但是如果使用value属性赋值需要传入标准的日期格式，十分的麻烦，那么该怎么样才能简化我们的操作呢？

+ 使用FactoryBean对传入的时间字符串进行加工转化为Date类型，这就是FactoryBean的一个简单实际应用



下面是步骤

+ 先创建FactoryBean类，MyTime类用来模拟当我们需要给一个bean注入时间类型的需求

    ```java
    public class DateFactory implements FactoryBean<Date> {
        private String time;
    
        public DateFactory(String time) {
            this.time = time;
        }
        @Override
        public Date getObject() throws Exception {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(time);
        }
    
        @Override
        public Class<?> getObjectType() {
            return null;
        }
    
        @Override
        public boolean isSingleton() {
            return FactoryBean.super.isSingleton();
        }
    }
    
    public class MyTime {
        private Date date;
    
        @Override
        public String toString() {
            return "MyTime{" +
                    "date=" + date +
                    '}';
        }
    
        public void setDate(Date date) {
            this.date = date;
        }
    }
    ```

    这个工厂类中有一个字符串用来储存传入的时间，使用构造方法传入，加工的过程中将时间字符串转换为Date类型并返回

+ 编写配置文件

    ```xml
    <bean id="date" class="com.framework.spring.bean.DateFactory">
        <constructor-arg index="0" value="2002-02-01"/>
    </bean>
    <bean id="myTime" class="com.framework.spring.bean.MyTime">
        <property name="date" ref="date"/>
    </bean>
    ```

    在这个文件中我们首先配置了FactoryBean的信息，并使用构造方法传入了时间字符串，然后在下面的创建了一个MyTimebean模拟Date类型的注入，这里我们直接给ref属性赋值为工厂类生成出的bean



### 4.Bean生命周期之五步

bean的生命周期有五步：

+ bean对象被创建（调用构造方法）
+ bean对象属性赋值
+ bean对象的初始化
+ bean对象的使用
+ bean对象的销毁

其中的初始化`init()`和销毁`destroy()`都要我们自己写，并且在配置文件中指定



下面是步骤

+ 创建类MyBean

    ```java
    public class MyBean {
        private String name;
    
        public void setName(String name) {
            this.name = name;
            System.out.println("constructor done!");
        }
    
        public MyBean() {
            System.out.println("setter done!");
        }
    
        @Override
        public String toString() {
            return "MyBean{" +
                    "name='" + name + '\'' +
                    '}';
        }
    
        public void init() {
            System.out.println("init done!");
        }
    
        public void destroy() {
            System.out.println("destroy done!");
        }
    }
    ```

    我们手动的提供了init和destroy方法

+ 编写配置文件

    ```xml
    <bean id="myBean" class="com.framework.spring.bean.MyBean" init-method="init" destroy-method="destroy">
        <property name="name" value="myBean"/>
    </bean>
    ```

    我们手动的指定了init的方法和destroy方法

+ 编写测试类

    ```java
    public void LifeTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        MyBean myBean = applicationContext.getBean("myBean", MyBean.class);
    
        System.out.println(myBean);
    
        //close application
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }
    ```

    需要注意的是，我们需要手动的关闭spring容器才能激活销毁程序，所以要先对ApplicationContext进行转型

+ 输出结果

    ```
    constructor done!
    setter done!
    init done!
    MyBean{name='myBean'}
    destroy done!
    ```

    可以看到一个bean的生命周期是符合五个步骤的



### 5.Bean生命周期之七步

在前面五步的基础上，我们可以在第三步init()方法执行的前后插入两步，这样bean的生命周期就变为了七步，这个机制也叫做`post processor`--bean的后处理机制：

+ 在上面的例子的基础上，提供一个类实现`BeanPostProcessor`接口，并重写`after & before`方法

    ```java
    public class LogBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("Before done!");
            return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        }
    
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println("After done!");
            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }
    ```

    注意，这个地方不能动原有的代码，只能在`return`语句的上面添加我们想要发生的事件

+ 编写配置文件

    ```xml
    <bean class="com.framework.spring.bean.LogBeanPostProcessor"/>
    ```

    这个类不需要我们主动去调用，所以不用给他命名

+ 测试，沿用上一个例子的代码

    ```
    constructor done!
    setter done!
    Before done!
    init done!
    After done!
    MyBean{name='myBean'}
    destroy done!
    ```

    可以发现，确实在init方法前后多了两个事件



**注意：**这个后处理机制对于同一个xml文件中的bean都是起作用的



### 6.Bean生命周期之十步

十步是在上面七步的基础之上又增加了三个点位：

+ 第一个点位：before方法之前
    + 检查Bean是否实现了Aware相关的接口(BeanNameAware, BeanClassLoaderAware, BeanFactoryAware)，如果实现了就会调用接口中的方法，这些方法的目的是传递一些数据，让我们更方便的使用
+ 第二个点位：before方法之后
    + 检查Bean是否实现了initializationBean接口，如果实现了，则调用接口中的方法
+ 第三个点位：使用Bean之后，销毁Bean之前
    + 检查Bean是否实现了DisposableBean接口，实现了则调用接口中方法



### 7.Bean生命周期特殊情况

其实上面所述的bean生命周期都是建立在单例的情况下的，如果说在多例的情况下--scope="protoType"，一旦客户端获取到bean--init之后，spring容器就不会再对bean的生命周期进行管理了



### 8.bean纳入spring管理

有时候我们想让我们手动创建的bean纳入spring进行管理，该如何操作呢？

+ 我们需要一个DefaultListableBeanFactory类

    ```java
    public void registerTest() {
        MyBean myBean = new MyBean();
        System.out.println(myBean);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("myBean",myBean);
    
        System.out.println(factory.getBean("myBean"));
    }
    ```

    这样我们就完成了一个对象的注册（交给spring容器进行管理）

+ 看一下输出的结果

    ```
    com.framework.spring.bean.MyBean@f5f2bb7
    com.framework.spring.bean.MyBean@f5f2bb7
    ```

    可见是同一个对象



### 9.Bean循环依赖问题

来看一下这个例子：我们定义一个妻子类，一个丈夫类，他们是双向关联的，也就是说其子类中有丈夫类的引用，丈夫类中有妻子类的引用，那么在这种情况下，他们会出现什么问题呢?



**使用set注入的情况**

**Singleton**

+ 先来看单例模式下会出现什么问题，我们在配置文件中对他们进行配置，看看能否正常进行

    ```xml
    <!--    singleton -->
    <bean id="husband" class="com.framework.spring.bean.Husband">
        <property name="wife" ref="wife"/>
    </bean>
    
    <bean id="wife" class="com.framework.spring.bean.Wife">
        <property name="husband" ref="husband"/>
    </bean>
    ```

+ 运行测试显示，这样是没有问题的



**ProtoType**

+ 再来看一下多例情况下，同样是上面的配置但是scope属性为`protoType`，运行一下测试看看

+ 抛出了异常

    ```
    org.springframework.beans.factory.BeanCreationException
    ```

    这证明在多例的情况下，循环依赖是会出错的，但是只要我们把其中一个改成单例的模式，又可以正常的运行了



**分析：**其实在spring中有这样一个机制，在单例的情况下，创建bean和给属性赋值是有两个阶段的，当bean被创建后，就会被“曝光”，就是会让容器知道，已经存在这个类对象了，等待bean创建完后，才会对属性进行赋值，所以就避免了循环依赖问题



**使用构造注入的情况**

这里我们直接说结论，因为构造注入无法实现两个阶段，所以即使在单例的情况下也会出现异常



### *10.Bean的缓存机制

从源码的角度来分析bean的创建

+ 首先我们要知道的是，在spring中对于bean的创建有三级缓存

    ```java
    public class DefaultSingletonBeanRegistry {
        private final Map<String, Object> singletonObjects;
        private final Map<String, Object> earlySingletonObjects;
        private final Map<String, ObjectFactory<?>> singletonFactories;
    }
    ```

    先来解释一下这三个Map集合代表了什么意思

    + singletonObjects：一级缓存，存储了完整单例bean对象，这里的bean对象已经完成了属性的赋值
    + earlySingletonObjects：二级缓存，存储了早期的单例bean对象，这个缓存中的单例bean对象没有赋值
    + singletonFactories：三级缓存，存储了单例工厂对象，这里存储的是创建bean对应的bean工厂对象，每一个单例bean对象都会对应一个单例工厂对象

+ 在创建好一个bean对象后，spring框架要做的是`急切的把他缓存起来为了能解决循环依赖问题`，所以在创建bean对象后，会直接存入缓存中

+ 取用对象时，会先从一级缓存取，取不到就会去二级缓存去，二级缓存取不到的情况下会直接使用三级缓存的工厂对象创建新的对象并且放入二级缓存中，然后从集合中移除工厂对象



将bean对象放入map集合的这个行为可以称为**曝光**



## 9.手写mySpring框架

下面我们从源码的角度来分析一下spring框架的结构，并用自己的理解去复现spring框架：

+ spring框架结构分析，首先来看一下spring框架的结构，我们使用了`ApplicationContext`接口和实现类`ClassPathXmlApplicationContext`，其中ApplicationContext提供了getBean方法，然后通过解析XML文件来生成bean类

+ 先来提供接口，接口中有一个getBean方法

    ```java
    public interface ApplicationContext {
        /**
         * return object by beanName
         * @param beanName the id of bean
         * @return instance of bean
         */
        Object getBean(String beanName);
    }
    ```

+ 然后为了模拟spring容器的管理，我们提供三个类来模拟需要管理的情况，分别是`User, UserDao, UserService`，他们是经典的MVC模式系统

+ 然后准备XML文件

    ```xml
    <beans>
        <bean id="user" class="com.framework.spring.bean.User">
            <property name="name" value="ZhangSan"/>
            <property name="age" value="15"/>
        </bean>
        <bean id="userDao" class="com.framework.spring.bean.UserDao"/>
        <bean id="userService" class="com.framework.spring.bean.UserService">
            <property name="userDao" ref="userDao"/>
        </bean>
    </beans>
    ```

    上面的文件很好的描述了三个类的关系

+ 编写实现类`ClassPathXmlApplicationContext`，到了这一步我们需要使用dom4j来解析XML文件

    ```java
    public class ClassPathXmlApplicationContext{
        //early cache
        private Map<String, Object> singletonObjects;
        
        public void getBean(String beanName){
    		//first part, parse xml, instantiate bean and put it into cache
            
            //second part, parse xml, assign values to properties
        }
    }
    ```


**技术细节：**

+ 在第一部分中，我们使用反射机制创建了对象并放入缓存中--曝光
+ 在第二部分中，我们首先使用了反射机制获取了属性的类型，然后通过类型的判断来给属性赋值，但是对于简单类型和引用类型的赋值大有不同，对于引用类型来说，我们只要在调用方法时，从缓存中取出赋值和被赋值的对象即可，但对于简单类型来说要通过繁琐的类型判断来解决





## 10.Springz注解式开发

注解出现的意义是简化配置文件的操作，spring6倡导全注解式开发



### *1.使用反射机制获取注解

首先来复习一下注解的创建和使用

+ 首先创建一个注解

    ```java
    @Target(Element.Type)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Component{
        String value() default "";
        
        String[] name() ;
    }
    ```

    这样就创建好了一个注解，上面的Target和Retention注解都是元注解--用来标注注解的注解，Target用来指定注解标注的对象是谁--字段，类...，Retention用来代表注解存在的地方，是存在于源码还是存在于字节码，如果说想使用反射机制去读取字节码，还是要使用Retention进行标注

    **注意：**对注解中的value属性进行赋值时，可以不用`属性 = {属性值}`的模式，可以省略`value =`，同时，如果是对数组属性进行赋值时，那么大括号也可以省略，注解可以有默认值

+ 下面使用注解标注一个类，我们在这里提供一个bean类User，模拟当一个类被注解标注时，使用反射机制去读取这个类的注解

+ 下面是使用反射机制读取注解的过程，我们的第一个任务就是只有一个包名的情况下，扫描一个包下的所有类，当类上有注解时，实例化这个类

    ```java
    public class ParseAnnotation {
        private final static Map<String, Object> singletonObjects = new HashMap<>();
        public static void main(String[] args) {
            String packageName = "com.java.bean";
            //turn package name into file path
            String packagePath = packageName.replaceAll("\\.","/");
    //        System.out.println(packagePath);
            URL url = ClassLoader.getSystemClassLoader().getResource(packagePath);
            String path = url.getPath();
            //get file from url
            File file = new File(path);
            File[] files = file.listFiles();
            Arrays.stream(files).forEach(f -> {
                String className = packageName + "." + f.getName().split("\\.")[0];
    //            System.out.println(className);
                try {
                    Class<?> clazz = Class.forName(className);
                    if(clazz.isAnnotationPresent(Component.class)){
                        Component component = clazz.getDeclaredAnnotation(Component.class);
                        String id = component.value();
                        //instantiate the bean if the bean has the annotation
                        Object obj = clazz.getDeclaredConstructor().newInstance();
                        //put into cache
                        singletonObjects.put(id, obj);
                    }
    
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
    
            });
            System.out.println(singletonObjects);
        }
    }
    ```

    首先是我们将报名转化为路径名，然后使用类加载器获取系统类加载器，从而获得类的Url，然后我们从url获得类的文件地址，通过包名的拼接来判断包是否存在注解，如果存在则进行实例化并放入缓存中，这就是spring的实现方法



### 2.Bean的实例化注解

bean的注解有四种：

+ Component
+ Repository
+ Controller
+ Service



下面我们来看一下他们的源码:

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	String value() default "";

}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Repository {

	/**
	 * The value may indicate a suggestion for a logical component name,
	 * to be turned into a Spring bean in case of an autodetected component.
	 * @return the suggested component name, if any (or empty String otherwise)
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

我们会发现这四个注解的源码都是一样的，除了Component，其他三个多了一个注解，别名为Component，这意思非常明显了，其实这四个注解的作用是完全一致的，只不过spring框架为了更好的区分每个层，使用了不同的别名：

+ 最上面的表示层使用`Controller`
+ 中间的业务层使用`Service`
+ 下面的DAO层使用`Repository`
+ 其他的Bean就使用`Component`



### 3.实例化注解的使用

学习了注解的定义之后，那就是注解的使用了，使用注解有以下几点要求：

+ 引入了AOP的依赖
+ 在配置文件中引入context命名空间
+ 在配置文件中指定要扫描的包名
+ 在bean类上使用注解



下面就从配置文件中的步骤讲起：

+ 我们要指定扫描的包名

    ```xml
    <context:component-scan base-package="com.framework.spring.bean"/>
    ```

+ 然后就在bean上使用注解即可

    ```java
    @Component("user")
    public class User {
    
    }
    ```

+ 然后就像之前一样从ApplicationContext中获得Bean实例就可以了



**注意：**在注解中的value属性会作为Bean的id，但是如果缺省的话，spring会给bean取一个默认的名字，就是类名的首字母小写



**多个包的指定方法**

当我们需要指定多个包为扫描的对象时，我们有两种解决方案：

+ 指定多个包名，每个包名用逗号隔开
+ 指定一个相同的父包名



### 4.选择性实例化Bean

假设包下有很多bean，每个bean使用了不同注解进行标注，当我们只想要一部分注解生效该怎么做呢？两种方案

+ 选择的方式，在context标签中添加属性`use-default-filters="false"`

    ```xml
    <context:component-scan base-package="com..." use-default-filters="false">
    	<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository"/>
        <context:include-filter type="annotation" expression="org.sprinframework.stereotype.Contronller"/>
    </context:component-scan>
    ```

    当我们设置属性`use-defautl-filters`为`false`时，所有的注解都会失效，但是当我们在下面include标签内填入的标签都会生效

+ 反选的方式，因为`use-default-filters`的默认属性是`true`，所以我们直接在exclude标签中填入我们不想生效的注解即可 



### 5.bean的注入注解

**简单属性注解**

bean的注入注解之一为`@value`，但是他只能用来注入简单属性，并且只能用在三个地方：

+ 类的属性上
+ 类的构造器形参前
+ 类的set方法上



来看下面的例子

```java
@Value("Bin JunKai")
private String name;
```

```java
@Value("Bin")
public void setName(String name) {
    this.name = name;
}
```

```java
public User(@Value("Bin1") String name) {
    this.name = name;
}
```



**注意：**在属性上使用注解时，即使没有set方法也可以，只有在构造方法的形参前才能加注解



**非简单属性注解**

对非简单属性的注入我们需要两个注解，分别是`@Autowire|@Qualifier`，他们只能被用在下面的地方：

+ 方法上
+ 属性上
+ 参数上
+ 类上
+ 构造器上（只有Autowire）



在属性上使用注解的前提下，来看下面的例子

+ 假如说我们提供了一个Dao的接口，然后提供了一个实现类，这时，我们只要用一个`@Autowire`标注在属性就可以实现属性的自动注入
+ 但是如果我们提供了两个或者更多的实现类，那`@Autowire`显然是不够用的，这个时候就需要`@Qualifier`出场了，在被注入的属性上使用`@Autowire`的前提下，在这个属性上再加上`@Qualifier("id")`来指定需要注入类



**注意：**`@Autowire`使用的是基于类型的自动注入，只有配合了`@Qualifier`才是基于名字的自动注入



### 6.@Resource注解

`@Resource`注解同样能完成非简单类型的注入，那它与`@Autowire`有什么区别呢？

+ Resource注解是JDK扩展包中的一员，而Autowire是spring框架中的一员，所以Resource更有通用性，建议使用Resource注解

+ Resource注解是基于name自动装配的，当我们指定名字后，会根据名字进行装配，如果我们省略了名字，就会根据属性的名字去查找，是否存在与属性名相同的bean，如果存在则直接进行注入，如果不存在才会切换到基于类型的自动装配

+ Resource只能用在属性，setter方法上

+ 使用Resource注解需要引入依赖，如果是spring6，因为spring6只支持Jakarta，所以要引入下面的依赖

    ```xml
    <dependency>
        <groupId>jakarta.annotation</groupId>
        <artifactId>jakarta.annotation-api</artifactId>
        <version>2.1.1</version>
    </dependency>
    ```

    如果是spring5及以下，需要引入javax的依赖

    ```xml
    <dependency>
        <groupId>javax.annotation</groupId>
        <artifactId>javax.annotation-api</artifactId>
        <version>1.3.2</version>
    </dependency>
    ```



### 7.全注解式开发

即使使用了上文我们所学的注解，还是有一样东西没有被替换--spring配置文件，所以这里我们将学习配置注解的使用，主要使用下面的注解：

+ @Configuration：用来指定配置类
+ ComponentScan：用来指定需要扫描的包



**配置注解的使用**

+ 替换掉配置文件的原理其实就是使用一个类来代替配置文件，所以首先我们提供一个配置类，并加上配置类的注解

    ```java
    @Configuration
    @ComponentScan({"cn","com"})
    public class SpringConfig {
    
    }
    ```

+ 对于配置的读取也跟使用配置文件时有所不同

    ```java
    public void classConfigTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Object vip = context.getBean("vip");
        System.out.println(vip);
    }
    ```

    不再使用`ApplicationContext`接口而是改用`AnnotationConfigApplicationContext`接口，使用的实现类也从`ClassPathXmlApplicationContext`改为`AnnnotationConfigApplicationContext`，传入的参数也变成了配置类



## 11.GoF之代理模式



### 1.代理模式初步

**什么是代理？**

+ 假设有一个演员需要完成一个高难度的表演动作，但是它无法完成，但是为了不让观众察觉，它找了一个替身演员来替他完成这个任务
+ 那么上面的这个行为就叫做代理



**Java中代理模式的作用**

+ 当一个对象需要保护的时候，可以考虑使用代理对象去完成这个行为
+ 需要给一个对象的功能进行增强的时候，可以使用代理去增强
+ A对象无法与B对象进行交互时，使用代理模式来解决



**代理三步骤**

+ 创建目标对象`target`
+ 创建代理对象`proxy`
+ 运行代理对象方法

### 2.静态代理

假设现在有一个业务需求，我们需要计算每个函数的执行时间，那么有多少种方法能实现我们的需求，业务中有一个接口和一个实现类：

+ 第一种方法，编写一个子类来继承这个类，重写父类方法时，在调用父类方法的前后编写计时的程序

    ```java
    public class SubClass extends ..{
        public void generate(){
            //counting code
            super.generate();
     		//counting code       
        }
    }
    ```

    上面的程序的缺点有：1.使用了继承关系耦合度很高 2.代码重复，冗余

+ 第二种方法，编写一个接口的实现类，对目标类进行代理，降低了耦合度

    ```java
    public class OrderProxy implements ...{
        private OrderService target;
        //..
    }
    ```

    尽管使用接口编程降低了耦合度，但是仍然没有解决代码冗余的问题



### 3.动态代理

动态代理技术原理是动态的生成字节码，不再需要我们编写具体的实现类，程序员只要对增强的代码进行负责即可，大大提高了程序员测试的效率，目前动态代理有三种实现方式：

+ JDK自带的代理类，只能代理接口，并且需要至少一个实现类
+ CGLIB--(Code Generate Library)，是一个开源项，强大的，高性能，高质量的代码生成库，可以在运行期间扩展Java类与实现Java接口，既可以代理接口，又可以代理类，底层通过继承的方式实现，效率比JDK的代理类高
+ Javassist动态代理技术，是一个，开源，分析，编辑Java字节码的类库



**JDK代理**

先来学习一下的JDK的代理类，先准备下面的类

```java
public interface OrderService {
    void generate();

    String details();

    String modify(String content);
}
```

```java
public class OrderServiceImpl implements OrderService{
    @Override
    public void generate() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("generate done");
    }

    @Override
    public String details() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ZhangSan";
    }

    @Override
    public String modify(String content) {
        return "Modify done" + content;
    }
}
```



下面使用动态代理来计算每个方法执行的时间

+ 使用JDK的动态代理首先要使用的是`Proxy.newProxyInstance()`静态方法，这个方法有三个参数

    + `ClassLoader loader`：这个类加载参数必须传入目标对象的类加载
    + `Class<?>[] interfaces`：这个参数代表目标对象实现的接口，是一个数组
    + `invocationHandler h`：调用处理器，这个一个接口，我们必须编写一个实现类并在里面编写增强代码

+ 下面就是编写调用处理器的实现类了，我们可以发现这个接口中只有一个方法`invoke()`，并且里面有三个参数

    + `Object proxy`：代理对象，一般来说不会使用

    + `Method method`：目标对象的方法

    + `Object[] args`：目标对象方法的参数

        也就是说，我们只要在invoke方法中调用目标对象的方法，并且在目标对象方法的前后添加增强代码即可

+ 下面是代理类的实现

    ```java
    public static void main(String[] args) {
        //create target program
        OrderService target = new OrderServiceImpl();
        //create proxy program
        OrderService orderService = (OrderService) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new TimeInvocationHandle(target)
        );
        orderService.generate();
        String details = orderService.details();
        String modify = orderService.modify("123");
    }
    ```

    ```java
    public class TimeInvocationHandle implements InvocationHandler {
        private Object target;
    
        public TimeInvocationHandle(Object target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //enhancement code here
            Object retValue = method.invoke(target, args);
            
            return retValue;
        }
    }
    ```

**注意：**如果说，我们想要调用目标方法，还是需要将目标对象传入调用处理器，也就是`target`，我们这里通过构造器传入了对象



**CGLIB代理**

既能代理接口，又能代理类，因为是通过子类继承的方式，所以被代理的类不能被final修饰



**Javassist代理**

在MyBatis的章节中已经学习过



## 12.面向切面AOP编程	

### 1.AOP的初步

**什么是AOP？**

+ AOP（Aspect Oriented Programming）面向切面编程，是一种编程技术，AOP是对OOP的延伸
+ AOP一般是那些跟业务逻辑不挂钩，但是重复使用的概率比较高的代码，比如数据库的事务操作



**交叉业务**

+ 一般一个系统当中都会有一些系统服务，如：日志，事务，安全管理等，这些与业务逻辑无关的系统服务被称为**交叉业务**，而这些交叉业务都是通用的，如果我们在每个业务中都去重复的编写，会造成代码的冗余，耦合度变高



**一句话总结AOP**

+ 将与核心业务无关的代码抽取出来，形成一个独立的组件，然后以横向交叉的方式应用到业务流程中的过程叫做AOP



**AOP的优点**

+ 代码复用性强
+ 代码易于维护
+ 使开发者更专注于业务逻辑



### 2.AOP七大术语

AOP中存在七大术语：

+ **连接点 JoinPoint**
    + 在整个的执行过程中，可以织入切面的地方，方法的前后，抛出异常的前后
+ **切点 PointCut**
    + 在执行过程中，真正织入切面的方法
+ **通知 Advice**
    + 通知又叫增强，就是负责增强的代码，根据所处的位置不同又有下面几种叫法
        + 在方法前，前置通知
        + 在方法后，后置通知
        + 前后都有，环绕通知
        + 抛出异常的语句前，异常通知
        + 在finally子句中，最终通知
+ **切面 Aspect**
    + 切点 + 通知
+ 织入 Weaving
    + 把通知应用到目标对象上的过程
+ 代理对象 Proxy
    + 目标对象织入后产生的对象
+ 目标对象 Target
    + 被织入的对象



### 3.切点表达式

切点表达式用来定义通知往哪些方法上切入



**语法格式**

```
execution([访问控制修饰符] 返回值类型 [全限定类名]方法名(参数列表) [异常])
```

+ 访问修饰控制符
    + 可选
    + 缺省就是4个权限都包括
    + 写public就是只包括公开的方法
+ 返回值类型
    + 必填项
    + `*`就是返回类型任意
+ 全限定类名
    + 可选项
    + `..`表示当前包已经子包下的所有类
    + 缺省表示所有类
+ 方法名
    + 必填项
    + `*`表示所有方法
    + `set*`表示所有set方法
+ 参数列表
    + 必填项
    + `()`表示没有参数的方法
    + `(..)`表示参数类型和个数随意的方法
    + `(*)`只有一个参数的方法
    + `(*, String)`第一个类型随意，第二个类型是String的方法
+ 异常
    + 可选项
    + 缺省表示任意异常



**例子**

```
execution(public * com.powernode.mall.service.*.delete*(..))
#service包下所有以delete开始的方法
```

```
execution(* com.powernode.mall..*(..)))
#mall包下所有的方法
```

```
execution(* *(..))
#所有类的所有方法
```



### 4.Spring中的AOP

spring框架对于AOP的实现有下面三种方式：

+ **Spring结合AspectJ框架实现的AOP，基于注解的方式**
+ **Spring结合AspectJ框架实现的AOP，基于XML配置文件的方式**
+ Spring自己实现的AOP，基于XML配置文件的方式

​	

### 5.AOP注解式开发

**准备环境**

+ 引入所需的依赖

    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.0.5</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-aspects -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aspects</artifactId>
        <version>6.0.5</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    ```

+ 在spring配置文件中引入context和aop命名空间，并设置扫描的包，开启自动代理

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xmlns:aop="http://www.springframework.org/schema/aop"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    	<context:component-scan base-package="com.framework.spring"/>
        <aop:aspectj-autoproxy proxy-target-class="true"/>
    </beans>
    ```

    其中的`<aop:aspectj-autoproxy>`代表自动代理开启，属性值proxy-target-class有两个值，false和true，false是默认值，代表使用JDK代理，true则代表强制使用GCLIB代理



**添加前置通知**

+ 编写Userservice类，提供一个切点

    ```java
    @Service
    public class UserService {
        public void login() {
            System.out.println("user login!");
        }
    
        public void logout() {
            System.out.println("user logout!");
        }
    }
    ```

+ 编写切面，织入通知

    ```java
    @Component
    @Aspect
    public class LogAspect {
        @Before("execution(* com.framework.spring.service..*())")
        public void enhance() {
            System.out.println("enhancement done!");
        }
    }
    ```

    首先我们要把所有的类交给spring来管理，也就是使用注解进行标注，对于切面来说，我们还需要额外的增加一个注解`@Aspect`，当添加这个注解时，spring就会自动的将这个类作为一个切面，然后编写通知，假如我想要添加前置通知，需要用到`@Before`注解，里面添加切点表达式，用来匹配目标类中的方法



**通知的类型**

通知包括下面几种

+ 前置通知：@Before
+ 后置通知：@AfterReturning
+ 环绕通知：@Around
+ 异常通知：@AfterThrowing
+ 最终通知：@After



**通知的运行顺序**

通知的运行顺序如下

+ 一般情况下的运行顺序，前环绕 -> 前置 -> 后置 -> 后环绕
+ 如果出现了异常，那么就会直接进入**异常通知**并且后面的通知都不会执行，除了**最终通知**



**切面的顺序**

当我们有多个切面时，我们如何去组织切面的顺序呢？

+ 使用`@Order`注解，我们可以为注解分配一个数字，数字越小，优先级越高



**通用切点表达式**

当我们的切点都相同时，可以自己定义一个通用切wsdasd////点表达式：

+ 首先在切面内定义一个方法，方法名随意，方法体中不需要内容，加上注解`@PointCut(execution(..))`，使用时，只需要在表达式中调用方法名接口，当同时使用多个自定义通用切点时，每个切点方法使用`||`隔开

    ```java
    @Pointcut("execution(* com.framework.spring.service..*())")
    public void generic() {
    
    }
    
    @Before("generic()")
    public void beforeAdvice() {
        System.out.println("before");
    }
    ```

    

**连接点 JoinPoint**

但我们对目标方法进行织入时，可以选择一个连接点，其实我们的方法可以有一个形参`ProceedingJoinPoint`，它可以作为方法中的一个连接点，我们可以通过他做下面的事情：

+ 执行方法 `joinPoint.proceed()`
+ 获取方法的签名 `jointPoint.getSignature()`

```java
@Around("generic()")
public void around(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("around before");
    joinPoint.proceed();
    joinPoint.getSignature();
    System.out.println("around after");
}
```



**全注解式开发**

与Spring一样，我们使用一个类来代替配置文件

```java
@Configuration
@ComponentScan("com.framework.spring")
@EnableAspectAutoProxy(proxyTargetClass = true)
public class SpringConfig{
    
}
```



## 13.Spring对Junit的支持

虽然我们之前一直在使用Junit进行单元测试，但是实际上Spring框架对Junit进行了支持，为了方便我们的测试，使用注解式测试会使得单元测试更加方便



**Junit4的支持**

+ 引入依赖

    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>6.0.6</version>
        <scope>test</scope>
    </dependency>
    ```

+ 创建测试类

    ```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:spring.xml")
    public class AOPTest {
        @Autowired
     	private UserService userService;
        @Test
        public void IoCTest(){
            userService.login();
        }
    }
    ```

    可以看到，我们不再每次都要创建ApplicationContext对象，也不用getBean，直接使用基于类型的自动装配即可，极大的方便了了我们代码调试的效率，但是需要注意的点是，在测试类的上面要加上两个注解`RunWith(SpringJUnit4ClassRunner.class), @ContextConfiguration("classpath:spring.xml")`，才能正常运行，而且只支持配置文件



**JUnit5的支持**

+ 引入依赖

    ```xml
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.9.2</version>
        <scope>test</scope>
    </dependency>
    ```

+ 创建测试类

    ```java
    @ExtendWith(SpringExtension.class)
    @ContextConfiguration("classpath:spring.xml")
    public class JUnit5Test {
        @Autowired
        private UserService userService;
        @Test
        public void test1() {
            userService.logout();
        }
    }
    ```

    这次与上面不一样的是

    + Test注解使用的junit-jupiter的包
    + 不再使用RunWith而是使用ExtendWith



## 14.Spring集成MyBatis框架



**引入依赖**

需要引入的依赖有：

+ spring-context
+ spring-jdbc
+ spring-test
+ mysql-connector-java
+ mybatis
+ mybatis-spring
+ druid
+ junit



**前期准备工作**

+ 准备数据库表
+ 创建包
    + com.framework.sm.service
    + com.framework.sm.mapper
    + com.framework.sm.pojo
    + com.framework.sm.service.impl



**初步工作**

+ 提供Account类
+ 提供AccountMapper接口
+ 提供AccountService接口
+ 提供AccountServiceImpl实现类



**配置文件**

虽然说MyBatis大部分的配置文件都可以迁移至Spring的配置文件，但是关键的setting属性还是需要用到MyBatis的核心配置文件，所以最好还是创建MyBatis的配置文件

+ 创建MyBatis核心配置文件，启用MyBatis的标准日志格式

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <configuration>
        <settings>
            <setting name="logImpl" value="STDOUT_LOGGING"/>
        </settings>
    </configuration>
    ```

+ 创建jdbc连接配置

    ```properties
    jdbc.driver=com.mysql.cj.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/mybatis
    jdbc.username=root
    jdbc.password=root1234
    ```

+ 创建spring核心配置文件:

    + 配置需要扫描的包
    + 引入外部配置文件（数据库连接配置）
    + 配置数据源
    + 配置`SqlSessionFactoryBean`工厂bean
    + 配置`MapperScannerConfigurer`mapper文件扫描
    + 配置`DataSourceTransactionManager`数据源事务管理器
    + 配置`transactionManager`事务管理注解

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
        <context:component-scan base-package="com.framework.ss"/>
        <context:property-placeholder location="jdbc.properties"/>
    
        <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
            <property name="driverClassName" value="${jdbc.driver}"/>
            <property name="url" value="${jdbc.url}"/>
            <property name="username" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>
        </bean>
    
        <bean class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource"/>
            <property name="configLocation" value="mybatis-config.xml"/>
            <property name="typeAliasesPackage" value="com.framework.ss.pojo"/>
        </bean>
    
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.framework.ss.mapper"/>
        </bean>
    
        <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"/>
        </bean>
    
        <tx:annotation-driven transaction-manager="txManager"/>
    </beans>
    ```

+ 将AccountServiceImpl纳入spring管理并启用事务，属性自动装配

    ```java
    @Service("accountService")
    @Transactional
    public class AccountServiceImpl implements AccountService {
    
        @Autowired
        AccountMapper accountMapper;
     	//...   
    }
    ```

+ 测试阶段

    ```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:spring.xml")
    public class SSTest {
        @Autowired
        AccountService accountService;
        @Test
        public void transferTest() {
            accountService.transfer("binjunkai","zhangsan",100);
        }
    }
    ```

    

**主配置文件引入副配置文件**

在大型的项目中，mapper和service会非常多，一般来说会一个mapper对应一个xml，一个service对应一个xml，所以我们需要在主配置文件中引入这些配置文件

```xml
<import resouce="common.xml"/>
```



