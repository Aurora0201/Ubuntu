[TOC]

# SpringDoc

## 1.SpringDoc

spring doc 是基于OpenApi 3的API文档自动生成库，可以配合Swagger ui使用



## 2.使用方法

在Spring Boot 3以上的版本，需要使用Spring doc 2以上的版本

**引入依赖**

```xml
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.0.4</version>
   </dependency>
```

只要引入这个依赖就可以自动的配置OpenApi和Swagger ui，只要访问`localhost:8080/swagger-ui/index.html`即可



**自定义设置**

我们可以创建一个JavaConfig类来对spring doc进行设置，需要注解`@Configuration`



**具体设置**

spring doc采用了组的策略来管理API，只需要指定组名，路径和过滤条件即可

```java
  @Bean
  public GroupedOpenApi publicApi() {
      return GroupedOpenApi.builder()
              .group("springshop-public")
              .pathsToMatch("/public/**")
              .build();
  }
  @Bean
  public GroupedOpenApi adminApi() {
      return GroupedOpenApi.builder()
              .group("springshop-admin")
              .pathsToMatch("/admin/**")
              .addMethodFilter(method -> method.isAnnotationPresent(Admin.class))
              .build();
  }

```



**Swagger ui设置**

我们可以对Swagger ui的标题，联系信息等做出一定的设置

```java
  @Bean
  public OpenAPI springShopOpenAPI() {
      return new OpenAPI()
              .info(new Info().title("SpringShop API")
              .description("Spring shop sample application")
              .version("v0.0.1")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
              .externalDocs(new ExternalDocumentation()
              .description("SpringShop Wiki Documentation")
              .url("https://springshop.wiki.github.org/docs"));
  }
```



## 3.常用注解

+ `@Tag`类注解，用来标注Controller类中API的总体描述，如*用户接口*
+ `@Parameter`用来指定请求的参数名和描述
+ `@ApiResponse`对相应对象的描述
+ `@Schema`可以对Model类和类中的字段进行解释描述
