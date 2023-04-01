[TOC]

# SpringBoot

## 1.JavaConfig入门

### 1.XML和JavaConfig

**JavaConfig**

+ Springboot引入了JavaConfig来替代XML配置文件，使得能使用纯Java配置Spring容器



**JavaConfig的使用**

+ 使用JavaConfig需要使用下面两个注解

    + *@Configuration*：将类标注为配置类
    + *@Bean*：bean的注入

+ 下面用一个例子来说明

    + 创建类*SpringConfig*，并使用注解*@Configuration, @ComponentScan*标注

    + 创建类*Tiger*，并提供*setter, getter*方法

    + 在*SpringConfig*类中编写下面的内容

        ```java
        @Configuration
        @ComponentScan("top.pi1grim")
        public class SpringConfig {
            @Bean
            public Tiger tiger() {
                Tiger tiger = new Tiger();
                tiger.setName("Ma ShiWen");
                tiger.setType("DongBei Hu");
                return tiger;
            }
        }
        ```

        这样，我们就成功创建了一个*Tiger*类



### 2.导入外部XML配置文件

当我们需要使用XML和JavaConfig混合配置时，可以使用注解*@ImportResource("classpath:")*来引入外部配置文件，作用与`<import resource="">`一致



### 3.读取属性配置文件

**引入外部属性文件**

+ 我们可以将一些bean的注入信息写在外部的*.properties*文件中，然后通过注解*@PropertySource*引入

+ 下面通过一个例子来表示

    + 在*SpringConfig*类上添加注解，*@PropertySource("classpath:")*指向*.properties*文件

    + 创建类*Student*，提供*setter, getter*方法，使用*@Component*标注

    + 在*Student*类中直接对属性注入

        ```java
        @Value("${student.name}")
        private String name;
        @Value("${student.age}")
        private int age;
        ```

        这样就完成了从外部属性配置文件的信息的读取并注入



## 2.SpringBoot入门

### 1.SpringBoot项目的创建

+ 打开新建模块，选择*Spring Initializr*，首先将*server URL*换成*start.springboot.io*，因为这个是国内的地址，会快很多
+ 输入我们的项目名，选择JDK版本，打包方式，然后点击下一步
+ 这里可以添加我们需要的依赖，在这个例子中我们选*Web -> Spring Web*

这样一个简单的Spring Web项目就完成了



### 2.SpringBoot项目的运行

**包的结构**

+ 我们可以看到有下面的文件结构

    + *.mvn*
    + *src*
    + *HELP.md*
    + *mvnw*
    + *mvnw.cmd*

    其中三个*mvn*开头的文件都是用来运行maven的，除了*src*都可以删除

+ 打开*src*我们可以看到有下面的的结构

    + *main*

        + *java*
            + *top.pi1grim*
                + *application*
                    + *WebApplication.java*

    + *resources*
        + *static*
        + *templates*
        + *application.properties*

+ 先大概介绍一下，*WebApplication.java*是SpringBoot提供给我们运行的主方法，我们需要通过主方法来启动web项目

+ *static*文件夹是用来装载静态资源的，如*html, css, js, png*等等资源都放在这里

+ *template*文件夹是用来装模板的，可以用来当做视图等等

+ *application.properties*是*SpringBoot*的配置文件



**项目运行**

+ 首先注意，*Application.java*必须放在与子包同级的位置，否则可能出现web服务启动失败，举个例子，所有的子包都是*top.pi1grim.\**，所以我们必须把*Application.java*放在*top.pi1grim*包下

+ 创建一个子包*controller*，创建一个*Controller*类，提供一个方法如下

    ```java
    @Controller
    public class HelloSpringBoot {
        @RequestMapping("/hello")
        @ResponseBody
        public String hello() {
            return "hello SpringBoot!";
        }
    }
    ```

+ 执行*Application.java*中的主方法，启动后进入*localhost:8080/hello*如果出现了则表示启动成功



### 3.Application类浅析

**注解**

+ 我们先从注解入手，点开*@SpringBootApplication*，我们可以发现，这个注解的内部是这样的

    ```java
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(
        excludeFilters = {@Filter(
        type = FilterType.CUSTOM,
        classes = {TypeExcludeFilter.class}
    ), @Filter(
        type = FilterType.CUSTOM,
        classes = {AutoConfigurationExcludeFilter.class}
    )}
    )
    ```

