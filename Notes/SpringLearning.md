[TOC]

# 1.Spring

## 1.Spring环境准备

本次spring的学习基于spring6，spring6要求最低的JDK版本为JDK17

+ 安装JDK17版本
+ 修改Maven的编译版本为JDK17



## 2.Spring启示录

### 1.OCP开闭原则

什么是OCP开闭原则？

+ 七大开发原则中最基本的原则
+ 是最核心，最基本的原则，其他六个原则都是为这个原则服务



开闭原则的的核心是什么？

+ 只要你在扩展系统功能的时候，没有修改之前写好的代码，那么就符合OCP原则
+ 反之，扩展系统功能时修改了以前写好的代码，那么这个设计就是失败的，违背了OCP原则
+ 这就是对扩展开放，对修改关闭



### 2.DIP依赖倒置原则

先来看看我们之前编写的MVC架构的项目结构

![1](https://img.noobzone.ru/getimg.php?url=https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/1-1677478801391-1.png)