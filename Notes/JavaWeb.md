## 1.Servlet

### 1.实现一个最基本的web应用

第一步在`tomcat/webapps`下新建一个目录叫做`oa`，那么这个oa就是我们这个应用的名字，然后放入我们的`index.html`文件

第二步启动`tomcat`，然后在浏览器上输入`hostlocal:8080/oa/index.html`，如果出现了界面，那么就成功了

---

### 2.模拟Servlet

对于Java程序员来说，我们只要编写一个类实现Servlet接口， 将编写的类配置到文件中，指定`请求路径`和`类名`的关系

注意：这个配置的文件名，文件路径都是固定的，参考SUN公司制定的`Servlet`规范中的明细

严格意义上来说，`Servlet`并不是一个简单的接口，`Servlet`规定了`Webapp`的：

+ 目录结构
+ 配置文件结构
+ 配置文件的路径
+ Java程序路径

`Tomcat`要遵循`Servlet`规范，`JavaWEB`程序员也要遵循这个规范

---

### 3.开发第一个`Servlet`

开发步骤：

+ 首先在`webapps`目录下新建一个目录`crm`，注意这个`crm`是项目的名字，同时也是这个`webapp`的根

+ 第二步在`webapp`的根也就是`crm`下创建一个目录`WEB-INF`，注意这个目录的名字的`Servlet`规范规定的

+ 第三步在`WEB-INF`目录下新建目录`classes`，这个目录的名字同样是规定的

+ 第四步在`WEB-INF`目录下新建一个目录`lib`，虽然这个目录不是必须的，但是当我们需要第三方的`.jar`包时，就要把它放到这个目录下，同样是规定的

+ 第五步在`WEB-INF`目录下新建一个文件`web.xml`，这个文件是必须的，在这个文件中描述了请求路径和`Servlet`类的对照关系，这个文件最好复制粘贴

+ ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    
    <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                          https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
      version="5.0"
      metadata-complete="true">
      
    </web-app>
    ```

+ 第六步，编写`Java`小程序，这个程序必须实现`Servlet`接口，需要注意的是，从`JavaEE9`开始，`JavaEE`更名为`Jakarta`，所以所有的包名都会改变，`Tomcat10`之后的的程序无法运行`Tomcat9`及以下的程序。编写完成后将生成的`.class`文件放到`classes`目录下即可

+ 第七步，在`web.xml`中编写配置文件注册`Servlet`类

+ ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    
    <web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                          https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
      version="5.0"
      metadata-complete="true">
            <!--Servlet 描述信息-->
        <!--任何一个servlet都对应一个servlet-mapping-->
        <servlet>
            <servlet-name></servlet-name>
            <!--这个位置必须是带有包名的全限定类名-->
            <servlet-class></servlet-class>
        </servlet>
        <!--Servlet 映射信息-->
        <servlet-mapping>
            <!--这里也是随便写，但是要和上面一样-->
            <servlet-name></servlet-name>
            <!--这里需要文件路径，当前可以随便写，必须以/开头-->
            <url-pattern>/asd/asd</url-pattern>
        </servlet-mapping>
    </web-app>
    ```

+ 第八步，在浏览器上请求，在地址框输入`localhost:8080/crm/asd/asd`，也就是`JavaWEB`的根目录加上上面我们写的路径。浏览器访问太麻烦我们可以使用超链接，需要注意的是，`HTML`文件需要放到`WEB-INF`之外

+ 总结文件的目录结构

+ ```xml
    webapproot
    	|----WEB-INF
    			|----classes（字节码文件）
    			|----lib（第三方jar包）
    			|----web.xml（注册文件）
    	|----.html
    	|----.css
    	|----.js
    	|----.img
    	... 
    
    ```

    向浏览器响应一段`HTML`代码

    ```java
    public void service(ServletRequest request, ServletResponse response){
        //Set the content
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>Hello Servlet</h1>")
    }
    ```

    
    

### 4.使用集成开发环境开发Servlet程序

第一步:基础搭建

1.创建一个新工程

2.创建一个新模块

2.1模块右击-->Add Framework Support..(添加框架支持)-->选择`WebApplication`(自动创建一个符合Servlet规范的webapp目录结构。)

---

第二步:编写Servlet

1.编写Servlet

1.1这个时候发现Servlet.class文件没有。怎么办? 

File --> Project Structrue --> Modules --> Dependeencies --> 添加Library或者JAR

2.实现`jakarta.servlet.Servlet`接口中的5个方法。

3.在Servlet当中的service方法中编写业务代码（我们这里连接数据库了。）

4.在WEB-INF目录下新建了一个子目录：lib，并且将连接数据库的驱动jar包放到lib目录下。

---

第三步:在web.xml文件中完成StudentServlet类的注册

```java
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">


    <servlet>
        <servlet-name>studentServlet</servlet-name>
        <servlet-class>com.bjpowernode.Student</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>studentServlet</servlet-name>
        <url-pattern>/servlet/student</url-pattern>
    </servlet-mapping>
</web-app>
```

---

第四步:给一个html页面，在HTML页面中编写一个超链接

- student.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!--这里的项目名是 /xmm ，无法动态获取，先写死-->
<a href="/xmm/servlet/student">student list</a>
</body>
</html>
```



---

第五步：让IDEA工具去关联Tomcat服务器。

1.Add Configuration

2.左上角加号，点击Tomcat Server --> local

3.Deployment（点击这个用来部署webapp），继续点击加号，部署即可。修改 `Application context为：/这个app的名字`我们这里是/xmm

---

第六步:启动tom服务器，浏览器输入地址

打开浏览器，在浏览器地址栏上输入：http://localhost:8080/xmm/student.html

-----

### 5.Servlet生命周期

#### 1.Servlet的创建的时机

+ 网站中所有的Servlet接口实现类的实例对象，只能由Http服务器负责创建。

开发人员不能手动创建Servlet接口实现类的实例对象

+ 在默认的情况下，用户第一次请求`service()方法调用`的时候，自动创建Servlet接口实现类对象。(调用无参构造方法)

---

在手动配置情况下，我们要求Http服务器在启动时自动创建某个Servlet接口实现类的实例对象（一般不会使用）

- 基于 XML ⽂件的配置⽅式。 

```xml
<servlet> 

<servlet-name>hello</servlet-name> <!--声明一个变量存储servlet接口实现类类路径-->

<servlet-class>com.bjpowernode.controller.OneServlet</servlet-class> <!--声明servlet接口实现类类路径-->

 <load-on-startup>30<load-on-startup><!--填写一个大于0的整数即可，数字越大优先级越高-->

