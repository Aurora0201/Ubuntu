[TOC]

# 1.Redis

## 1.Redis初步

### 1.Redis的用途

+ Redis在生产中使用最多的场景就是做数据缓存。即客户端从DBMS中查询出的数据首先写入到Redis 中，后续无论哪个客户端再需要访问该数据，直接读取Redis 中的即可，不仅减小了RT，而且降低了DBMS的压力



**服务器，缓存，数据库间的关系**

图片



缓存数的分类：

+ 实时同步数据：要求缓存中的数据必须与DB中的一致，只要DB中的数据一变化，缓存中的数据立刻消失
+ 阶段性同步数据：对一致性没有强制的要求，只要差不多就行，有一个生存时长属性



### 2.Redis特性

+ 性能极高
    + 读的数据11w/s，写数据8w/s
    + 使用C开发
+ 简单稳定
    + 仅有5W行代码
+ 持久化
    + RDB
    + AOF
+ 丰富是数据类型
    + Redis是一个键值对储存系统，支持的value包括，String，List，Set，Zset，Hash
+ 支持多线程
    + 在Redis6之后支持了多线程模型



### 3.Redis的IO模型

**单线程模型**

在Redis3.0之前采用的是单线程模型

+ 优点:可维护性高，性能高。不存在并发读写情况，所以也就不存在执行顺序的不确定性，不存在线程切换开销，不存在死锁问题，不存在为了数据安全而进行的加锁/解锁开销
+ 缺点:性能会受到影响，且由于单线程只能使用一个处理器，所以会形成处理器浪费。

图片

*值得一提的是：*单线程模型采用了**多路复用技术**，多路选择算法常见的有三种：select模型，poll模型，epoll模型

+ poll，select模型采用的是轮询
+ epoll模型采用的是回调的方式



**混合模型**

从Redis 4.0版本开始，Redis中就开始加入了多线程元素。处理客户端请求的仍是单线程模型,但对于一些比较耗时但又不影响对客户端的响应的操作,就由后台其它线程来处理。例如，持久化、对AOF的rewrite、对失效连接的清理等



**多线程模型**

+ 优点:其结合了多线程与单线程的优点，避开了它们的所有不足
+ 缺点:该模型没有显示不足。如果非要找其不足的话就是，其并非是一个真正意义上的“多线程"，因为真正处理“任务"的线程仍是单线程。所以，其对性能也是有些影响的。

图片

多线程模型中的“多线程”仅用于接受、解析客户端的请求，然后将解析出的请求写入到任务队列。而对具体任务(命令)的处理，仍是由主线程处理。这样做使得用户无需考虑线程安全问题，无需考虑事务控制，无需考虑像LPUSH/LPOp等命令的执行顺序问题。



**注意**

实际上Redis的多线程模型都不是真正意义上的“多线程”



### 4.Redis安装

**安装部分**

+ 首先从官网上下载最新版的redis文件，解压缩后放到合适的位置
+ 进入redis的安装目录，*如果此时没有安装gcc，要先安装gcc编译工具*，终端执行make，完成后再执行make install
+ 验证安装完成，终端执行`redis-server`，如果出现了图标就代表成功了，执行`Ctrl + C`退出



**配置部分**

+ Redis实际上的使用不会直接像刚才那样启动而是会以守护进程的形式启动，编辑配置文件*redis.conf*，找到*daemonize no*修改为*yes*，以后启动的时候就使用命令`redis-server redis.conf`

+ 解除ip绑定，找到配置文件中*bind 127.0.0.1*，将这行注释掉

+ 解除保护模式，找到配置文件中的*protect-mode yes*修改为*no*

+ 设置访问密码，找到配置文件中的*requirepass fooboard*将后面的*fooboard*修改为想要的密码，**注意**修改后，进入redis后都需要进行授权

    + 进入redis前使用`redis-cli -a pwd`授权
    + 进入redis后使用`auth pwd`授权

+ 重命名/禁用命令，找到配置文件中*rename-command*的位置，在下面空的地方加上下面两行

    ```
    rename-command flushall ""
    rename-command flushdb ""
    ```

    这两条的意思是禁用了刷新缓存和刷新数据库的命令，防止有人破坏缓存



## 2.Redis配置详解

### 1.内存大小的设置

配置文件的开头就有说明，如果我们想对内存的大小进行修改，可以像平常一样去指定大小，内存的单位对大小写不敏感



### 2.指定包含的文件

使用*include*来指定包含的外部配置文件，想要让外部文件覆盖内部的配置文件时，应该将包含的语句写在最后一行



### 3.超时时间的设置

+ 配置文件中有一个*timeout*的设置，是用来检测当一个客户端空闲多久之后会切断连接，如果是0即使空闲也不会切断
+ 还有一个*tcp-keepalive*设置，表示多少秒之后会跟客户端验证是否连接



### 4.TCP连接队列

在配置文件中有一个*tcp-backlog 511*，他代表的是一个TCP的连接队列，主要用于高并发下的客户端慢连接的问题，该队列主要与TCP连接的三次握手有关，不同的Linux内核logback队列中存放的元素类型也不同



### 5.Log配置详解

+ 首先我们可以看到配置文件中有*loglevel notice*的选项，它有四个等级：
    + debug：大量的信息，一般用在测试中
    + verbose：许多有用的信息，但是信息不像debug那么多
    + notice：生产环境中常用的模式
    + warning：只用十分重要，严重的信息才会被记录
