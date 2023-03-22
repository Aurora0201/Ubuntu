[TOC]

# MybatisPlus

## 代码生成器

MyBatis支持根据数据库表生成实体类，控制器层，业务逻辑层，DAO层，XML文件

### 引入依赖

对于SpringBoot3来说需要最新的依赖，并且因为新版本不再内置模板，需要额外添加模板依赖，建议在DAO层添加

```xml
<!--代码生成器-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.5.3</version>
</dependency>
<!--模板-->
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>2.3.32</version>
</dependency>
```



### 编写生成器

首先需要明确一点的是，现代应用大多是采用多模块的管理策略，但是代码生成器只能在一个包下生成所有的代码，所以需要设定共同的父包名，防止后面迁移时发生错误

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mall", "root", "root1234")
                .globalConfig(builder -> {
                    builder.author("Bin JunKai") // 设置作者
                            .fileOverride() // 覆盖已生成文件，新版本会迁移到strategy中
                            .outputDir("/home/binjunkai/mall/mapper/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("top.pi1grim.mall") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/home/binjunkai/mall/mapper/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("users,user_login_history,user_addr,shopping_cart,product_sku,product_params,product_img,product_comments,product,orders,order_item,index_img,category")
                            .entityBuilder()
                            .enableLombok()//启用lombok
                            .disableSerialVersionUID()
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .mapperBuilder()
                            .superClass(BaseMapper.class);//specify BaseMapper
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}

```

+ 其中指定父包名，XML文件的路径和BaseMapper十分重要，配置错误可能会导致生成的代码无法使用
+ 默认的业务层接口名一般为`IService`，可以设置`.formatServiceFileName("%sService"),.formatServiceFileImplName("%sServiceImpl")`来解决
+ 可以启用实体类的Lombok模式，让代码更具灵活性
+ 更多的设置参考[代码生成器配置](https://baomidou.com/pages/981406/)



### 单表CRUD

该框架提供了业务层和DAO层的CRUD接口，我们这里一般使用业务层的作为测试。对于条件的构造，我们使用框架提供的包装类来实现

**查询操作**

```java
@Test
void query() {
    QueryWrapper queryWrapper = new QueryWrapper<>(new Users());
    queryWrapper.eq("username", "aaaa");
    System.out.println(usersService.getOne(queryWrapper));
}
```

**删除操作**

```java
@Test
void remove() {
    QueryWrapper queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username","tom123123");
    usersService.remove(queryWrapper);
}
```



更多条件构造参考[条件构造器](https://baomidou.com/pages/10c804/#abstractwrapper)