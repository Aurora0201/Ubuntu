# 1.Nginx

## 1.Nginx初步

### 1.安装Nginx

在Ubuntu22.04中，我们使用两条命令就可以安装Nginx

+ `sudo apt update`
+ `sudo apt install nginx`



### 2.Nginx配置文件

**配置文件位置**

+ 使用apt命令安装的nginx的默认配置文件在*/etc/nginx*中
+ 默认配置文件为*nginx.conf*



**配置文件结构**

图片

+ 配置文件的层级如上，*main*就是全局域，下面的*event, http*是他的下一级，*http*中还有下一级*server*..以此类推



**配置文件的使用**

+ 打开配置文件，我们发现最顶头的有几行配置信息，这些就是*main*的配置信息，我们首先把*user*改成*user root;*
+ nginx的配置信息是按照*配置项 配置值;*的形式来设置的
+ 某些特殊的配置如*server, mail*，需要使用*{}*来括起来，这就是配置块



### 3.第一个nginx服务

**目标**

+ 实现本地静态资源访问



**配置信息**

+ 首先打开刚才的配置文件，在*http*块中添加下面的信息

    ```
    server{
        listen 9999;
        server_name localhost;
    
        location /xixi {
            alias /home/binjunkai/www;
            index index.html;
        }
    
        location /images {
            root /home/binjunkai/www;
        }
    }
    ```

+ 上面两条配置信息的意思为：

    + 使用*9999*端口，当没有指定时，默认使用80端口
    + 可以从*localhost*访问nginx，**对于每个*server*来说，必须指定*server_name，否则无法访问***

+ *location*配置块配置了静态资源的访问地址，这里我们需要区分*root, alias*的区别：

    + *location path*在用户访问时，会使用*path*进行匹配
    + 使用*root*时，当匹配成功后，会将*root*值拼接在*path*的前面来访问本地资源
    + 使用*alias*时，匹配成功后，则会将*path*替换为*alias*的值

+ 这个例子中我们将静态资源存放的结构如下

+ > *home/*
    >
    > + *binjunkai/*
    >     + *www/*
    >         + *index.html*
    >         + *images/*
    >             + *1.png*



**访问资源测试**

+ 此时我们在浏览器上直接访问*localhost:9999/xixi*会跳转到我们的*index*页面，访问*localhost:9999/images/1.png*则会得到我们的图片
+ **注意:**静态资源最好存放在用户文件夹下，不然会出现资源没有访问权限的错误



### 4.第一个nginx代理服务

**目标**

+ 使用nginx反向代理，将请求转发到本地的tomcat服务器



**配置文件修改**

+ 创建一个新的*server*配置块

    ```
    server{
        server_name localhost;
        location / {
            proxy_pass http://localhost:8080;
        }
    
        location ~ \.(gif|jpeg|png)$ {
            root /home/binjunkai/www/images;
        }
    }
    ```

+ 上面的配置信息代表：

    + 使用默认的80端口
    + 当访问*.git|.png|.jpeg*为结尾的资源时则使用本地的静态资源路径
    + 其他路径则直接转发到*localhost:8080*的服务器上



**代理服务测试**

+ 首先启动*tomcat*服务器，并且能通过localhost:*8080/static/index.html*访问到我们的页面
+ 直接访问*localhost/static/index.html*如果能访问到我们的*tomcat*页面就表示成功了



### 5.使用nginx管理Flask微服务

**安装依赖**

+ 安装*flask*依赖`pip install flask`
+ 安装*uwsgi*依赖`pip install uwsgi`



**编写Python代码**

+ 如下

    ```python
    from flask import Flask
    app = Flask(__name__)
    
    app.route("/python/api")
    def hello():
        return 'hello world'
    ```



**nginx配置**

+ nginx需要通过*uwsgi*与Python通信，所以要导入模块

    ```
    location /python {
    	include uwsgi_params;
    	uwsgi_pass 127.0.0.1:3031
    }
    ```

+ 上面的配置表示，引入了nginx自带的*uwsgi*模块，同时将*/python*开头的路径转发到*127.0.0.1:3031*地址上



**uwsgi的启动**

+ 使用命令`uwsgi --socket 127.0.0.1:3031 --wsgi-file 1.py --callable app --processes 1 --threads 1 --stats 127.0.0.1:9191`
+ 上面的命令表示，使用socket模式启动，并指定了作为*Application*的Python文件，可以作为*app*被调用，4个进程，每个进程2个线程
+ 可选，*--stats 127.0.0.1:9191*则是表示可以访问这个地址来获得内部的调试数据，但是需要使用*uwsgitop*来管理，使用`pip install uwsgitop`来安装