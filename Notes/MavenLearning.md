[TOC]

# 1.Maven

## 1.Maven初步



### 1.Maven是什么？

+ Maven是由apache开发的一款项目管理工具



### 2.传统项目开发遇到的问题

+ 很多模块，模块之间的关系，手工管理关系十分繁琐
+ 需要很多的jar文件，需要手工从网络上下载并导入
+ 需要管理jar包的版本
+ 管理jar包之间的依赖关系
    + a.jar需要b.jar才能运行，这种关系叫做依赖关系



### 3.Maven管理项目的好处

+ maven可以管理jar文件
+ 自动下载jar包，文档和源代码
+ 管理jar包的依赖，自动下载所需的jar包
+ 管理jar包的版本
+ 编译和打包文件
+ 部署项目



### 4.Maven支持项目的构建

+ 清理，把之前编译的东西清理掉，为新的代码编译做准备
+ 编译，将源代码编译为`.class`文件，maven可以一次性编译大量的源文件
+ 测试，maven可以测试源代码是否正确，maven可以同时执行多个测试代码，批量对功能进行测试，并生成测试的结果
+ 打包，maven可以把项目中的`.class`文件打包成`.jar`文件或者`.war`文件

---

## 2.Maven核心概念

### 1.Maven约定的目录结构

+ 什么是约定的目录结构？
    + 约定是大家都默认遵守的一个规则，不是强制的，但是一般来说都要遵守
+ 每一个Maven在项目在磁盘中都是一个文件夹，下面是Maven的结构

> Hello/
>
> ​	---/src
>
> ​	------/main   #放置Java主程序和配置文件
>
> ​	---------/java #放程序包和Java文件
>
> ​	---------/resources #需要使用的配置文件
>
> ​	------/test   #放置测试代码文件和代码（可以没有）
>
> ​	---/pom.xml   #maven的核心文件（maven项目必须有）

+ 值得注意的是，在resources文件夹下存放的文件相当于直接放在根目录下



### 2.使用朴素的方法创建一个maven项目

+ 首先在`/Hello`文件夹内创建符合规定的一个目录结构，并创建`pom.xml`文件，`/Hello`文件夹就是这个项目的根目录

    + ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apche.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <groupId>com.hello</groupId>
            <artifactId>ch01-maven</artifactId>
            <version>1.0-SNAPSHOT</version>
        </project>
        ```

+ 在java文件夹下随便创建一个含有main方法的Java文件

+ 回到根目录下，在terminal中使用`mvn compile`命令进行编译



### 3.解决下载过慢的问题

+ 来到`$MAVEN_HOME/conf`下，找到`settings.xml`文件中的`<mirrors>`

+ 添加以下内容

    + ```XML
        <mirror>
            <id>aliyunmaven</id>
            <mirrorOf>*</mirrorOf>
            <name>阿里云公共仓库</name>
            <url>https://maven.aliyun.com/repository/public</url>
        </mirror>
        ```

+ 即可将下载地址切换到阿里云



### 4.仓库

+ 仓库是什么？
    + 仓库是存放maven所使用的的jar和项目中使用的jar存放的
+ 仓库的分类
    + 本地仓库，位于我们计算机磁盘上的仓库
    + 远程仓库，资源位置位于网络上的仓库，只有联网才能使用
        + 中央仓库，最权威的，所有开发者共同使用的仓库
        + 中央镜像仓库，中央仓库的备份，减轻中央仓库的压力
        + 私服，个人或者公司搭建的，只能在内部使用
+ 仓库的使用，maven仓库的使用不需要人为参与
    + 开发人员需要mysql驱动-->maven首先在本地仓库查找-->私服-->镜像-->中央仓库
+ 在Ubuntu配置本地仓库时，不要配置在没有读取权限的目录，尽量配置在用户目录下

### 5.pom文件

+ 什么是pom文件？
    + Project Object Model 项目对象模型
    + pom文件是maven的灵魂，maven把项目的结构和内容抽象成一个模型，在xml文件中进行声明，方便模型的构建和描述
+ pom中的参数
    + modelVersion
        + Maven模型的版本，对于Maven2和Maven3来说，他们只能是4.0.0
    + groupId
        + 组织Id，一般是公司域名的倒序，或者域名的倒序+项目名
            + com.baidu,org.apache
            + com.baidu.appolo
    + artifactId
        + 项目名称，对应groupId中的子项目
    + version
        + 项目的版本号，通常使用三位数字标识，如1.0.0
        + 在开发中的版本一般在后面加上`-SNAPSHOT`，表示不稳定版本
    + packing
        + 打包后的压缩文件扩展名，默认是.jar，可以不写
    + dependency
        + 用来为项目添加依赖
    + propertities
        + 用来配置项目的一些属性
    + build
        + 用来设置maven编译时相关的设置，如jdk的版本信息等

### 6.坐标

+ Maven把任何一个插件都作为仓库中的一个项目进行管理，用三个向量组成的坐标来表示，坐标是一个项目的唯一标识符
    + groupId：组织名，通常是公司域名倒序+项目名
    + artifactId：模块名，通常是工程名
    + version：版本号
+ 在互联网中，我们可以用唯一标识符来查找一个项目

#### 使用坐标在网络上[查找](https://mvnrepository.com/)项目

### 7.依赖

+ 我们可以在pom文件中使用`dependencies,dependency`标签来添加项目依赖

+ 比如说我们的项目需要添加mysql的驱动，首先在pom文件中添加以下内容

    + ```xml
        <dependencies>
        
        </dependencies>
        ```

    + 然后通过上面的网站查找我们需要的依赖，并放入`<dependencies>`标签中

    + ```xml
        <dependencies>
            <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
            <dependency>
                <groupId>com.mysql</groupId>
                <artifactId>mysql-connector-j</artifactId>
                <version>8.0.32</version>
            </dependency>
        </dependencies>
        ```

    + 这样就引入我们的项目依赖了
    
+ 依赖的范围

    + 依赖中还有一个子标签`<scope></scope>`，他代表的是这个依赖的作用范围有下面的取值
    + compile`(default)`,test,provided


|                    | compile | test | provided |
| :----------------: | :-----: | :--: | :------: |
|  对主程序是否有效  |    Y    |  F   |    Y     |
| 对测试程序是否有效 |    Y    |  Y   |    Y     |
|    是否参与打包    |    Y    |  F   |    F     |
|    是否参与部署    |    Y    |  F   |    F     |



### 8.常用命令

+ mvn clean 删除原来编译和测试的`target`目录，但是已经`install`到仓库里的包不会删除
+ mvn compile 编译`main`目录下的程序
+ mvn test-compile 编译`test`目录下的程序
    + compile命令生成的文件都会存储到`target`目录下
+ mvn test 测试程序并生成`surefire-reports`目录存储测试结果
+ mvn package 打包主程序，编译，编译测试，测试并按照pom中的配置打包生成jar包或者war包
+ mvn install 安装主程序，把工程打包并保存到本地仓库中
+ mvn deploy 部署主程序，把本工程打包，保存到本地仓库和私服中并部署到web项目中



### 9.测试

+ maven中怎么测试程序？

    + 单元测试使用的是junit，junit是一个专门的测试框架，他测试的是类中的方法，每一个方法都是独立测试的

    + maven借助单元测试，批量的测试类中的方法是否符合预期

+ 怎么使用测试？

    + 首先引入测试框架的依赖

    + ```xml
        <!-- https://mvnrepository.com/artifact/junit/junit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        ```

    + 在项目的`/src/test/java`下，创建测试程序

        + 测试类的名称 Test + 被测试类
        + 测试方法的名称 Test + 被测方法名
            + 测试方法定义时，必须是`public`，返回值为`void`，在方法名上加上`@Test`

    + 例如我们要测试`Hello`

        + 创建测试类`TestHello`

        + ```java
            public class TestHello{
            	@Test
                public void testAdd(){
                   	//测试代码
                    
                    //get Actuall value
                    int act = Add();
                    //get expect value
                   	int exp = 123;
                    //verify and get the result
                    Assert.assertEquals(exp, act);
                }
            }
            ```




