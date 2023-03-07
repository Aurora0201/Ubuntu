[TOC]

# 1.基于云服务器的frp配置

## 1.服务端操作



**设备**

+ 腾讯云服务器
+ Ubuntu 22.04



**步骤**

+ 从Github下载frp-linux，解压缩到服务器的适当位置，这里我放到了`/usr/local/frp`

+ 配置`frps.ini`文件

  ```
  [common]
  # frp监听的端口，默认是7000，可以改成其他的
  bind_port = 7000
  # 授权码，请改成更复杂的
  token = 780201  # 这个token之后在客户端会用到
  
  # frp管理后台端口，请按自己需求更改
  dashboard_port = 7500
  # frp管理后台用户名和密码，请改成自己的
  dashboard_user = admin
  dashboard_pwd = admin
  enable_prometheus = true
  
  # frp日志配置，可以不使用
  log_file = /var/log/frps.log
  log_level = info
  log_max_days = 3
  ```

+ 启动服务，在frp安装目录下终端输入`./frps -c frps.ini`

+ 开放端口，云服务器的端口直接从控制面板设置规则即可，开放上面的7000和7500端口

+ 访问`公网ip:7500`进入控制面板，输入我们刚刚设置的信息，如果登录成功则表示我们的服务器端设置完成了



## 2.客户端操作



**设备**

+ Virtual Machine Ubuntu 22.04



**步骤**

+ 如上一样安装frp

+ 配置`frpc.ini`文件

  ```
  # 客户端配置
  [common]
  server_addr = 服务器ip/公网ip
   # 与frps.ini的bind_port一致
  server_port = 7000
   # 与frps.ini的token一致
  token = 780201
  
  # 配置ssh服务
  [ssh]
  type = tcp
  local_ip = 127.0.0.1
  local_port = 22
   # 这个自定义，之后再ssh连接的时候要用
  remote_port = 6000 
  
  # 配置http服务，可用于小程序开发、远程调试等，如果没有可以不写下面的
  [web]
  type = http
  local_ip = 127.0.0.1
  local_port = 8080
  # web域名
  subdomain = test.hijk.pw
  # 自定义的远程服务器端口，例如8080
  remote_port = 8080
  ```

+ 开放端口，我们需要开放上面除了服务器端口的所有端口

  ```
  sudo ufw allow 端口号
  sudo ufw reload 
  ```

+ 如果是想要从远程连接本机，还需要安装openssh-server

  ```
  sudo apt-get install openssh-server
  ```

  验证安装

  ```
  ps -e |grep ssh
  ```

  如果出现了sshd就代表成功了

+ 启动服务

  ```
  ./frpc -c frpc.ini
  ```

  





## 3.通过SSH连接内网机器



**前提**

+ 云服务器启动了frps
+ 被访问的内网机器启动了frpc



**步骤**

+ 使用ssh连接

  ```
  ssh 内网机器登录用户名@公网ip -p [ssh]remote_port
  ```

+ 输入密码即可