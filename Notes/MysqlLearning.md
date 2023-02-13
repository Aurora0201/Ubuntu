[TOC]

## MYSQL STATEMENTS

```mysql
desc + 表名 #Show structure of table
show create table 表名 #Show the sentence of building the table
rename table 原来的名字 to 新的名字; #Rename table
create table 表名 select #Create table with selecting
delete * from 表名 #Delete data of the table
drop table 表名 #Delete the table
truncate table 表名 #Only delete the data of the table
start transaction #Stop transaction automatically committing
explain SQL #Show details of the SQL running
create 索引名 on 表名(字段名) #Create index on field
drop index 索引名 on 表名 #Delete index
update 表名 set 字段1 = 值, 字段2 = 值,... where(case)#不加where就是对所有行修改
```

## Config Files

>"my.cnf" located in "/etc/mysql"
>
>Using "--skip-grant-table" will make mysql stop net-working and cause port be set to 0

> "mysqld.cnf" located in "/etc/mysql/mysql.conf.d"

> line bind-ipaddress
>
> 总结一下，bind-address的设置有以下三种情况：
>
> - bind-address=127.0.0.1 #只允许本机访问。
>- bind-address=某个网卡的ip #例如bind-address=192.168.1.101，只能通过ip为192.168.1.101的网卡访问。
> - bind-address=0.0.0.0 #此规则是系统默认配置，监听所有网卡，即允许所有ip访问，或者注释掉本行

## 约束

> ### 1.唯一性约束 unique
>
> 属性 类型 unique，#（属性约束）当前表只能存在一个同名的属性
>
> 属性1 类型，
>
> 属性2 类型,
>
> unique(属性1，属性2) #（联合约束）当前表只能存在一个属性对
>
> ```mysql
> create table t_user(
> 	id int unique, #列级
>     userName varchar(255),
>     userPwd varchar(255)
> );
> ```
>
> ```mysql
> create table t_user(
> 	id int,
>     userName varchar(255),
>     userPwd varchar(255),
>     unique(userName, userPwd) #联合
> );
> ```
>
> ---
>
> ### 2.主键约束
>
> 属性 类型 primary key，#列级约束
>
> 或者在建表语句最后一行
>
> primary key(field) #表级约束
>
> 使用auto_increment修饰可以让主键自增，留空主键时，从一开始自增，步长为1
>
> ```mysql
> create table t_user(
> 	id int primary key auto_increment, #列级
>     userName varchar(255),
>     userPwd varchar(255)
> );
> ```
>
> ```mysql
> create table t_user(
> 	id int,
>     userName varchar(255),
>     userPwd varchar(255),
>     primary key(id)
> );
> ```
>
> ---
>
> ### 3.外键约束
>
> 外键引用外键属性不一定是主键但是应具有唯一性约束
>
> 外键可以为空
>
> 在建表语句的最后
>
> foreign key(属性) references 父表名(父表属性)
>
> ```mysql
> create table t_class(
> 	stu_class int,
>     class_name varchar(10)
> );
> 
> create table t_stu(
> 	stu_id int primary key,
>     stu_name varchar(10),
>     stu_class int,
>     foreign key(stu_class) references t_class(stu_class)
> );
> ```
>
> ---
>
> 删除表的时候
>
> 先删除子表，再删除父表

## 事务