+ 下面就是*logfile ""*表示我们可以指定日志的文件的位置，如果为空在则默认从终端中输出，如果是在守护模式下，则会保存到*/dev/null*中(不记录)



### 6.最大连接数量限制

+ 在配置文件中有一个*maxclient 10000*的配置，表明了最大的客户连接数量为10000，但是前提是没有超过linux支持的可打开的文件描述符最大数量
+ 在使用Redis集群的情况下，集群中的每一个节点都会使用两个连接，这对于我们修改连接数量的限制时分重要



### 7.内存配置详解

+ 我们可以通过配置文件中的*maxmemory*来设置Redis的最大可用内存
    + 如果在使用集群的情况下，限制Redis的缓存大小是十分有用的，多出的缓存可以给系统或者输出缓冲使用
+ 还可以通过设置*maxmemory-policy*来设置内存的移除策略
    + 一般是*LRU,LFU*
+ 通过*maxmemory-samples*来设置移除key的样本数量，选择样本的算法只能是*LRU*，从样本中移除key的策略则是上面我们设置的策略
+ 修改*maxmemory-eviction-tenacity*可以修改移除key时的延迟，但是一般不修改



### 8.多线程的配置

+ 我们可以通过配置文件中的*io-thread*来开启多线程，前提是我们至少有四个以上核心的CPU，但是在性能没有瓶颈的前提下，是不推荐启用多线程的，因为Redis会站哪一个相当多的CPU时间，即使开启多线程，也要预留出两个以上的CPU核心



## 3.Redis命令

### 1.Redis基本命令

**心跳命令**

```
ping
```

用来验证与服务器的连接，如果连接则返回*PONG*



**读写命令**

```
set [key] [value]
get [key]
```

设置键值对，和通过键获取值



**DB切换命令**

```
select [dbid]
```



**查看键值对数**

```
dbsize
```



### 2.Key操作命令

**查看键命令**

```
keys [pattern]
```

pettern是正则表达式，用来匹配键，*但是这个命令会造成服务器阻塞，一般情况下不会使用*



**查找键命令**

```
exists [key]
```

查看键是否存在



**删除命令**

```
del [key1, key2, key3...]
```



**重命名键命令**

```
rename [key] [newKey]
```



**移动键命令**

```
move [key] [dbid]
```



**查看值类型命令**

```
type [key]
```



**设置生存时间命令**

```
expire [key] [second]
pexpire [key] [millisecond]
```

设置key多长时间后过期



**查看生存时间命令**

```
ttl [key]
pttl [key]
```

查看key还有多长的生命



**去除生存时间命令**

```
persist [key]
```

去除key的生存时间设置



**随机返回键命令**

```
randomkey
```

一般用于判断数据库是否为空



### 3.sacn命令

scan命令较为复杂，有三个参数，cursor是必须的参数，即从哪个位置开始

```
scan [cursor] [MATCH pattern] [COUNT count] [TYPE type]
scan 0 match *a* count 2 type string
```



**注意**

使用负数，超出范围或者其他非正常的游标来执行增量式迭代不会造成服务器崩溃，当数据量很大时，count指定的数量可能会不起作用，Redis会自动调整每次遍历的数目



## 4.不同Value类型操作命令

### 1.String型Value操作命令

**set命令**

先来看*set*命令的参数

```
set key value [NX|XX] [GET] [EX second|PX millisecond]
```

带括号的参数都是可选的，基础的set命令只需要包含key和value，NX代表的是不存在，XX代表的是已经存在，GET表示先获取原先的值再设置新的值， EX和PX是设置过期时间



**setex, psetex命令**

直接来看参数

```
setex key seconds value
```

设置值的同时设置生命



**getset命令**

相当于set命令的简化版

```
getset key value
```



**mset, msetnx命令**

一次插入多个key value

```
mset key value [key value..]
```



**mget命令**

一次获取多个key value

```
mget key [key..]
```



**append命令**

在原先的value后添加字符

```
append key value
```



**incr和incrby命令**

自增命令，只能对Integer类型起作用

```
incr key
```



**strlen命令**

获取字符串的长度

```
strlen key
```



**getrange命令**

获取一个区间的子串

```
getrange key start end
```



**setrange命令**

对一部分子串设置

```
setrange key start value
```

start超出原有的部分会以/*0x00*填充



### 2.Hash型Value操作命令

**hset命令**



**hget命令**



**hmset命令**



**hmget命令**



**hgetall命令**



**hsetnx命令**



**hdel命令**



**hexits命令**



**hincrby命令**



**hkeys, hvals命令**



**hlen命令**



**hstrlen命令**



### 3.List型Value操作命令

**lpush/rpush**



**llen**



**lindex**



**lset**



**lrange**



**lpushx**



**rpushx**



**linsert**



**lpop/rpop**



**blpop/brpop**



**lrem**



**ltrim**



**应用场景**

+ 栈：可以使用List模拟栈
+ 队列：可以使用List模拟队列
+ 阻塞消息队列：使用*lpush, brpop*实现阻塞消息队列
+ 动态有限集合：使用*lpush, ltrim*可以实现动态有限集合



### 4.Set型Value操作命令

**sadd**



**smember**



**scard**



**sismember**



**smove**



**srem**



**srandmember**



**spop**



**sdiff**



**应用场景**

+ 动态黑白名单
+ 有限随机数
+ 用户画像



### 5.ZSet型Value操作命令

通过*help @sorted-set*来查询



## 5.SDS简单动态字符串