</servlet> 

<!--为了降低用户访问Servlet接口实现类难度，需要设置简短请求别名-->

<servlet-mapping> 

<servlet-name>hello</servlet-name> 

<url-pattern>/newhello</url-pattern> <!--设置简短请求别名,别名在书写时必须以"/"为开头-->

</servlet-mapping>
```

---

- 基于注解的⽅式。 

```java
@WebServlet("/newhello") 
public class HelloServlet implements Servlet { 

}
```

#### 2.关于Servlet类中方法的调用次数

- 无参构造方法只执行一次，程序员只能提供无参构造或者不提供构造方法，建议不提供
- init方法只执行一次，通常在Servlet对象被创建后调用，做初始化操作
- service方法：用户发送一次请求则执行一次，发送N次请求则执行N次
    - 一般服务用户的内容都会在service方法执行
- destroy方法只执行一次
    - 一般用来释放使用过的资源，文件流，数据库链接...

---

说明:

- 用户在发送第一次请求的时候Servlet对象被实例化（AServlet的构造方法被执行了。并且执行的是无参数构造方法。）
- AServlet对象被创建出来之后，Tomcat服务器马上调用了AServlet对象的`init()`方法，初始化对象
- 用户发送第一次请求的时候，在`init()`方法执行之后，Tomcat服务器马上调用AServlet对象的`service()`方法
- 用户在发送第二次，或者第三次，或者第四次请求的时候，Servlet对象并没有新建，还是使用之前创建好的Servlet对象，直接调用该Servlet对象的service方法。这说明:
    - Servlet对象是单实例的，但是是假单例，真单例的构造方法是private修饰的
    - 单例的原因是Servlet是由Tomcat来创建的并且只创建了一个，我们无法进行干预
- 在关闭服务器的时候，Aservlet对象在销毁前会调用`destroy()`方法

---

### 6.Servlet的实现

#### 1.实现一个Servlet

+ 在webapp编写过程中，我们既可以实现`Servlet`接口，也可以去继承`GenericServlet`类:

    + 因为GenericServlet实现了Servlet接口，同时只里面只有一个抽象方法`service()`，这意味着我们必须实现的方法只有`service()`，其他方法当我们需要用到时再重写也行

    + GenericServlet类同时重载了`init()`方法，因为原来的`init(ServletConfig)`方法是带有参数的，`Tomcat`在创建对象后就会调用这个方法并传入参数，这个方法的存在是为了将`Tomcat`传入`Servlet`的`ServletConfig`对象保留下来，所以如果我们重写`init(ServletConfig)`则可能会丢失存储的对象，所以这里重载了一个没有参数的`init()`方法供程序员重写，同时`init(ServletConfig)`会调用`init()`这样的流程既运行程序员重写`init()`方法，又保留的之前的模板代码


+ 现在还有一个专供Http协议的类HttpServlet，现在我们编写一个类去继承这个类，然后重写`doGet()`或者`doPost()`，同样可以实现一个`Servlet`

---

#### 2.ServletConfig对象

##### 1.ServletConfig的定义

+ ServletConfig是什么？
    + ServletConfig是Jakarta内部提供的一个接口
+ 谁去实现了这个接口？

    + WEB服务器去实现了这个接口，在这里就是Tomcat去实现了这个接口
+ 一个`Servlet`对象对应一个`ServletConfig`对象
+ 时候创建了ServletConfig对象，由谁创建？

    + Tomcat在创建Servlet的同时创建了ServletConfig对象
+ ServletConfig对象是用来干什么的？

    + ServletConfig对象就是Servlet的配置文件，他包装了web.xml的内容，Tomcat会自动解析xml文件并将信息打包进ServletConfig对象中

---

##### 2.ServletConfig局部配置文件

```xml
<!--我们可以在xml文件中配置一个Servlet对象的初始化信息，这些init标签应该放到<servlet>标签中-->
<init-param>
	<param-name>driver</param-name>
    <param-value>com.mysql.cj.jdbc.Driver</param-value>
</init-param>
<init-param>
	<param-name>url</param-name>
    <param-value>jdbc:mysql://localhost/mysql_learning</param-value>
</init-param>
<init-param>
	<param-name>user</param-name>
    <param-value>root</param-value>
</init-param>
<init-param>
	<param-name>password</param-name>
    <param-value>root1234</param-value>
</init-param>


```

---

##### 3.ServletConfig配置文件的解析

+ 以上`<servlet></servlet>标签中的<init-param></init-param>`标签会被`Tomcat`封装进`ServletConfig`对象中，我们可以通过两个方法获取：

    + java.util.Enumeration<java.lang.String> getInitParameterNames()
    + java.lang.String getInitParameter(java.lang.String name)

    ```java
    ServletConfig config = getServletConfig();
     
    Enumeration<String> names = config.getInitParamNames();
    while(names.hasMoreElement()){
        String name = names.nextElement();
        String value = config.getInitParam(name);
        out.print(name + " : " + value + "<br>");
    }
    //这样就获取了配置在xml中的name和value
    
    //但是因为我们继承的GenericServlet实现了这两个方法，所以我们有更简便的写法
    
    Enumeration<String> names = getInitParameterNames();
    while (names.hasMoreElements()) {
        String name = names.nextElement();
        String value = getInitParameter(name);
        out.print(name + " : " + value + "<br>");
    }
    
    ```

---

#### 3.ServletContext对象

##### 1.ServletContext的定义

1.ServletContext是什么？

+ Servlet是接口，是Servlet规范中的一员

2.Servlet是谁实现的？

+ Tomcat实现的

3.Servlet对象是谁创建的，在什么时候创建的？

+ 是由WEB服务器创建的
+ 在WEB服务器启动的时候创建
+ 对应一个Webapp来说只有一个ServletContext
+ ServletContext对象在服务器关闭时销毁

4.ServletContext怎么理解？

+ ServletContext对象其实就是对应整个web.xml文件
+ 放在ServletContext对象的数据其实是所有Servlet共享的
+ Tomcat是一个容器，一个Tomcat可以放多个webapp，每个webapp对应一个ServletContext对象

---

##### 2.ServletContext全局配置

那么根据ServletContext的这个特性，我们可以通过在xml文件中编写上下文的初始化参数，用于全局的配置

```xml
<context-param>
	<param-name>pageSize</param-name>
    <param-value>10</param-value>
</context-param>

<context-param>
	<param-name>startIndex</param-name>
    <param-value>0</param-value>