+ 其中使用了*@SpringBootConfiguration, @EnableAutofiguration, @ComponentScan*

+ 前面两个都与配置文件有关，重点讲一下第三个注解

+ 这个*@ComponentScan*注解只会扫描同级的包和子包，这就是为什么我们在上面如果将*Application.java*放错位置会导致Web服务启动失败



## 3.SpringBoot配置文件

### 1.默认配置文件

**SpringBoot支持的配置文件**

+ 在上面的文件结构中，在*resources*文件夹下，有一个*application.properties*文件，这个文件是SpringBoot的默认配置文件



**SpringBoot支持的文件**

+ 在最近的发展中，*.yml*文件的出现使得配置文件的配置信息更清晰，正在逐渐成为主流，SpringBoot也支持这种文件格式作为配置文件
+ 但是，在SpringBoot中默认读取的必须是*application.properties*文件



### 2.使用配置文件

**.properties文件配置**

+ 现在我们想要配置tomcat服务器的端口，访问路径，只需要像下面这样设置即可

    ```properties
    server.port=8082
    server.servlet.context-path=/context



**.yml文件配置**

+ 首先要明确的一点，yml对缩进要求十分严格，因为yml文件根据缩进来判断层级

+ 同样的配置端口，访问路径

    ```yaml
    server:
      port: 8082
      servlet:
        context-path: /context
    ```



**自定义配置文件**

+ 因为SpringBoot配置文件是一个*.properties*文件，所以我们可以在里面自定义键值对

    + 那如何从键获取值呢，像上面的*Student*类一样，在属性上使用*@Value("${}")*注解注入，花括号中填写的就是键
    + 只要是配置文件中的键值对，我们都可以通过上面的访问来获取值

+ 使用*@ConfigurationProperties*注解来自动获取配置信息并注入

    + 提供一个类*School*，提供*setter, getter*方法，使用*@Component*注解标注

    + 在*.properties*文件中定义一系列的键值对，格式为

        ```properties
        prefix.类中属性的名字=value
        ```

    + 使用注解*@ConfigurationProperties(prefix = "prefix")*标注*School*类

    + 这样的话在放入容器的同时会自动进行属性的注入



### 3.多环境配置文件

**多环境配置文件的使用**

+ 在实际的开发中，对于一个项目会有多个不同阶段开发-测试-上线，不同的运行环境有不同的配置，常见的配置就有三种：

    + *application-dev*
    + *application-test*
    + *applicatoin-online*

+ 使用多环境配置文件的前提就是，首先命名一定要是*application-`name`*，文件可以是*properties, yml*，并且都放在*resources*文件夹下

+ SpringBoot框架默认读取的是*application.properties*，要切换配置文件只需要在默认配置文件中添加下面的语句

    ```properties
    spring.profiles.active=name
    ```

    

### 4.获取容器

在平常的测试中，我们如果想从容器中拿到对象进行测试，只需要用容器对象*applicationContext*接收*SpringApplicatoin.run()*的返回值即可



## 4.SpringMVC的集成

### 1.拦截器的创建

**回顾SpringMVC的拦截器创建**

+ 首先提供一个类实现*HandlerInterceptor*中的方法
+ 在SpringMVC配置文件中注册拦截器



**SpringBoot拦截器创建**

+ 同样创建一个类实现*HandlerInterceptor*接口
+ 创建一个类实现*WebMvcConfigurer*接口，并使用*@Configuration*注解标注
    + 这个接口中有诸多的方法，都是用来代替SpringMVC的配置文件的，我们这里就要实现其中的*addInterceptors()*方法

+ 实现这个方法后，我们需要手动的创建拦截器实例，然后将拦截器注册，并设置过滤的路径，如下

    ```java
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new HelloInterceptor();
        String[] path = {"/context/**"};
        String[] excludePath = {"/context/user"};
        registry.addInterceptor(interceptor)
                .addPathPatterns(path)
                .excludePathPatterns(excludePath);
    }
    ```

+ **需要注意的是，过滤的path不会考虑服务器的context路径，路径的匹配会从切掉context路径后开始**



## 5.三大框架的集成

### 1.集成三大框架

**依赖的引入**

使用*mybatis initializr*可以简化我们的依赖引入操作，只需要勾选下面的选项即可

+ *Spring Web*
+ *MySQL Driver*
+ *Mybatis Framework* 



**类的编写**

+ 先提供bean类*User*
+ 编写Mapper接口
+ 编写Mapper文件
+ 提供*Service*类
+ 编写*Controller*类



**配置文件**

+ 在*application.xml*文件中添加下面的语句

    ```properties
    #数据库连接配置
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/mybatis
    spring.datasource.username=root
    spring.datasource.password=root1234
    #包别名扫描
    mybatis.type-aliases-package=top.pi1grim.model
    #静态资源访问
    spring.mvc.static-path-pattern=/static/**
    #设置MyBatis日志输出
    mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
    ```



### 2.Mapper注解的使用

**Mapper上使用注解的两种方法**

+ 在Mapper接口上使用*@Mapper*注解
+ 在*Application*类上使用*@MapperScan*，指定*basePackage = ""*，相当于声明了一个组件扫描器



### 3.事务功能的实现

+ 在服务类上添加*@Transactional*注解即可



## 6.RESTful

### 1.REST的概念

REST是一种互联网软件架构设计的风格，但他并不是标准，它只是提出来一组客户端和服务器交互时的架构设计和理念，基于这种原则设计的接口可以更有层次，更简洁，下面举出一个例子来说明

+ 通常我们见到的url，*localhost/student?class=4&sid=23*
+ 使用REST风格的url，*localhost/student/4/23*



### 2.REST接口编写

首先要使用两个注解*@ResController, @VarialePath*，前者是一个复合注解，表示使用*@Controller, @ResponseBody*，后者则是能通过url来获取参数，下面给出一个简单的例子

```java
@GetMapping("/result/{mail}/{sort}")
    public List<Data> getAll(HttpServletResponse response, @PathVariable String mail,@PathVariable String sort) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    return dataService.showAllData(mail, sort);
}
```



### 3.使用方式

RESTful风格的接口使用名词来区分url，用请求方式来区分动作，假如我想通过一个url来完成添加和删除的操作，应该如下编写API

```java
@GetMapping("/{id}")
public void GetGoods(@PathVariable int id) {

}

@PostMapping("/{id}")
public void addGoods(@PathVariable int id) {

}
```

类似的请求方式如下

+ GET 获取资源
+ POST 添加资源
+ DELETE 删除资源
+ PUT 修改资源



## 7.整合Redis

前提是系统中已经安装了redis

### 1.引入redis的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```



### 2.Redis的配置

默认情况下不用配置



### 3.Redis的使用

Redis提供了一个对象StringRedisTemplate用于执行Redis的各种操作，下面提供一个业务层的缓存例子

```java
@Resource
private StringRedisTemplate template;

@Override
public ProductDetailDTO productDetail(int productId) {
    //先从Redis查询
    String productInfoString = (String) template.boundHashOps("product").get(String.valueOf(productId));
    //有就直接返回
    if(productInfoString != null) return JSON.parseObject(productInfoString, ProductDetailDTO.class);
    //没有就从数据查询
    Product product = productMapper.selectOne(new LambdaQueryWrapper<Product>().eq(Product::getProductId, productId));
    //如果数据库没有就证明不存在商品
    if (product == null) {
        return null;
    }
    ProductDetailDTO productDetail = ProductDetailDTO.builder()
        .product(product)
        .productImgs(productImgMapper.selectProductImgById(productId))
        .productSkus(productSkuMapper.selectList(new LambdaQueryWrapper<ProductSku>().eq(ProductSku::getProductId, productId)))
        .build();
    //有就放入Redis中，并设置过期时间
    template.boundHashOps("product").put(String.valueOf(productId), JSON.toJSONString(productDetail));
    template.boundHashOps("product").expire(1, TimeUnit.HOUR);
    //返回商品信息
    return productDetail;
}
```



### 4.Redis缓存的数据

Redis缓存的数据一般来说有下面的特点：

+ 写的次数较少或者几乎不写，但是读的次数相当多的数据
+ 对于数据一致性要求较低，读次数较多的数据
