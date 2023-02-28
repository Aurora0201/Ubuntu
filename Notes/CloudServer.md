[TOC]

# 1.轻量级云服务器

## 1.使用亿速云轻量级服务器

### 1.购买服务器

在亿速云官网购买即可



### 2.管理服务器

亿速云右上角管理面板 -> 轻量级服务器 -> 管理 进入服务器的详情界面：

+ 安装Ubuntu-20.04系统



### 3.远程连接服务器

远程连接服务器使用的是SSH连接，这里使用的是工具有：

+ Xshell 个人版
+ Xftp 个人版



配置远程连接的ip（公网ip）和登录服务器的账号和密码即可



### 4.安装MySQL

使用apt安装MySQL

+ 运行`apt update`更新列表

+ 运行`apt install mysql-server`即可安装MySQL
+ 运行`systemctl status mysql`查看MySQL是否安装成功



MySQL8.0配置

+ 安装完成后，初次登录MySQL，`mysql -uroot -p`，此时是没有密码的，直接回车即可
+ 设置密码`ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password by 'root1234';`将密码设置为`root1234`

+ 安装MySQL安全，运行`mysql_secure_installation`，然后会出现下面的信息

  + would you like to setup VALIDATE PASSWORD plugins? 是否安装密码管理插件？
    + 选择n

  + new password | re-enter new passrod 输入新密码 | 重新输入新密码 
    + 输入上面我们设置过的密码
  + Remove anonymous users? 移除匿名账户？
    + 选择n
  + Disallow root login remotely? 不允许root远程访问？
    + 为了方便管理还是让root可以远程访问，选择n
  + Remove test database and access to it？ 移除test数据库和访问权限？
    + 选择y
  + Reload privilege tables now? 立刻刷新表权限?
    + 选择y

+ 此时执行`mysql -uroot -proot1234`，登录数据库测试，登录成功即可



### 5.配置MySQL远程连接

想要远程连接数据库还需要进行配置

+ 修改表权限，在数据库mysql中，user表存储了root用户能被访问的范围，这里我们先修改数据库，先登录数据库

  ```mysql
  use mysql #输入以下语句，进入mysql库
  
  update user set host='%' where user ='root'; #更新域属性，'%'表示允许任意IP地址访问
  
  flush privileges; #执行以上语句之后再执行，刷新权限
  
  GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'WITH GRANT OPTION; #再执行授权语句
  ```

+ 修改mysql配置文件，修改`vim /etc/mysql/mysql.conf.d/mysqld.cnf`，注释掉`bind-address = 127.0.0.1`



### 6.使用Navicat连接MySQL

连接远程服务器不能像连接本地服务器一样，要先通过SSH连接远程服务器，再作为服务器中的本地应用访问MySQL

+ 配置SSH远程连接，在新建连接的面板中选择SSH连接，配置远程连接的主机ip，账户名，密码
+ 进入基本配置界面，mysql的连接配置为localhost，配置登录的用户名，密码即可



可能存在的问题：

+ 使用远程来连接是报错`The server key has changed`，代表远程数据库数据发生改变
  + 删除本机用户目录下的`/.ssh/known_hosts`文件即可



### 7.配置JDK

配置Java环境

+ 将java安装包解压到指定位置，如`/usr/local/java17`

+ 配置环境变量`sudo vi /etc/profile`，在最上面添加下面的内容

  ```
  export JAVA_HOME=/usr/local/java17
  export PATH=$JAVA_HOME/bin:$PATH
  export CLASSPATH=.:$JAVA_HOME/lib
  ```

+ 保存后退出，执行`source /etc/profile`，刷新配置

+ 验证Java是否安装成功，运行`java -version`，终端出现java版本代表安装成功



### 8.配置Tomcat

配置轻量级Web容器

+ 解压Tomcat到指定的位置，如`/usr/local/tomcat10`

+ 配置环境变量`sudo vi /etc/profile`，在顶部添加下面的内容

  ```
  export CATALINA_BASE=/usr/local/tomcat10
  ```

+ 刷新环境变量，`source /etc/profile`

+ 到安装目录`/bin`下，运行`startup.sh`，如果运行成功则表示安装成功



## 2.使用腾讯云服务器

### 1.购买，管理服务器

类似亿速云服务器



### 2.服务器设置

并不像亿速云服务器，腾讯云服务器默认关闭了root用户的远程登录，为了解决后续配置麻烦，这里直接使用root用户登录，但是要先进行配置：

1. 使用 ubuntu 帐户登录轻量应用服务器。

2. 执行以下命令，设置 root 密码。

```bash
sudo passwd root
```

3. 输入 root 的密码，按 **Enter**。
4. 重复输入 root 的密码，按 **Enter**。 返回如下信息，即表示 root 密码设置成功。

```bash
passwd: password updated successfully
```

5. 执行以下命令，打开 `sshd_config` 配置文件。

```bash
sudo vi /etc/ssh/sshd_config 
```

6. 按 **i** 切换至编辑模式，找到 `#Authentication`，将 `PermitRootLogin` 参数修改为 `yes`。如果 `PermitRootLogin` 参数被注释，请去掉首行的注释符号（`#`）。如下图所示：

![img](https://qcloudimg.tencent-cloud.cn/image/document/565ffca41a52b21db17020dfc4d72b20.png)





7. 找到 `#Authentication`，将 `PasswordAuthentication` 参数修改为 yes。如下图所示：

**说明**：若 `sshd_config` 配置文件中无此配置项，则添加 `PasswordAuthentication yes` 项即可。



![img](https://qcloudimg.tencent-cloud.cn/image/document/8a500f485cabb0f1e0d227f983b359aa.png)





8. 按 **Esc**，输入**:wq**，保存文件并返回。
9. 执行以下命令，重启 ssh 服务。



```bash
sudo service ssh restart
```

10. 参考[使用远程登录软件登录 Linux 实例](https://cloud.tencent.com/document/product/1207/44578)，并使用以下信息登录 Ubuntu 轻量应用服务器：

**用户名：**root

**登录密码**：在步骤2中已设置的密码



### 3.配置JDK，Tomcat，MySQL

如上配置即可



### 4.配置公网ip访问Tomcat

修改配置文件

+ 修改配置文件`/tomcat/conf/server.xml`，将开头这一段的`port`改为`80`即可

  ```xml
  <!-- A "Connector" represents an endpoint by which requests are received
           and responses are returned. Documentation at :
           HTTP Connector: /docs/config/http.html
           AJP  Connector: /docs/config/ajp.html
           Define a non-SSL/TLS HTTP/1.1 Connector on port 8080
      -->
      <Connector port="80" protocol="HTTP/1.1"
                 connectionTimeout="20000"
                 redirectPort="8443" />
      <!-- A "Connector" using the shared thread pool-->
  ```

+ 检查防火墙，是否允许TCP协议，80端口通过，没有则添加自定义规则

+ 从网站上测试即可



### 5.跨域访问问题

使用前端接受后端响应的json时，出现了跨域访问问题，具体原因不明，但是目前使用下列解决方案：

+ 添加响应头

  ```java
  response.addHeader("Access-Control-Allow-Origin","*");//允许所有来源访同
  ```

+ 在配置文件中添加过滤器，`tomcat/conf/web.xml`中在过滤器定义位置下添加下面的内容

  ```xml
  <filter>
    <filter-name>CorsFilter</filter-name>
    <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>CorsFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>
  ```

  