</context-param>
```



---

##### 3.ServletContext配置文件解析

在ServletContext中同样内置了两个方法用来读取这些初始化的数据

```java
jakarta.servlet.ServletContext application = getServletContext();

        Enumeration<String> names = application.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = application.getInitParameter(name);
            out.print(name + " " + value);
        }
```

是不是跟ServletConfig很像？但是还是略有区别的

+ 首先ServletContext配置的初始化信息在当前webapp目录下的每个Servlet都能访问，相当于全局变量
+ 而ServletConfig只能被对应的Servlet访问，相当于局部变量
+ 那么我们对于将初始化信息放到Context还是Config，要看这个信息是所有Servlet都要访问还是只有一个Servlet要访问

---

##### 4.ServletContext目录获取

```java
String contextPath = request.getContextPath();
//获取该webapp的根目录
```

```java
String realPath = application.getRealPath("path");
//获取文件的绝对路径，默认从webapp根下找
```

---

##### 5.ServletContext记录日志

```java
public void log(String message);
//日志信息会记录到CATALINA_HOME/logs目录下，如果是IDEA则会记录到IDEA的目录下
```

---

##### 6.ServletContext对数据的增删改

ServletContext对象还有一个名字，应用域，如果所有用户共享一份数据，且这份数据量不大，修改较少或者几乎不修改，那么我们可以把这份数据放在ServletContext中：

+ 为什么要求所有用户共享？
    + 因为如果不是共享的没有意义，这样还不如放到ServletConfig中
+ 为什么要求数据量小？
    + 因为ServletContext生命周期长，直到服务器关闭才销毁，服务器占用过大的内存会影响性能
+ 为什么要求修改少？
    + 因为多用户对数据修改操作引发的线程并发问题会导致安全性问题
+ 当满足上面三个条件的数据存放到ServletContext中，因为数据存放在缓存中，会大大提高数据的读取效率，避免了反复读取数据库的操作

```java
public void setAttribute(String name, String value);
//将数据存入应用域;
public Object getAttribute(String name);
//从应用域取出数据
public void removeAttribute(String name);
//删除应用域中的数据
```

---

### 7.Servlet其他常用方法

```java
response.setContentType("text/html;charset=UTF-8");//设置输出格式和字符集
getServletName();//获取Servlet的注册名字
getServletPath();//获取Servlet的访问路径
getServletContext();//获取应用域配置文件
getServletConfig();//获取请求域配置文件
```

---

### 8.HttpServlet引子

我们以后编写Servlet类的时候，实际上不会去直接继承GenericServlet，因为我们使用的是B/S结构的系统，这种系统是基于HTTP超文本传输协议的，在Servlet规范当中，提供了一个叫HttpServlet的类，是专门为Http准备的一个Servlet类，我们编写Servlet类的时候要去继承HttpServlet，使用HTTP协议更加敏捷，下面是他的继承结构:

```java
jakarta.servlet.Servlet(interface);
jakarta.servlet.GenericServlet implements Servlet(abstract class);
jakarta.servlet.http.HttpServlet extends GenericServlet(abstract class);
```

以后我们需要继承的是`HttpServlet`类

---

### 9.缓存机制

+ 堆内存中的字符串常量池
    + 创建字符串时，先在常量池查找，如果有就会指向这个对象，如果没有就会在常量池创建一个新的字符串对象
+ 堆内存中的整数型常量池
    + [-127,128]一共256个Integer类型的引用，放在整数型常量池中。没有超出这个范围直接从常量池取
+ 连接池
    + 这个连接指的是Java中连接数据库的连接对象Java.sql.Connection
    + JVM是一个进程，MySQL是一个进程，不同进程间建立连接是非常耗费资源的，所以我们可以先创建N个Connection对象，将连接对象放到一个集合当中，我们把这个集合称为连接池，这样用户在访问的时候，省去新建对象的过程直接从连接池获取对象，大大提高了效率
+ 线程池
    + Tomcat服务器是支持多线程的
    + Tomcat服务器每次启动时，会创建N个线程Thread对象，然后把线程对象放到集合中，这个集合称为线程池，用户发送请求后，需要一个对应的线程来处理，这时候直接从线程池中分配，效率比较高
    + 所有的WEB服务器，或者应用服务器，都是支持多线程的，都有线程池机制
+ redis
    + NoSQL数据库，非关系型数据库。缓存数据库
+ 向ServletContext中存放数据，等于是将数据存放到缓存cache中

---

### 10.HTTP协议

+ 什么是协议？
    + 协议实际上是某些人，或者某些组织指定好的一套规范，大家都按照规范来沟通就没有障碍
+ 什么是HTTP协议？
    + HTTP是W3C组织制定的一套超文本传输协议，相当于提前定好的信息发送模板
    + HTTP协议包括请求协议和响应协议
    + HTTP请求协议
        + 请求行
            + 包括三部分
            + 第一部分：请求方式
                + GET（常用）
                + POST（常用）
                + DELETE
                + PUT
                + HEAD
                + OPTIONS
                + TARCE
            + 第二部分：URI
                + 统一资源标识符，代表网络中某个资源的名字，但是通过URI无法定位到资源
                + 与URL的区别，URL：统一资源定位符，可以通过URL访问到资源，URL包括URI
            + 第三部分：HTTP协议版本号
        + 请求头
            + 请求的主机
            + 主机的端口
            + 浏览器信息
            + 平台信息
            + cookie信息
            + ...
        + 空白行
            + 区分请求头和请求体
        + 请求体
            + 向服务器发送的数据
    + HTTP响应协议
        + 状态行
            + 三部分组成
            + 第一部分：协议版本号（HTTP/1.1）
            + 第二部分：状态码
                + 200表示响应成功
                + 404表示资源不存在，是前端的错误
                + 405表示前端发送的请求方式与后端请求的处理方式不一致，如前端是POST请求，后端按GET请求处理，发生405错误，反之亦然
                + 500表示服务器段程序异常，一般认为是服务器的错误
                + 以4开头的，一般认为是浏览器的错误导致
                + 以5开头的，一般认为是服务器的错误导致
            + 第三部分：状态的描述
                + OK表示正常
                + NOT FOUND表示资源找不到
        + 响应头
            + 响应的内容类型
            + 响应的内容长度
            + 响应的时间
            + ...
        + 空白行
            + 用来分割响应头和响应体
        + 响应体
            + 响应的正文，内容是一个长的字符串，这个字符串被浏览器渲染并执行
+ W3C
    + W3C是万维网联盟组织
    + 万维网之父：蒂姆·博纳斯·李
+ 什么是超文本？
    + 超文本意思是不是普通文本，比如流媒体，音频，视频，图片等
    + HTTP协议支持：传递字符串，声音，视频，图片
+ HTTP如何工作?
    + HTTP协议游走在B和S之间，双方向对方发送数据都要遵守HTTP协议，这样双方才能解耦合
    + 解耦合的意思是，双方不互相依赖
+ B/S结构的系统的工作
    + 浏览器向服务器发送数据叫做请求(request)
    + 服务器向浏览器发送数据叫做响应(response)
+ 怎么向服务器发送get或者post请求？
    + 到目前为止只有一种情况可以发送post请求，使用form表单提交数据并且method属性为post
    + 其他一律都是get请求
        + 在地址栏输入URL回车
        + 点击超链接
        + form表单没有指定post方式
        + ...
+ GET和POST有什么区别？
    + get发送数据的时候，数据会挂在URI的后面，并且在在URI后面'?'，这个问号的后面就是数据，这样会导致发送的数据回显在地址栏上
    + post发送数据的时候，在请求体中发送，不会回显到地址栏上，也就是说在地址栏上看不到回显的数据
    + get只能发送普通的字符串，并且长度有限制，不同的浏览器限制不同，所以无法发送大量数据
    + post理论上能发送任何数据，长度没有限制
    + W3C是这样认为的
        + get请求比较适合从服务器端获取数据
        + post请求比较时候向服务器端传送数据
    + get请求是绝对安全的，因为get请求是为了从服务器上获取数据，不会对服务器造成威胁
    + post请求是危险的，因为post请求是为了向服务器提交数据，如果这些数据从后门的方式进入服务器当中，服务器是很危险的，另外post是为了提交数据，所以一般情况下拦截请求的时候，大部分会选择拦截（监听）post请求
    + get请求支持缓存而post请求不支持
    + 任何一个get请求的最终响应结果都会被浏览器缓存起来，在浏览器缓存当中，一个get请求的路径对应一个资源。实际上，只要你发送get请求，浏览器做的第一件事都是先从本地浏览器缓存中找，找不到才会去浏览器上获取，这种缓存机制就是为了提供用户体验
+ 如何选择发送的方式？
    + 如果从服务器上获取数据，使用get请求，如果向服务器发送数据，使用post请求
    + 大部分form表单的提交都是post方式，因为get请求会回显敏感信息在地址栏上，如果表单中有敏感信息，建议使用post
    + 文件上传一定是post，因为传的数据不是普通文本
    + 其他情况可以时候get

---

### 11.HttpServlet

在HttpServlet中返回ServletRequest和请求ServletResponse的类型都变成了HttpServletRequest和HttpServletResponse，所以我们首先要学习这两个接口中的常用方法

#### 1.获取用户的get请求或者post请求的数据

```java
Map<String, String[]> parameterMap = request.getParameterMap();//返回请求的name和value集合
Enumeration<String> names = request.getParameterNames();//获取所有的key
String[] values = request.getParameterValues();//获取Value集合
String value = request.getParameter("key");//获取value集合中的第一个元素
```

---

#### 2.请求域的使用

+ 请求域的问题，与应用域类似，在Servlet还存在一个请求域，与应用域相比，请求域小很多，生命周期短很多，请求域只在一次请求内有效。
+ 一个请求对象request对应一个请求域对象，一次请求结束后就销毁了，请求域对象也有三个方法

```java
void setAttribute(String name, String value);//往请求域中添加数据
Object getAttribute(String name);//获取请求域中的数据
void removeAttribute(String name);//删除请求域中的数据
```

+ 请求域和应用域的选择
    + 尽量使用小的域对象，因为小的对象占用的资源较少

---

#### 3.资源转发机制

+ Servlet资源转发机制，执行了A之后，我们想跳转到B，这时就需要利用转发机制
+ 首先把下一个资源的地址包装到资源转发器当中，然后调用资源转发器的方法，实现资源的转发
+ 资源转发机制可以实现`请求域`的数据共享
+ 资源转发可以是Webapp中的任何合法资源

```java
RequestDispatcher dipatcher = request.getRequestDispatcher("下一个资源的路径"); //把路径包装到资源转发器中
dispatcher.forward(request, response);//转发

