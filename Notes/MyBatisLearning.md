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
