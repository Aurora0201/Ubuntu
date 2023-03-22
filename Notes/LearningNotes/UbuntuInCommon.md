[TOC]

# 1.Ubuntu

## 1.Ubuntu常见问题

### 1.设置消失

`sudo apt-get install unity-control-center`



### 2.图标消失

`sudo apt-get install gnome-control-center`



### 3.软件更新消失

`sudo apt-get install software-properties-gtk`



### 4.网络消失

重启网络管理器

```
sudo nmcli network off
sudo nmcli network on
```

删除缓存文件

```
sudo service NetworkManager stop 
sudo rm /var/lib/NetworkManager/NetworkManager.state 
sudo service NetworkManager start
```

### 5.无法启动AppImage应用

+ 首先确定给予了执行权限
+ 缺少库的情况下，先安装库`sudo apt install libfuse2`
+ 在命令行执行时如果出现了`GPU process isn't usable. Goodbye.`，加上参数`--no-sandbox`执行



## 2.常用软件的安装

### 1.数据库

`sudo apt-get install mysql-server`



### 2.python以及pip

`sudo apt-get install (python3 | python)`

`sudo apt-get install python3-pip`