> ### 事务（transaction）
>
> 一个事务是一个完整的业务逻辑单元，不可再分
>
> 事务是为了保证数据的完整性，安全性
>
> 通常一个事务通过多条DML语句完成
>
> ----
>
> ### 事务执行过程
>
> 一个事务的开启(start transaction)
>
> 执行insert语句 --> 提交到缓存
>
> 执行update语句 --> 提交到缓存
>
> 执行delete语句 --> 提交到缓存
>
> 提交或者回滚事务(commit; or rollback;) --> 提交到硬盘中，清空缓存记录
>
> ---
>
> 单独执行DML语句时，只会向数据库提交记录，不会持久化修改，当你提交事务后，就会清空历史记录，将数据持久化到硬盘中
>
> ---
>
> ### 事务语句
>
> ```mysql
> start transaction; #开始事务
> commit; #提交事务
> rollback; #回滚事务
> ```
>
> ---
>
> ### 关于事务之间的隔离性
>
> #### 第一级别：读未提交（read uncommitted）
>
> ​	对方事务还没有提交，我们当前事务可以读取到对方未提交的数据
>
> ​	读未提交存在脏读（Dirty read）现象：表示读到了脏的数据
>
> #### 第二级别：读已提交（read committed）
>
> ​	对方提交之后的数据我们可以读到
>
> ​	存在问题：在我的事务没有结束的时候无法重复读取（在我的事务中，我在9：00与10：00读取到的数据不同）
>
> #### 第三级别：可重复读（repeatable read）
>
> ​	解决了不可重复读问题（即在我的事务中，不管对方怎么提交，我读到的数据都是一样的）
>
> ​	存在问题：读取的数据是幻象
>
> #### 第四级别：序列化读/串行化读（serializable）
>
> ​	解决所有问题但是效率低
>
> ---
>
> mysql默认情况下事务隔离性是**可重复读**
>
> mysql事务默认情况下是**自动提交**
>
> ---
>
> ### 乐观锁与悲观锁的概念
>
> 悲观锁
>
> 假如我们在事务中想要保持数据不被更改，使用for update
>
> ```mysql
> select * from emp where job = 'MANAGER' for update
> ```
>
> 乐观锁
>
> 在表中存在有一个版本号
>
> > ename		job		sal		version
> >
> > ---
> >
> > BLACK		MANAGER	2500.00	1.0
>
> 此时有一个事务1和一个事务2同时要对这张表修改，此时他们都读取到版本号为1.0
>
> 首先事务1对表进行了修改，提交事务的时候发现版本号是1.0，于是提交修改的数据，将版本号修改为1.1
>
> 然后事务2也对表进行了修改，准备提交事务的时候发现版本号变为了1.1，与一开始的版本不同，决定回滚
>
> 悲观锁：事务必须排队执行。数据锁住了，不允许并发（多个用户操作）
>
> 乐观锁：支持并发，事务不需要排队，但是需要版本号

## 索引

> ### 索引（index）
>
> 索引相当于数据库的目录，可以快速查找数据
>
> 在数据库查找一张表有两种检索方式：1.全表扫描 2.索引检索
>
> 索引虽然可以提高查找效率，但是索引也有维护的成本，如果表中的数据经常被修改，那么这张表就不适合添加索引
>
> 索引的实现：B+Tree
>
> ```mysql
> create index 索引名 on t_user(userName);
> ```
>
> ---
>
> ### 什么时候考虑给字段添加索引
>
> 1.数据量庞大
>
> 2.该字段很少DML操作
>
> 3.该字段经常出现在where子句中
>
> ### 补充：
>
> 主键和具有unique的字段会自动添加索引
>
> 根据主键索引效率比较高，尽量通过主键检索
>
> ---
>
> ### 索引的实现原理
>
> 通过B+Tree缩小扫描范围，底层索引进行了排序分区，索引会携带数据在表中的物理地址，通过索引检索到数据关联的物理地址，通过物理地址确定表中的数据位置
>
> ----
>
> ### 索引的分类
>
> 单一索引：给单个字段添加索引
>
> 复合索引：多个字段联合添加索引
>
> 主键索引:主键自动添加索引
>
> 唯一索引:unique修饰字段自动添加索引
>
> 索引的失效：
>
> 使用模糊查询会使索引失效，降低效率，慎用

## 视图

> ### 视图（view）
>
> 视图就是站在不同的视角看数据
>
> ```mysql
> create view 视图名 select... #Create view by DQL statement
> drop view 视图名 #Delete view
> ```
>
> ---
>
> ### 对视图的操作也会影响原表
>
> 所以在某些情况下，当数据库保密级别较高到时候，只会提供视图给程序员操作

## 范式

> ### 范式的类型
>
> 1NF：每张表都应该有主键，并且每一个字段原子性不可再分
>
> 2NF：非主键字段完全依赖主键，不能产生部分依赖

## 经典数据库设计

> ### 一对一设计两种方案
>
> #### 主键共享方案
>
> > t_user_login 用户登录表
> >
> > id(pk)	username	password
> >
> > --------------------------------
> >
> > 1         zs          123
> >
> > 2         ls          333
> >
> > ...
> >
> > t_user_detail 用户详细信息表
> >
> > id(pk+fk) realname	tel
> >
> > ------
> >
> > 1	     张三		 13871512323
> >
> > ...
>
> #### 外键唯一
>
> > t_user_login 用户登录表
> >
> > id(pk)	username	password
> >
> > --------------------------------
> >
> > 1         zs          123
> >
> > 2         ls          333
> >
> > ...
> >
> > t_user_detail 用户详细信息表
> >
> > id(pk) realname	tel	      userid(fk+unique)
> >
> > ------
> >
> > 1	     张三		 13871512323  1
> >
> > ...
>
> ### 口诀
>
> 一对多，两张表，多的加外键
>
> 多对多，三张表，关系表加两个外键
