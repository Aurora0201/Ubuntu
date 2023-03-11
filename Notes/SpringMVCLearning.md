[TOC]

# 1.SpringMVC

## 1.第一个SpringMVC程序

本程序基于spring官网的`Serving Web Content with Spring MVC`

### 1.生成一个demo

进入[spring项目生成](https://start.spring.io/)

+ 勾选Java语言，版本是17
+ 勾选Maven项目
+ 添加依赖
    + Spring Web
    + Thymdleaf
    + Spring Boot DevTools
+ 点击Generate，会自动下载一个包，解压缩后导入IDAE



### 2.项目的构建

**创建一个Controller**

+ 使用Spring的方式来创建web项目，HTTP请求都是被Controller来处理的，我们可以使用注解`@Controller`来快速的定义，在下面的例子中，我们就使用了这个注解来定义`GreetingController`，Controller中的方法会通过返回一个字符串(View的名字)来指定负责渲染网页的的View，在我们的例子中是greeting

    ```java
    package com.example.servingwebcontent;
    
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    
    @Controller
    public class GreetingController {
    
    	@GetMapping("/greeting")
    	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    		model.addAttribute("name", name);
    		return "greeting";
    	}
    
    }
    ```

    `@GetMapping`注解用来表明通过什么路径来访问这个方法，上面是通过`/greeting`，`@RequestParam`注解绑定了这个方法的一个形参，表示这个方法的形参会跟查询字符串`name`绑定在一起，但是这个字符串并不是必须的，如果没有传入值，则使用默认值`World`，方法执行的过程中会将name属性绑定到Model对象中，最后被展示到页面中



**创建一个greeting页面**

+ 下面在项目的`/resource/templates`中创建`greeting.html`，粘贴下面的内容

    ```html
    <!DOCTYPE HTML>
    <html xmlns:th="http://www.thymeleaf.org">
    <head> 
        <title>Getting Started: Serving Web Content</title> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
        <p th:text="'Hello, ' + ${name} + '!'" />
    </body>
    </html>
    ```

    这个页面使用`Thymeleaf`渲染，确保我们引入了他的依赖



**运行应用**

这里我们使用了SpringBoot的工具来运行

+ 创建一个类`ServingWebContentApplication`

    ```java
    package com.example.servingwebcontent;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    @SpringBootApplication
    public class ServingWebContentApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ServingWebContentApplication.class, args);
        }
    
    }
    ```

    使用`@SpringBootApplication`注解来替换我们所需的所有配置文件，这意味着等我们到了SpringBoot会减少大量的配置文件使用

+ 直接运行这个类，访问`localhost:8080/greeting`就可以出现我们的页面了



## 2.Spring初步



### 1.SpringMVC的概念

+ SpringMVC是Spring框架的一个模块，所以它能使用spring的IoC和AOP
+ SpringMVC是建立在Servlet的基础之上，在Servlet的基础之上赋予了其更多的功能
+ springMVC中负责与用户交互的是DispatcherServlet对象，它把请求分发给Controller对象，由Controller对象负责处理用户的请求，Controller并不是一个Servlet



### 2.第一个Servlet

+ 首先创建一个web项目

+ 在`web.xml`文件中编写关于DispatcherServlet的配置，这个servlet是springmvc自带的，不需要我们创建

    ```xml
    <servlet>
    	<servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
        	<param-name>contextConfigLocation</param-name>
            <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
    	<servlet-name>springmvc</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
    ```

    来解释一下上面配置文件的意思

    + DispatcherServlet是必须在服务器启动时就被创建，所以`load-on-startup`为1，这个参数代表了这个servlet的启动优先级，数字越小，优先级越高

    + 同时，当servlet创建时，会创建springmvc的容器对象，创建spring容器对象就会读取spring的配置文件，所以实际上在servlet创建的过程中会运行`init()`，下面的是该方法的一部分

        ```java
        public void init(){
            WebApplicatoinContext context = new ClassPathXmlApplicationContext("springmvc.xml");
            getServletContext().setAttribute(key, context);
            //...
        }
        ```

    + 但是实际上，默认会从`WEB-INF`下读取名为`<servlet-name>-servlet.xml`的文件，这非常的不方便，所以我们一般会自定义文件的读取路径，使用`init`参数，指定`contextConfigLocation`从`classpath`开始读取

+ 设置完`servlet`属性后就要开始设置`servlet-mapping`了，`url-pattern`有两种设置方法：

    + `*.xxxx`类型，这个`xxxx`是我们定义的后缀，此时我们使用`.do`作为例子
    + `/`类型，这个就是像我们之前学习tomcat时的命名方式

+ 编写一个`Controller`，用户的请求都必须要由`Controller`来处理，使用注解`@Controller`来标注一个Controller，使用`@RequestMapping`来指定一个方法处理哪个路径，就相当于之前的servlet

    ```java
    @Controller
    public class MyController{
        @RequestMapping("/some.do")
        public ModelAndView doSome() {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg","Welcome to use springmvc");
            modelAndView.setViewName("/show.jsp");
            return modelAndView;
        }
    }
    ```

    方法中定义了一个ModelAndView，表示这个方法既会处理数据，又会处理视图，其中`addObject`方法就是往请求域中添加数据，在底层的操作其实是调用了`request.setAttribute`方法，`setViewName`指定了使用哪个文件来处理视图，这里我们用的是jsp文件，最后返回ModelAndView对象即可

+ 指定组件扫描器，完成上面的工作后，我们还需要让`DispatcherServlet`知道它来负责哪个包，所以我们需要在刚刚设置好的`springmvc.xml`文件中编写我们需要扫描的包，注意这个文件是一个spring的配置文件

    ```xml
    <context:component-scan base-package="com.framework.springmvc"/>
    ```

    



### 3.请求处理过程

**some.do的处理过程**

+ 发起some.do
+ tomcat通过web.xml知道应该由DispatcherServlet(*.do)来处理，并把请求交给他
+ DispatcherServlet(*.do)通过app-context.xml扫描包并实例化ControllerBean对象，也就是说会将Controller纳入容器的管理，并把请求转发给相应的Controller
+ Controller交由对应的方法doSome来执行，最后将处理好的数据转发给视图



**容器的创建过程**

+ 在springMVC中，容器是WebApplicationContext，当我们创建Servlet时，首先会运行servlet的`init`方法，init方法会寻找容器也就是WebApplicationContext，如果没有就会创建一个容器，创建容器的过程中就会读取spring配置文件，创建完成后就会将容器放入全局作用域中，注意这个容器是由对应的DispatcherServlet来进行管理的



**请求的处理过程**

+ 首先会调用DispatcherServlet的service方法，然后调用doService方法，最后调用核心方法`doDispatch`，这个方法就会调用我们的Controller的方法来处理数据，然后返回的ModelAndView对象就会交由DispatcherServlet来完成后续的处理



### 4.关于上面的项目的补充

**项目存在的问题**

+ 用户可以直接访问视图，如果数据没有处理的情况下，这样的操作十分的危险，所以我们要对视图进行一定的处理



**解决方案**

+ 我们知道WEB-INF文件夹中的文件都是受保护的，不会对用户开放，所以一般的情况下，我们会把视图放入WBE-INF/view文件夹下

+ 单靠上面那一步还不够，我们还需要指定视图解析器才能让用户进行访问，在对于DispatcherServlet的配置文件中指定

    ```xml
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    ```

+ 指定完成后，只要我们在方法中指定视图的名字，springmvc就会自动的将 前缀 + 名字 + 后缀 拼起来，组成最终的访问地址



## 3.springMVC深入

### 1.乱码的解决

**使用过滤器来解决post请求乱码**

tomcat9及以下的版本使用post请求发送中文时会造成乱码，因为post请求默认的字符集是ISO，有下面的方法可以解决这个问题

+ **手动的在方法中添加**

    ```java
    public ModelAndView doSome(HttpRequest request){
        request.setCharacterEncoding("UTF-8");
    }
    ```

    但是当方法一多这种方法还是不太好用

+ **使用过滤器**

    + 使用过滤器可以简化我们的操作，我们可以实现一个自己的过滤器，也可以使用spring框架自带的过滤器，这里我们使用spring框架自带的过滤器，只需要在web.xml文件中添加下面的代码即可

        ```xml
        <filter>
            <filter-name>CharacterEncodingFilter</filter-name>
            <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
            <init-param>
                <param-name>Encoding</param-name>
                <param-value>UTF-8</param-value>
            </init-param>
            <init-param>
                <param-name>forceRequestEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
            <init-param>
                <param-name>forceResponseEncoding</param-name>
                <param-value>true</param-value>
            </init-param>
        </filter>
        <filter-mapping>
            <filter-name>CharacterEncodingFilter</filter-name>
            <url-pattern>/*</url-pattern>
        </filter-mapping>
        ```



**通过设置@RequestMapping注解来解决响应字符串乱码**

使用tomcat9及以下的版本返回一个普通的字符串时很可能造成乱码，所以我们使用下面的方法来解决

+ 设置@RequestMapping的属性produces为`text/plain;charset=utf-8`

    ```java
    @RequestMapping("/user/doSome", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doSome(String name){
        return "hello world!";
    }
    ```

    




### 2.@RequestMapping注解的使用

**对于路径的指定**

+ **在方法上的使用**
    + 在上面的项目中我们知道，`@RequestMapping`在方法上使用时，表示该方法用来处理哪个路径的请求，但是在实际开发中，我们的路径一般不会只有一段，肯能是`/user/some.do, /dept/some.do`等等，如果说，更长那就更麻烦了

+ **在类上的使用**
    + 一般来说，同一个Controller中的路径都会有一个相同的前缀，如果每个方法都写一遍就会非常的繁琐，而且维护难度大，这时我们可以把路径相同的部分提取出来写到类上的注解中



**对于请求方式的指定**

+ 除了能指定路径以外，还能指定处理的请求方式，只需要给注解的属性`method`赋值即可，如果不指定则没有限制



### 3.用户参数的接收

**传统的接收方法**

+ 像之前学习servlet一样，我们使用request来获取用户传入的参数，此时需要我们在Controller的方法中加上参数

    ```java
    public ModelAndView doSome(HttpRequest request){
        request.getParameter("name");
    }
    ```

    上面获取参数的方式还是比较繁琐的



**使用框架接收用户的参数**

+ **使用基本类型接收**

    + 我们只需要在方法的参数列表设置想要的参数，用户发送的数据会根据名字传入，框架会自动的做类型转换，在方法中就可以直接使用了

        ```java
        public ModelAndView doSome(String name, int age){
        
        }
        ```
    
+ **使用对象来接收**

    + 当接收的参数较多时，会造成我们的麻烦，这时候还是封装一个对象来接收参数比较的方便，使用对象的接收的前提的是提供了无参的构造方法，类中提供了标准的setter方法，*因为spring会先调用无参构造方法创建对象，然后使用set方法去给属性赋值*

        ```java
        public ModelAndView doSome(Student student){
        
        }
        ```

        




### 4.@RequestParam注解的使用

当前前后端分离时，前段传过来的参数很可能与后端的参数名字不一致，这时我们可以用@RequestParam注解来解决这个问题，但是这个注解只能使用在基础数据类型上：

+ 这个注解有三个常用的属性：
    + value（name的别名），String类型：用来指定参数的名字，前端传入的参数中与这个名字匹配的值会被赋到参数上
    + required，boolean类型，默认值true：表示这个参数是否必须
    + defaultValue，String类型：当前端没有参数传入时，使用默认的参数赋值
    
    ```java
    public ModelAndView doSome(@RequestParam("rname", required = false, defaultValue = "bin")String name, String age)
    ```
    



### 5.Controller中的方法返回值

上面我们已经使用了ModelAndView返回值，下面来系统性的学习一下返回值



**返回值的类型**

+ ModelAndView
    + 如果说我们想要同时处理数据和视图，需要用ModelAndView来对数据处理后转发到视图
+ String
    + 如果说我只需要返回视图，不用对数据的处理，只需要字符串即可，所以可以返回视图的名称，也可以返回视图的完整访问路径
    + 注意如果使用这种方式，我们使用了视图解析器时，只能返回名称，不能返回路径
+ void
    + 表示发送请求后，我们不想让界面跳转只要保留原界面即可
+ Object
    + 可以将Object对象转换为，json，xml...等等媒体格式响应给浏览器



**具体的使用**

+ ModelAndView

    + 将数据处理后，存入域中，然后转发到视图进行访问

        ```java
        public ModelAndView doSome(String name){
            ModelAndView mv = new ModelAndView();
            mv.setAttribute("name", name);
            mv.setViewName("show");
            return mv;
        }
        ```

        要指定视图的名字

+ String

    + 直接指定视图的名字即可

        ```java
        public String doSome(String name, HttpRequest request){
            request.setAttribute("name", name);
            return "show";
        }
        ```

+ void

+ Object

    + 直接返回对象即可，但是使用这个返回值有两个前提

        + 在配置文件中添加了驱动`<mvc:annotation-driven/>`
        + 在方法上添加了注解`@ResponseBody`

        ```java
        public Student doSome(String name, String age){
            Student stu = new Student();
            stu.setName(name);
            stu.setAge(age);
            return stu;
        }
        ```

    + 除了返回一个对象，我们还可以返回List数组，只需要将多个对象添加到List然后直接返回List即可



**返回字符串文本**

上面不是说过当返回值是String时，是直接跳转到视图么，那么在这种情况下怎么返回字符串文本呢？

+ 只要我们在方法上加上注解`@Response`即使此时返回值为String，也会被认为是向浏览器相应一个对象，只不过这个对象是字符串



### 6.静态资源和内部资源的访问

**tomcat的defaultServlet**

我们打开tomcat的全局web.xml文件可以发现有下面一段配置：

```xml
<servlet>
    <servlet-name>default</servlet-name>
    <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
    <init-param>
        <param-name>debug</param-name>
        <param-value>0</param-value>
    </init-param>
    <init-param>
        <param-name>listings</param-name>
        <param-value>false</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
  <!-- The default servlet for all web applications, that serves static     -->
  <!-- resources.  It processes all requests that are not mapped to other   -->
  <!-- servlets with servlet mappings (defined either here or in your own   -->
  <!-- web.xml file)
```

上面定义了一个叫做default的servlet，它是负责提供静态资源的和处理那些找不到映射的url的，并且它的`url-pattern`就是一个`/`，代表所有的静态资源和未映射的url都会由这个servlet处理



**静态资源与内部资源的区别**

+ 静态资源
    + 静态资源一般是指用户在浏览器上输入地址就可以访问到的资源，如html页面，图片等
    + 静态资源一般放在webapp/static下
+ 内部资源
    + 内部资源一般是用户在浏览器上输入地址无法直接进行访问的资源，如视图等，内部资源是受保护的资源
    + 内部资源一般放在WEB-INF下，内部资源的访问需要配置`内部资源视图解析器`



**静态资源的访问**

+ 在上面我们的几个例子使用的都是`**.do, **.action`等格式的url-pattern，这样的的话DispatcherServlet就很难处理所有的请求

+ 如果说此时我们把DispatcherServlet的url-pattern设置为`/`那么我们就可以处理所有的请求了，但是就会导致我们所有的静态资源访问失效，下面是常用解决方案

+ 首先将所有的静态资源都放到`static`下，注意不要使用`WEB-INF`及其子目录

    ```
    webapp/static
        |--image
        |--html
        |--js
        |--css
    ```

+ 使用标签`<mvc:resources mapping="" location="">`来指定静态资源的位置，使用这个的前提是启动注解驱动`<mvc:annotation-driver/>`

    ```xml
    <mvc:resources mapping="/static/**" location="/static/"
    ```

	上面表示的是只要url是以`static`开头的，就会直接到`/static`目录下查找



**内部资源的访问**

+ 对于视图，一般要在用户有一定的前提操作之后，才能访问，所以一般视图资源要作为受保护的资源存放在WEB-INF下

+ 访问视图的操作第一步就是配置内部资源视图解析器，视图解析器是由`中央处理器DispatcherServlet来负责的`

    ```xml
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="WEB-INF/static/html/"/>
        <property name="suffix" value=".html"/>
    </bean>
    ```

+ 第二步就是在Controller中的对应方法中指定对应的视图名称，使用`ModelAndView`或者直接返回视图名



### 7.相对路径和绝对路径

在实际开发中我们经常会遇到url前到底是加斜杠还是不加斜杠的问题，下面来解析一下，路径有两种：

+ **绝对路径**
    + 绝对路径就是指从协议开始，如`https://localhost/`这种只要带有协议的都是绝对路径
+ **相对路径**
    + 没有协议开始的都是相对路径，相对路径的使用都需要一个参考，比如下面有个url，此时我们访问的地址是`https://localhost:8080/001/index.jsp`
    + 假设我们从index.jsp访问这个地址`user/dosome`，那么我们会访问到`https://localhost:8080/001/user/dosome`
    + 假设我们从index.jsp访问这个地址`/user/dosome`，那么我们会访问到`https://localhost:8080/user/dosome`



## 4.SSM整合开发

### 1.每个框架负责的部分

+ SpringMVC：视图层，界面层，负责接收请求，显示处理的结果
+ Spring：业务层，管理Service，Dao之间的关系
+ MyBatis：负责持久化层



### 2.添加的依赖

+ springmvc

+ spring-mybatis

+ mybatis

+ mysql-connector-java

+ druid

+ jakarta.servlet

+ jakarta.annotation

    ```xml
    <dependencies>
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>6.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>6.0.6</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.30</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>6.0.5</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.11</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.1.0</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.16</version>
        </dependency>
        <dependency>
            <groupId>jakarta.annotation</groupId>
            <artifactId>jakarta.annotation-api</artifactId>
            <version>2.1.1</version>
        </dependency>
    </dependencies>
    ```



### 3.创建包

我们的包结构如下

+ controller
+ mapper
+ bean
+ service



### 4.编写配置文件



**web.xml配置文件**

+ 注册ContextLoaderListener

+ 配置spring的配置文件位置

+ 配置DispatcherServlet

    ```xml
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:conf/applicationContext.xml</param-value>
    </context-param>
    <servlet>
        <servlet-name>app</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:conf/dispatcherServlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>app</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
    ```



**dispatcherServlet配置文件**

+ 配置组件扫描器指定Controller的位置

+ 配置内部资源视图解析器

+ 注解驱动标签

+ DispatcherServlet使用`/`时，配置资源位置标签

    ```xml
    <context:component-scan base-package="top.pr1grim.controller"/>
    
        <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
            <property name="prefix" value="/static/html/"/>
            <property name="suffix" value=".html"/>
        </bean>
        <mvc:annotation-driven/>
    ```



**applicationContext.xml配置文件**

+ 引入外部数据库配置文件

+ 配置组件扫描器扫描全部的包

+ 配置德鲁伊连接池：配置数据库信息

+ 配置SqlSessionFactoryBean：指定数据源，设置bean包别名，指定MyBatis核心配置文件

+ 指定mapper扫描器指向mapper包

+ 配置事务管理器：配置数据源

+ 事务驱动标签

    ```xml
    <context:property-placeholder location="classpath:/conf/jdbc.properties"/>
    <context:component-scan base-package="top.pr1grim"/>
    
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>
    <bean class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:/conf/mybatis-config.xml"/>
        <property name="typeAliasesPackage" value="top.pr1grim.bean"/>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="top.pr1grim.mapper"/>
    </bean>
    <bean id="tx" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <tx:annotation-driven transaction-manager="tx"/>
    ```



### 5.编写类

**bean**

+ 提供与数据库对象相应的bean，提供setter和getter方法，toString方法



**mapper**

+ 首先从mapper接口写起，编写需要的方法
+ 编写mapper.xml文件，编写相应的SQL语句



**service**

+ 编写service接口，提供合适的方法
+ 编写service实现类，使用@Service进行标注，其中的mapper属性使用@Resource进行标注



**controller**

+ 提供对应的方法，使用@RequestMapping标注



### 6.实现过程中遇到的问题

+ 创建maven项目的问题
    + 如果我们创建了一个空的maven项目，然后给项目添加web的依赖，那么此时的web项目是有问题的，不能这么做
    + 正确的做法是直接创建maven的webapp模板项目
+ 依赖的问题
    + 如果在项目中出现了无法启动监听器的情况，很有可能是出现了没有引入依赖的情况，有可能是上面的做饭造成的
    + 可以打开项目的结构，通过



## 5.转发和重定向

之前我们已经学习过servlet中的转发和重定向

```java
request.getDispatcher().forward("");
reponse.sendRedirect("")
```

但是springMVC框架对他们进行了包装，能让我们更加方便的使用



### 1.转发

转发向与servlet的中的重定向功能一致，只是实现形式略有区别

```java
@RequestMapping("/c.do")
public ModelAndView forwardTest() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("forward:forward.html");
    return mv;
}
```



### 2.重定向

同样是实现形式略有区别

```java
@RequestMapping("/d.do")
public ModelAndView redirectTest() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:forward.html");
    return mv;
}
```

但是如果说我们在ModelAndView中存储了数据，重定向时参数会以get请求的形式携带



**注意**

+ 使用这两个功能就无法使用视图解析器，这个两个功能一般使用来访问视图解析器无法解析的位置
+ 重定向无法访问`WEB-INF`下的资源



## 6.异常集中处理机制

在springMVC中，框架基于AOP编程实现了异常的集中处理机制，不再需要程序员进行繁杂的异常捕捉操作，实现只需要下面的几步：

+ 首先创建一个全局异常处理类，重写有参和无参构造方法，这个类是其他自定异常的父类

    ```java
    public class UserException extends Exception{
        public UserException() {
        }
    
        public UserException(String message) {
            super(message);
        }
    }
    ```

+ 然后创建异常类，同样重写构造方法

    ```java
    public class NameErrorException extends UserException{
        public NameErrorException() {
        }
    
        public NameErrorException(String message) {
            super(message);
        }
    }
    ```

+ 在需要进行异常处理的方法中直接抛出异常，抛出全局异常即可

    ```java
    @RequestMapping("/c.do")
    public ModelAndView forwardTest() throws UserException {
        ModelAndView mv = new ModelAndView();
        if (true) {
            throw new NameErrorException("Illegal Name");
        }
        return mv;
    }
    ```

+ 编写异常处理类

    ```java
    @ControllerAdvice
    public class UserExceptionHandler {
        @ExceptionHandler(NameErrorException.class)
        public ModelAndView nameException() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("error");
            return mv;
        }
    }
    ```

    这里需要注意的是，异常处理类必须使用`@ControllerAdvice`标注，并且指定包扫描，同时负责处理对应异常的方法要使用`@ExceptionHandler`标注，并在注解中指定处理的异常类型



## 7.拦截器

拦截器是springMVC实现的一个类似于过滤器的组件，但是功能更为强大，提供了更多的特殊时机来给程序员操作的机会



### 1.拦截器的声明和使用

**拦截器的实现**

+ 首先创建一个类并实现HandlerInterceptor接口，重写接口中的三个方法

    ```java
    public class MyInterceptor implements HandlerInterceptor {
    
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
    
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        }
    
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        }
    }
    ```

+ 这个三个方法执行顺序为 preHandle -> controller -> postHandle -> afterCompletion

+ preHandel方法一般是执行过滤的操作如用户登录等，如果返回值为真，则继续向下执行，如果返回假，后面的所有方法都不会执行，这个方法的执行时机是在controller之前

+ postHandle方法一般是用来修正视图的操作，也就是说这个方法会在controller方法执行过后，视图解析器工作之前执行

+ afterCompletion一般用来释放资源，在视图解析器工作之后执行



**拦截器的声明**

+ 创建拦截器后还需要在DispatcherServlet的配置文件中对拦截器进声明

    ```xml
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/**"/>
            <bean class="top.pr1grim.handlerInterceptor.MyInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
    ```

    mapping代表拦截的路径，class代表拦截器的类



**拦截器的执行顺序**

+ 当有多个拦截器声明时，他们的执行顺序并不像servlet中的过滤器一样，而是像一个层层包装的盒子

![interceptor](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/interceptor.png)

+ 会先经过外层的盒子，然后经过内层的盒子，所以拦截器的执行顺序为，preHandle1 -> preHandle2 -> postHandle2 -> postHandle1 -> afterCompletion2 -> afterCompletion1



### 2.拦截器与过滤器的区别

1. 过滤器是servlet中的对象，拦截器是框架中的对象
2. 过滤器实现Filter接口的对象，拦截器是实现HandlerInterceptor
3. 过滤器是用来设置recuest，response的参数，属性的，侧重对数据过滤的。拦截器是用来验证请求的，能截断请求。
4. 过滤器是在拦截器之前先执行的。
5. 过滤器是tomcat服务器创建的对象，拦截器是springmva容器中创建的对象
6. 过滤器是一个执行时间点。拦截器有三个执行时间点
7. 过滤器可以处理jsp, is, html等等
  拦截器是侧重拦截对controller的对象。如果你的请求不能被DispathcerSErvlet接收，这个诸求不会执行拦截器内容