//也可以这么写
request.getRequestDispatcher("下一个资源的路径").forward(request, response);
```

---

#### 4.其他一些常用方法

+ Request中的其他方法

```java
String remoteAddr = request.getRemoteAddr();//获取客户端IP地址
request.setCharacterEncoding("UTF-8");
response.setContextType("text/html;charset=UTF-8");
//设置服务器的字符集，解决post请求乱码问题，但是在Tomcat10之后，Tomcat会自动处理字符集引起的乱码问题
//那么怎么解决get请求的乱码问题
//修改CATALINA_HOME/conf/serve.xml文件
<Connector URIEncoding="UTF-8"/>
    
//获取应用根目录
String contextPath = request.getContextPath();
//获取请求的URI
String URI = request.getRequestURI();
//获取Servlet路径
String servletPath = request.getServletPath();

```



---

### 12.HttpServlet + JDBC + MySQL 编写简单的B/S系统

#### 1.需求分析

构建一个系统前，首先我们要对用户的角度入手，分析用户的需求，那么从当前我们的系统入手，大概有以下几点：

+ 首页index，用来检测用户的登录，登录后跳转到list页面（但是在我们这个项目先省略了）
+ 页面list，展示DEPT表的一个总览数据，用户可以对条目进行的操作有，增加，修改，删除，查看详情
+ 点击增加后，会跳转至添加页面add，上面有三个框分别是deptno，dname，loc，下面有一个按钮submit
+ 点击删除后，弹出确认框，因为对应敏感数据的删除还是要进行二次的确认，删除后跳回list页面
+ 点击详情后，跳转至详情页面，将完整的数据打印在页面上，下面有一个回退的按钮
+ 点击修改后，跳转至修改页面，上面三个框deptno，dname，loc，但是deptno是primary key是无法修改的用户只能修改除了主键之外的字段，下面一个按钮提交，提交后跳转至list

---

#### 2.系统构建流程

完成系统设计之后，不应该急着写代码，应该构思如何去编写代码，一般来说有下面两种方法：

+ 在用户的角度，从前端往后端一步步写
+ 在程序员的角度，从后端往前写

但是这里我们选择从前端往后端写，养成良好的习惯



因为需要连接到数据库，所以这里我们自己包装一个JDBCUtils工具类方便我们对于数据库的连接

```java
package com.javaweb.oa.Utils;
import java.sql.*;
import java.util.ResourceBundle;
/*
* JDBC Utils
* */
public class JDBCUtils {
    static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    static String driver;
    static String url;
    static String name;
    static String password;
    static {
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        name = bundle.getString("name");
        password = bundle.getString("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }
    public static void close(Connection conn ,PreparedStatement ps, ResultSet rs){

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

```



---

#### 3.list页面的构建

首先，用户会看到的是index页面，但是这里我们没有，所以直接显示list页面，首先创建一个html文件，编写大概的页面

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List</title>
</head>
<body>
<h1 align="center">Department</h1>
<hr/>
<table align="center" border="2px" width="50%">
  <tr>
    <th>Deptno</th>
    <th>Dname</th>
    <th>Loc</th>
    <th>Operation</th>
  </tr>
  <tr>
    <td>123</td>
    <td>123</td>
    <td >123</td>
    <td>
      <a href="" onclick="window.confirm('Are you sure to delete this item?')">Delete</a>
      <a href="edit.html">Edit</a>
      <a href="">Details</a>
    </td>
  </tr>
</table>
<a href="add.html">Add item</a>
</body>
</html>
```

下面是大概的结果

![image-20230120165305990](/home/binjunkai/.config/Typora/typora-user-images/image-20230120165305990.png)

可以看到我们的界面大概就是这个结果，但是我们的页面要动态的显示数据库的查询结果，所以现在开始list页面的设计与编写：

+ 首先，用户会先从index页面跳转至list页面，所以我们要先在web.xml中注册list页面

+ ```xml
    <servlet>
        <servlet-name>list</servlet-name>
        <servlet-class>com.javaweb.oa.action.DeptList</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>list</servlet-name>
        <url-pattern>/dept/list</url-pattern>
    </servlet-mapping>
    ```

注册完成后，用户就能正常的跳转到list页面，这个页面将会由DeptList这个类来解决，所以现在我们开始编写DeptList类：

+ 首先边写DeptList类继承HttpServlet类，因为是要从数据库中得到数据，所以是取用服务器中的数据，使用get请求，这里我们覆盖doGet方法

+ 因为是要动态的打印页面，因为我们没有学习jsp所以这里先使用PrintWriter向浏览器中输出HTML代码，使用下面的语句：

+ ```java
    request.setContentType("text/html");
    PrintWriter out = request.getWriter();
    ```

+ 然后是确定固定的部分和动态的部分分开打印：

+ ```java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        response.setContentType("text/html");
        String contextPath = request.getContextPath();
        PrintWriter out = response.getWriter();
        //Fixed part
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>List</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<script type='text/javascript'> ");
        out.print("function del(no) {");
        out.print("    if (window.confirm('Are you sure to delete the item?')) {");
        out.print("        alert('deleting please wait a second...');");
        out.print("        document.location ='" + contextPath + "/dept/delete?deptno=' + no");
        out.print("    }");
        out.print("}");
        out.print("</script>");
        out.print("<h1 align='center'>Department</h1>");
        out.print("<hr/>");
        out.print("<table align='center' border='2px' width='50%'>");
        out.print("  <tr>");
        out.print("    <th>Deptno</th>");
        out.print("    <th>Dname</th>");
        out.print("    <th>Loc</th>");
        out.print("    <th>Operation</th>");
        out.print("  </tr>");
    
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Dynamic part
                String deptno = rs.getString("deptno");
                out.print("  <tr>");
                out.print("    <td>"+ deptno +"</td>");
                out.print("    <td>"+rs.getString("dname")+"</td>");
                out.print("    <td>"+rs.getString("loc")+"</td>");
                out.print("    <td>");
                out.print("      <a href='javascript:void(0)' onclick='del(" + deptno + ")')'>Delete</a>");
                out.print("      <a href='/oa/dept/edit?deptno=" + deptno + "'>Edit</a>");
                out.print("      <a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>Details</a>");
                out.print("    </td>");
                out.print("  </tr>");
            }
            out.print("  <tr>");
            out.print("    <td colspan='4'><a href='" + contextPath + "/add.html'>Add item</a></td>");
            out.print("  </tr>");
            out.print("</table>");
            out.print("<hr>");
            out.print("</body>");
            out.print("</html>");
    
    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }
    ```

+ 完成list页面后我们首先要进行测试，打开页面看看是否显示正常

![image-20230120171816305](/home/binjunkai/.config/Typora/typora-user-images/image-20230120171816305.png)

那么list页面到这就完成了

---

#### 4.delete页面的构建

+ 那么下面就是delete页面的构建，用户首先会从list页面跳转至delete页面，同样的，先在web.xml中进行注册，然后修改list的代码，让超链接能够跳转至delete

+ ```xml
    <servlet>
        <servlet-name>delete</servlet-name>
        <servlet-class>com.javaweb.oa.action.DeptDelete</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>delete</servlet-name>
        <url-pattern>/dept/delete</url-pattern>
    </servlet-mapping>
    ```

+ 注册完成后，下面就是页面的构建了，首先是对应删除数据的确认，这里我们直接在前端弹窗确认，使用JavaScript编写一个函数，传入需要修改的deptno，这样就可以往服务器发送数据时，就可以知道要修改的行

+ ```javascript
    <script>
      function del(no) {
          if (window.confirm('Are you sure to delete the item?')) {
              alert('deleting please wait a second...');
              document.location = '/oa/dept/delete?deptno=' + no;//确定跳转的地址同时发送数据
          }
      }
    </script>
    ```

+ 然后编写类DeptDelete，首先要连接数据库，然后对数据进行update操作，然后跳转会list界面，这里我们只能用转发机制回到list（因为还没学重定向）

+ ```java
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String deptno = request.getParameter("deptno");
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            int count = 0;
            try {
                //启用事务机制
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);
                String sql = "delete from DEPT where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, deptno);
                count = ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
            if(count == 1){
                //以斜杠开头且不用写项目名
                request.getRequestDispatcher("/dept/list").forward(request, response);
            }else{
                request.getRequestDispatcher("/error.html").forward(request, response);
            }
        }
    ```

+ 对删除进行测试

![image-20230120173523410](/home/binjunkai/.config/Typora/typora-user-images/image-20230120173523410.png)

![image-20230120173540679](/home/binjunkai/.config/Typora/typora-user-images/image-20230120173540679.png)

可以看到数据成功被删除，那么delete的功能就完成了

---

#### 5.add界面的构建

那么下面就是add界面的构建了，首先用户会点击“Add item”然后跳转至添加界面，在表单中填写添加的条目，最后点击提交按钮，数据就会上传到服务器，添加到数据库中，最后跳转到list页面，下面是步骤：

+ 因为页面是静态的，所以先编写一个接受用户输入的html界面

+ ```html
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Add</title>
    </head>
    <body>
    <form action="list.html" method="post">
      deptno<input type="text" name="deptno"><br>
      dname<input type="text" name="dname"><br>
      loc<input type="text" name="loc"><br>
      <input type="submit" value="Submit">
    </form>
    </body>
    </html>
    
    ```

![image-20230120220404641](/home/binjunkai/.config/Typora/typora-user-images/image-20230120220404641.png)

+ 下一步就是让用户跳转到这个界面了，直接修改list页面的代码跳转到这个页面

+ 在这个页面填写的数据会以post请求发送到服务器，所以先注册一个“dept/add”页面

+ ```xml
    <servlet>
        <servlet-name>add</servlet-name>
        <servlet-class>com.javaweb.oa.action.DeptAdd</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>add</servlet-name>
        <url-pattern>/dept/add</url-pattern>
    </servlet-mapping>
    ```

+ 然后编写DeptAdd类，覆盖doPost方法

+ ```java
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
    
            Connection conn = null;
            PreparedStatement ps = null;
            int count;
            try {
                conn = JDBCUtils.getConnection();
                String sql = "insert into DEPT values(?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,deptno);
                ps.setString(2,dname);
                ps.setString(3,loc);
                count = ps.executeUpdate();
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, null);
            }
            if (count == 1) {
                request.getRequestDispatcher("/dept/list").forward(request,response);
            }else{
                request.getRequestDispatcher("/dept/error").forward(request,response);
            }
        }
    ```

+ 那么这就是add界面的构建

---

#### 6.edit页面的构建

首先，这个页面相对前面的页面会稍稍复杂一些，首先用户点击“edit”跳转至修改的页面，但是这个页面是动态的，所以这里要先对数据库进行查询，然后将可以修改的数据显示在修改框中，用户可以修改然后点击提交按钮，然后表单将数据提交至服务器完成修改，用户跳转至list页面，这里用户第一次跳转的时候，使用的是get请求，而第二次提交数据时，是post请求，所以这里我们可以写在一个DeptEdit类里，然后同时覆盖get和post方法，下面是构建的过程：

+ 首先是点击edit，跳转至修改页面，所以这里我们先把页面进行注册

+ ```xml
    <servlet>
        <servlet-name>edit</servlet-name>
        <servlet-class>com.javaweb.oa.action.DeptEdit</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>edit</servlet-name>
        <url-pattern>/dept/edit</url-pattern>
    </servlet-mapping>
    ```

+ 然后是页面的构建，因为是动态的显示，所以我们要使用list页面相同的方法

+ ```java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String deptno = request.getParameter("deptno");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
    
            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("    <meta charset='UTF-8'>");
            out.print("    <title>Edit</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<form action='" + request.getContextPath() + "/dept/edit' method='post'>");
    
            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from DEPT where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(deptno));
                rs = ps.executeQuery();
                //Print the source table
    
                if(rs.next()){
                    out.print("                deptno<input type='text' name='deptno' value='"+rs.getString("deptno")+"' readonly><br>");
                    out.print("                dname<input type='text' name='dname' value='"+rs.getString("dname")+"'><br>");
                    out.print("                loc<input type='text' name='loc' value='"+rs.getString("loc")+"'><br>");
                }
    
                out.print("    <input type='submit' value='Edit'>");
                out.print("</form>");
                out.print("</body>");
                out.print("</html>");
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
        }
    ```

+ 用户点击提交按钮后，就会向服务器发送修改请求，此时就会使用doPost方法，方法要求对获取的数据对数据库的指定项目进行修改，修改完成后跳转至list页面

+ 对应修改数据库中的数据，我们直接写在doPost方法就行

+ ```java
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
            int count;
            try {
                conn = JDBCUtils.getConnection();
                String sql = "update DEPT set dname = ?, loc = ? where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dname);
                ps.setString(2, loc);
                ps.setString(3,deptno);
                count = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn,ps,rs);
            }
            if (count == 1) {
                request.getRequestDispatcher("/dept/list").forward(request,response);
            }else{
                request.getRequestDispatcher("/dept/error").forward(request,response);
            }
        }
    ```

+ 测试一下，功能很成功

---

#### 7.对于这个项目的总结

虽然这个项目大部分的功能都实现了，但是还是有很多不足的地方：

+ 首先是对与数据库的连接问题，与数据库的连接是非常消耗资源的，但是我们在每个方法中都会去与数据库建立连接，然后释放资源，这样是非常浪费资源的，应该事先创建好资源，需要时取用即可，这是可以优化的地方
+ 然后是动态页面的问题，因为不想用前端的方法去实现动态页面，所以目前来说只能去去打印html代码来实现动态页面的显示，这是非常复杂且繁琐的，并且这样会显著降低代码的可读性
+ 在增删改查中的问题，添加和修改条目的时候，没有进行确认，所以可能导致非法格式的数据录入数据库中
+ 页面跳转的问题，大部分的页面在完成任务后都需要跳转到list界面，因为list的页面只覆盖了doGet方法，资源转发时对于某些post请求目前来说只能覆盖doPost方法去调用doGet方法，应该使用重定向去优化
+ 对于类的注册，当前仅有的几个类在web.xml中的注册都十分繁琐，每一次都需要重复这样的步骤显然开发效率较低
+ 一个简单的CRUD项目就使用了6个Servlet，如果一个复杂的系统以这种方式开发，显然会导致类爆炸

---

### 13.资源的转发和重定向

对于资源的跳转我们有两种方法：

+ 转发
+ 重定向



这两者有什么区别？

+ 转发与重定向代码不同

    + ```java
        request.getDispatcher("转发路径，可以不用加项目名 /dept/list").forward(request, response);//转发
        ```

    + ```java
        response.sendRedirect("转发路径，必须加项目名 /oa/dept/list")//重定向
        ```

+ 请求的域不同

    + 对于转发来说，不管转发多少次，都只有一次请求request，也就是说他们同属一个`请求域`，假设从ServletA转发到ServletB，仍然只有一次请求，虽然页面中是B的内容，但是地址栏上是A的地址，同时他们两个也同属一个请求域，也就说B能访问到A请求域中的资源
    + 对于重定向来说，重定向一次就是两次请求，这也意味着他们是两个不同的请求域，请求域中的资源也不能互相访问

+ 操控请求的主体不同

    + 对于转发来说，每一次转发都是由`Tomcat服务器`来操作的，是服务器内部把请求转发到了另一个Servlet中，把当前的request和response传递到了下一个Servlet中
    + 而对于重定向来说，是服务器重新提供了一个资源地址给浏览器，由`浏览器`主动对地址发起了请求，是由浏览器进行的操作，这是一次全新的请求

+ 使用转发的问题
    + 假设，我们在第一个请求中对数据库中插入了一条数据，然后使用转发到达一个提示成功的页面，此时我们不断的刷新页面，虽然我们一直显示是成功的页面，但是相当于我们不停的在发送第一个请求，此时数据库中会不停的插入数据，这是使用转发存在的一个问题
    + 而重定向不存在这个问题



对于这两者如何去选择？

+ 如果在一次请求中我们向AServlet中的request域中存入了数据，并且我们希望在BServlet中把数据读取出来，`使用转发`
+ 其他情况一律使用重定向

---

### 14.基于注解开发Servlet

首先我们发现通过web.xml配置文件开发效率十分的低下，所以在Servlet3.0及以上版本，提供了注解开发方式，可以将一部分的配置文件写在java代码中，极大的提高了开发的效率，所以目前的开发模式一般是注解+配置文件的形式，下面就来详细了解注解的使用：

+ 首先是关于注解和配置文件的选择

    + 一般来说不会经常变化修改的配置文件建议使用注解，可能会经常修改的建议写到配置文件中

+ 注解的使用，首先我们要在类上用注解标注`@WebServlet`:

    + name属性用来指定类的名字
    + value属性可以用来指定类的访问路径，与urlPatterns不同的是，使用value时连名字都不用写
    + urlPatterns属性用来指定类的访问路径，路径可以有多个
    + loadOnStartUp属性用来指定是否在服务器启动时加载Servlet
    + WebInitParams属性用来指定初始化参数

+ ```java
    @WebServlet(name = "delete", urlPatterns = {"/dept/delete", "/dept/delet1"},
    initParams = {@WebInitParam(name = "name", value = "root"), @WebInitParam(name = "password",value = "root1234")})
    //具体用法展示，但是在实际开发中，我们只要用一个属性就可以了
    @WebServlet("/dept/list")//当注解的属性名是value时可以省略
    //当使用的注解是一个数组时，如果提供多个参数，要使用大括号括住，如果只有一个参数，可以不使用大括号
    ```

---

### 15.使用模板方法改造OA项目

对于之前的项目，我们使用了六个类来解决六个问题，显然这是非常复杂且浪费资源的方法，那么有什么方法去优化呢？

+ 以前是一个类对应一个Servlet类，1000个请求对应1000个Servlet类，导致类爆炸
+ 可以这样做，一个请求对应一个方法，一个业务对应一个Servlet类
+ 比如处理部门业务的类DeptServlet类，处理客户的业务UserServlet类



重构步骤

+ 首先我们要知道，HttpServlet类中是由谁去调用doGet和doPost的，查看源码可以发现，在HttpServlet中是由service方法去调用所有的do系列方法，所以这里我们直接覆盖service方法，由service去调用对应功能的方法

+ ```java
    String name = request.getServletPath();
    if (name.equals("/dept/list")) {
        doList(request,response);
    } else if (name.equals("/dept/add")) {
        doAdd(request,response);
    }else if (name.equals("/dept/edit")) {
        doEdit(request,response);
    }else if (name.equals("/dept/delete")) {
        doDel(request,response);
    }else if (name.equals("/dept/detail")) {
        doDetail(request,response);
    }else if (name.equals("/dept/modify")) {
        doModify(request,response);
    }
    //这里本来可以使用enhanced switch，但是不知道什么原因导致了编译错误
    ```

+ 然后编写对应的方法，传入request和response参数，还有抛出异常

+ ```java
    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
            int count;
    
            try {
                conn = JDBCUtils.getConnection();
                String sql = "update DEPT set dname = ?, loc = ? where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, dname);
                ps.setString(2, loc);
                ps.setString(3,deptno);
                count = ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn,ps,rs);
            }
            if (count == 1) {
    //            request.getRequestDispatcher("/dept/list").forward(request,response);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }else{
    //            request.getRequestDispatcher("/dept/error").forward(request,response);
                response.sendRedirect(request.getContextPath() + "/error.html");
            }
        }
    
        private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String s = request.getParameter("deptno");
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("    <meta charset='UTF-8'>");
            out.print("    <title>Detail</title>");
            out.print("</head>");
            out.print("<body>");
    
            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from DEPT where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, s);
                rs = ps.executeQuery();
                if(rs.next())out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
                out.print("<hr><br>");
                out.print("<input type='button' onclick='window.history.back()' value='Back'/>");
                out.print("</body>");
                out.print("</html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
        }
    
        private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            String deptno = request.getParameter("deptno");
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            int count = 0;
    
            try {
    
                conn = JDBCUtils.getConnection();
                conn.setAutoCommit(false);
                String sql = "delete from DEPT where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, deptno);
                count = ps.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
            if (count == 1) {
    //            request.getRequestDispatcher("/dept/list").forward(request,response);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }else{
    //            request.getRequestDispatcher("/dept/error").forward(request,response);
                response.sendRedirect(request.getContextPath() + "/error.html");
            }
        }
    
        private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            String deptno = request.getParameter("deptno");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
    
            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("    <meta charset='UTF-8'>");
            out.print("    <title>Edit</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<form action='" + request.getContextPath() + "/dept/modify' method='post'>");
    
            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from DEPT where deptno = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,Integer.parseInt(deptno));
                rs = ps.executeQuery();
                //Print the source table
    
                if(rs.next()){
                    out.print("                deptno<input type='text' name='deptno' value='"+rs.getString("deptno")+"' readonly><br>");
                    out.print("                dname<input type='text' name='dname' value='"+rs.getString("dname")+"'><br>");
                    out.print("                loc<input type='text' name='loc' value='"+rs.getString("loc")+"'><br>");
                }
    
                out.print("    <input type='submit' value='Edit'>");
                out.print("</form>");
                out.print("</body>");
                out.print("</html>");
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
        }
    
        private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            String deptno = request.getParameter("deptno");
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
    
            Connection conn = null;
            PreparedStatement ps = null;
            int count;
            try {
                conn = JDBCUtils.getConnection();
                String sql = "insert into DEPT values(?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,deptno);
                ps.setString(2,dname);
                ps.setString(3,loc);
                count = ps.executeUpdate();
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, null);
            }
            if (count == 1) {
                request.getRequestDispatcher("/dept/list").forward(request,response);
            }else{
                request.getRequestDispatcher("/dept/error").forward(request,response);
            }
        }
    
        private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            response.setContentType("text/html");
            String contextPath = request.getContextPath();
            PrintWriter out = response.getWriter();
            out.print("<!DOCTYPE html>");
            out.print("<html lang='en'>");
            out.print("<head>");
            out.print("    <meta charset='UTF-8'>");
            out.print("    <title>List</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<script type='text/javascript'> ");
            out.print("function del(no) {");
            out.print("    if (window.confirm('Are you sure to delete the item?')) {");
            out.print("        alert('deleting please wait a second...');");
            out.print("        document.location ='" + contextPath + "/dept/delete?deptno=' + no");
            out.print("    }");
            out.print("}");
            out.print("</script>");
            out.print("<h1 align='center'>Department</h1>");
            out.print("<hr/>");
            out.print("<table align='center' border='2px' width='50%'>");
            out.print("  <tr>");
            out.print("    <th>Deptno</th>");
            out.print("    <th>Dname</th>");
            out.print("    <th>Loc</th>");
            out.print("    <th>Operation</th>");
            out.print("  </tr>");
    
            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from DEPT";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String deptno = rs.getString("deptno");
                    out.print("  <tr>");
                    out.print("    <td>"+ deptno +"</td>");
                    out.print("    <td>"+rs.getString("dname")+"</td>");
                    out.print("    <td>"+rs.getString("loc")+"</td>");
                    out.print("    <td>");
                    out.print("      <a href='javascript:void(0)' onclick='del(" + deptno + ")')'>Delete</a>");
                    out.print("      <a href='" + contextPath + "/dept/edit?deptno=" + deptno + "'>Edit</a>");
                    out.print("      <a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>Details</a>");
                    out.print("    </td>");
                    out.print("  </tr>");
                }
                out.print("  <tr>");
                out.print("    <td colspan='4'><a href='" + contextPath + "/add.html'>Add item</a></td>");
                out.print("  </tr>");
                out.print("</table>");
                out.print("<hr>");
                out.print("</body>");
                out.print("</html>");
    
    
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtils.close(conn, ps, rs);
            }
        }
    ```

+ 然后使用注解注册

+ ```java
    @WebServlet({"/dept/list", "/dept/add", "/dept/edit","/dept/delete","/dept/detail","/dept/modify"})
    /*
        Also we can use like
        @WebServlet("/dept/*")
        but, the getServletPath() will only return "/dept"
    */
    ```

+ 进行测试，测试结果完全符合预期

---

## 2.JSP

### 1.仅使用Servlet开发web应用的缺陷

+ 使用java编写html/css/js等前端代码的缺点
+ 编写难度大，麻烦
+ 耦合度很高
+ 非常不美观
+ 维护成本高，难于维护

---

### 2.什么是JSP？

+ JSP是一套规范，所有的web容器/web服务器都会遵循这一套规范进行翻译，每一个web容器/web服务器都会内置一个jsp翻译器，当`.jsp`文件被访问时，实际上会自动的翻译生成`_jsp.java`文件，并且自动编译生成`_jsp.class`文件
+ JSP和Servlet的生命周期是一样的，他们完全是一个东西
+ 对JSP调试时，还是要打开JSP文件对应的java文件，检测java代码
+ JSP在第一次访问时都比较慢，第二次比较块，为什么？
    + 第一次访问时，要把jsp文件翻译生成java源文件，java源文件要编译生成class字节码文件，然后通过class文件去生成servlet对象，然后调用`构造方法，init()，service()`方法
    + 第二次直接调用单例对象servlet对象的service方法即可

---

### 3.JSP的基础语法

编写普通的html代码和网页内容

+ 在JSP中直接编写文字，都会被自动翻译到Servlet类的service方法里的out.write("翻译到这里")，被java程序当做字符串输出到网页里

+ 在JSP中编写前端代码，虽然对于JSP来说就是一些普通的字符串，但是一旦输出到网页里就会被浏览器翻译执行

+ JSP的page指令是用来解决乱码问题的，在JSP的第一行写下代码

    + ```jsp
        <%@page contentType="text/html;charset=UTF-8"%>
        ```

    + 表示响应的的内容类型是text/html，响应的字符集是UTF-8

---

在JSP中编写Java程序

+ 在JSP中编写Java程序需要放在`<% Java statement %>`中，在这个符号中的java程序被视为java语句，`会被servlet翻译到service方法中`，所以编写这些java语句相当于我们在方法体内编写java语句，我们应该遵守在方法体内遵守编写代码的规范

+ 这个符号可以在JSP中出现多次，语句的翻译会自上而下的执行

+ 还有一个符号`<%! Java statement %>`，在这个符号中编写的代码会被`翻译到service方法体之外，类体之内`，所以我们可以在这里定义实例变量，方法，静态代码块...，但是这个不建议使用，因为在方法体之外编写实例变量和静态变量都会引起线程安全问题

+ 编写JSP的注释，在符号`<%-- Annotation --%>`的会被认为是注释，不会参与代码的翻译，如果是`<!---->`这种html的注释，同样会被编译到java程序中

+ JSP的输出语句，怎么向浏览器中输出Java变量？

    + ```java
        <%String s = "123";out.print(s)%>
        ```

    + out是JSP中的九大内置对象之一，可以直接拿来用但是只能在service方法中用

+ 下面再介绍一种输出方式`<%= Statement %>`，这个括号的作用是，当我们需要输出带有java变量的语句时，使用这个可以直接将括号内的语句翻译到`out.print(翻译到这里)`

    + ```java
        <%int a = 1;
        int b = 2;
        %>
            
        <%= a + b%>
        ```

+ 那么对应上面的`<%%>`和`<%=%>`我们该如何选择呢，首先我们要明白，前者是负责将java语句翻译到service方法内部的，可以用来定义变量，编写算法等等，但是第二个是当我们需要输出带有java变量且不是纯字符串时的语句时使用的
    + 需要注意的是，如果是输出纯字符串，我们只需要在jsp文件中直接输入即可

---

JSP语法总结

+ JSP直接写字符串
    + out.print("会翻译到这里")
+ <% %>
    + 作为一条条的java语句翻译到service方法体内部，
+ <%! %>
    + 作为java语句翻译到方法体外
+ <%= %>
    + out.print(翻译到这里)
+ <%@page %>
    + page指令可以设置响应的内容类型

---

### 4.使用JSP改造OA项目

使用JSP改造OA项目的步骤：

+ 使用Servlet处理业务，收集数据，使用JSP展示数据
+ 将之前的HTML页面原型全部修改为JSP文件（如果页面中有中文要使用`@page contentType="text/html;charset=UTF-8"`防止乱码），将所有的JSP文件拷贝至web目录下
+ 修改源代码完成页面的流转