### 10.属性

+ 在pom文件中我们可以使用`<properties>`来设置Maven中的一些属性，比如编译jdk的版本，编码格式等等
+ 设置全局变量
    + 使用尖括号括起来的标识符可以作为全局变量，比如`<junit.version>4.0.2</junit.version>`
    + 然后使用`${junit.version}`调用



### 11.资源插件



---

## 3.在IDEA使用Maven

### 1.配置IDEA的Maven环境

+ 虽然IDEA内置了Maven，但是一般不使用IDEA的Maven，因为使用自己的Maven可以方便我们对配置文件的修改
+ Current project配置过程
    + 首先打开File-->Settings-->Build,Excution,Deployment-->Build Tools-->Maven
    + 修改Maven home path为本地Maven根目录
    + 修改User setting file为本地Maven根目录下的`/conf/settings.xml`
    + 然后点击Maven标签下的Runner标签在VM Options中添加`-DarchetypeCatalog=internal`，可以让maven从本地寻找模型，加快maven速度
    + 最后修改JRE中为我们的JDK根目录即可
+ New project配置过程
    + 首先打开File-->New Project Setup-->Settings for New Projects
    + 然后照着上面的步骤操作一次即可



### 2.使用IDEA创建第一个Maven项目

使用模板创建maven项目

+ 首先，我们新建一个项目，在项目中新建一个模组，在创建模组的界面我们要在`Generator`选项中选择`Maven Archetype`
+ 然后在`Name`中填入我们的模块名，它会作为我们的`artifactId`
+ 选择正确的JDK
+ 在`Archetype`中选择`quickstart`
+ 在`Advance Settings`中可以设置[坐标](#6.坐标)
+ 等待生成完成即可



创建纯净的maven项目

+ 一般来说使用模板比较具有局限性，所以我们可以在创建`Module`时，选择普通module，输入模块名后，在下面有一个maven的选项，选择maven，然后点击创建，就可以得到一个纯净的maven项目了

### 3.使用IDEA出现的问题

+ 使用maven时即使引入了依赖但是却无法找到包
    + 清除IDEA的缓存，重新启动即可
+ 在创建maven项目后，无法`reload maven project`
    + 初步猜测可能是上面[配置](#1.配置IDEA的Maven环境)时没有配置完全，需要同时在Settings和New Project Settings配置完全
    + 可能存在配置错误，检查配置路径和配置文件，同时仓库的路径要有读取权限，在linux下配置在系统文件中可能会出现无法导入依赖
+ 创建web项目时，使用的依赖为`jakarta.servlet 4.0.0`但是在源码中却不能引入`jakarta`的包
    + 使用`javax.servlet.*`引入包
+ tomcat10建议使用`jakarta.servlet 5.0.0`



## 4.Maven多模块管理

### 1.创建父工程

+ 随便创建一个maven项目
+ 把src文件夹删除
+ 打包方式设置为pom