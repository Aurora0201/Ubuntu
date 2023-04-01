[TOC]

# 1.Redis

## 1.Redis初步

### 1.Redis的用途

+ Redis在生产中使用最多的场景就是做数据缓存。即客户端从DBMS中查询出的数据首先写入到Redis 中，后续无论哪个客户端再需要访问该数据，直接读取Redis 中的即可，不仅减小了RT，而且降低了DBMS的压力



**服务器，缓存，数据库间的关系**

![relationAmongSRD.drawio](https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/upgit_20230331_1680261235.png)



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

在默认情况下，字符串类型最大的存储量是512MB



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



## 5.Redis数据结构

### 1.SDS简单动态字符串

**SDS结构**

```c
struct SDS{
    char buf[];
    int len;
    int free;
}
```

*buf*存储了字符串本身，*len*代表字符串长度，*free*代表剩余的空间



**SDS优势**

+ 普通的C字符串需要遍历来获取字符串长度，但是*SDS*存储了字符串长度，避免了长字符串获取长度的时间开销

+ 保障二进制安全，C语言中的字符串以`\0`为结尾，但是图片，音频，视频等二进制数据中以`\0`为分隔符的现象十分常见，为了兼容这类数据的存储，*SDS*并不以`\0`为结尾，而是通过字符串长度为判断

+ 内存预分配策略

    + 如果*len*小于1M，则分配等长的*free*空间
    + 如果*len*大于1M，则分配1M

    这个策略的出现是为了防止后期二次扩容造成的性能损失

+ 兼容C函数，Redis中提供了很多*SDS*的API，以便进行二次开发



**注意：Redis中不是所有的字符串都是SDS，如保留字等不会改变的字符串都C字符串**



### 2.zipList压缩列表

zipList是一个经过特殊编码的用于存储字符串或整数的双向链表，底层的数据结构由三部分构成，他们都是在内存中连续的存放

+ head
+ entries
+ end



**head部分**

head部分又由三个部分构成

+ zlbytes：占四个字节，存放ziplist数据结构整体数据结构所占的自己数
+ zltail：占四个字节，存放ziplist最后一个entry的偏移量，使得能够快速定位列表尾
+ zllen：占两个字节，存放entry个数，由于只有16位，最多只能存65535个



**entries部分**

entries部分才是真正的列表，有很多的列表元素构成，由于不同的元素类型，数值的不同，从而导致每个entry的长度不同，同样由三个部分构成

+ prevlength：记录上一个entry的长度，以实现**逆序遍历**，默认长度一个字节，只要上一个entry小于254字节，prevlength就占一个字节，否则会自动扩展三个字节
+ encoding：这个部分用于标志后面data部分的数据类型，根据数据类型的不同，encoding的长度也会发生变化，一般为1，2，5
+ data：真正存储数据的部分，只能是整数或者字符串类型



**end部分**

+ end部分称为zlend，占一个字节，值固定为255，二进制全是1，标志这个是ziplist的结束



### 3.listPack压缩列表

Redis7.0中将所有的zipList替换为listPack，listPack的出现是因为ziplist在高并发的情况下，在中间插入元素时会引起级联更新，这对于性能来说是一个极大的损失，所以redis对ziplist进行了重写，重写后的产物就是listPack，listPack同样由三部分构成，但是有一些细微的不同

+ head
+ entries
+ end



**head部分**

+ totalBytes：四个字节，存储了列表的长度
+ elemNum：两个字节，列表节点个数



**entries部分**

+ encoding：data的具体类型，根据存储的data不同有不同的长度
+ data：真正的数据
+ element-total-len：当前entry的长度



**end部分**

+ end：一个字节，结束标记



### 4.skipList跳表

跳表的数据结构是一种随机化的数据结构，在大数据中已经学习过



### 5.quickList快速列表

quickList也是一个双向无循环列表，但是其中的每一个节点都是zipList



## 6.Redis事务

Redis的事务的本质是一组命令的批处理。这组命令在执行过程中会被顺序地、一次性全部执行完毕，只要没有出现语法错误，这组命令在执行期间是不会被中断。。

**Redis 事务特性**

+ Redis的事务仅保证了数据的一致性，不具有像DBMS 一样的ACID特性
+ 这组命令中的某些命令的执行失败不会影响其它命令的执行，不会引发回滚即不具备原子性
+ 这组命令通过乐观锁机制实现了简单的隔离性。没有复杂的隔离级别这组命令的执行结果是被写入到内存的，是否持久取决于Redis的持久化策略，与事务无关



## 7.Redis持久化

### 1.RDB持久化

RDB，Redis DataBase，是指将内存中某一时刻的数据快照**全量**写入到指定的rdb文件的持久化技术。RDB持久化默认是开启的。当Redis启动时会自动读取RDB快照文件，将数据从硬盘载入到内存，以恢复Redis 关机前的数据库状态



**save命令**

手动激活持久化，在持久化的过程中是阻塞的



**bgsave命令**

启用背景持久化，这种持久化不会阻塞进程，因为是异步的持久化



### 2.RDB持久化优化配置

在默认配置文件中提供了多种选项来让我们



**save**

设置触发持久化的条件



**stop-writes-on-bgsave-error**

当背景持久化发生错误时，设置继续进行持久化操作



**rdbcompression**

设置是否压缩rdb文件



**rdbchecksum**

设置是否对持久化文件进行校验



### 3.RDB文件结构

+ SOF：常量，是一个字符串，长度为5，用于表示RDB文件的开始
+ rdb_version：整数，4个字节，表示RDB文件的版本号
+ EOF：常量，1个字节，用于表示RDB文件的结束，校验和的开始
+ check_sum：校验和，使用CRC校验算法
+ database：是RDB文件中真正存储数据的部分，可以包含多个非空数据库，每个数据库由下面的三部分组成：
    + SODB：是一个常量，占一个字节，用于标识数据库的开始
    + db_number：数据库编号
    + key_value_pairs：当前数据库的键值对数
    + VALUE_TYPE：常量，一个字节，标识键值对中value的类型
    + EXPIRETIME_UNIT：常量，一个字节，用于标识过期单位是秒还是毫秒
    + time：当前键值对过期时间



### 4.RDB持久化过程

在bgsave过程中，父进程fork出一个子进程，子进程会继承父进程的所有资源，子进程就会将内存中的资源拷贝到磁盘中，此时如果用户写入了数据，就会将数据写入到新的内存区域，当原来的数据拷贝结束后，才会将新写入的数据copy到rdb文件中



### 5.AOF持久化

AOF，Append Only File， 指将每一次的写操作都以日志的形式记录到AOF文件中的持久化技术，当需要恢复数据时，只需要将日志内记录的操作重新执行一遍即可



### 6.AOF配置

首先我们需要在配置文件中开启AOF持久化，在配置文件中找到`appendonly no`设置为`yes`重启服务器即可开启



### 7.Rewrite机制

Rewrite机制其实就是对AOF文件的重写整理，当Rewrite开启后，主进程redis-cli会创建出一个子进程bgrewriteaof，由子进程完成Rewrite过程，对现有的AOF文件进行Rewrite计算，将计算结果写入一个临时文件中，写入完毕后重名对原文件进行覆盖



**Rewrite计算**

Rewrite计算遵循以下的策略：

+ 读操作不写入文件
+ 无效命令不写入文件
+ 过期数据不写入文件
+ 多条命令合并写入文件



**Rewrite开启**

Rewrite机制有两种启动方式，手动启动和自动开启，自动开启则需要设置一些触发条件



### 8.AOF配置优化

**fsync**

设置我们同步的策略，有下面三种策略：

+ always：每一次写操作都计入文件中，性能低，一般不使用
+ everysec：每一秒钟的写操作都计入文件中，性能一般，这种方案是一种折中方案
+ no：当缓存快要到达上限时，由操作系统来决定是否写入文件中，性能最高，但是可能会有数据丢失的风险



**aof-load-truncated **

redis启动时是否截断末尾损坏的数据，注意，这个操作无法处理数据在中间部分的损坏



### 9.AOF持久化过程

当满足AOF持久化策略时，会首先将数据写入AOF文件中，若AOF文件中的数据满足rewrite策略，则主线程会fork出一个子线程来负责rewrite操作，这个操作会创建一个新的持久化临时文件，此时如果有新的数据读写，会开辟的缓冲区来写入新的操作，当rewrite结束后，就会将缓冲区的数据写入临时文件，最后就会将临时文件重命名替换掉原来的持久化文件



### 10.AOF与RDB对比

**RDB优势与不足**

优势

+ RDB文件较小
+ 数据恢复快

不足

+ 数据安全性较差
+ 写时复制会降低性能
+ RDB文件可读性差



**AOF优势与不足**

优势

+ 数据安全性较强
+ AOF可读性较高

不足

+ AOF文件较大
+ 写操作会影响性能
+ 数据恢复较慢



**执行化技术选型**

官方推荐RDB与AOF混合式持久化

+ 如果对数据安全性要求不高，推荐使用RDB持久化方式
+ 不推荐使用纯AOF持久化方式



## 8.Redis集群搭建

### 1.Redis集群概念

Redis的集群是一个“一主多从”的读写分离集群，集群中的Master节点负责处理客户端的读写请求，而Slave节点仅能处理客户端的读请求，之所以这样设计集群，是因为对于数据库来说，大部分的压力都来自于读操作而不是写操作，所以只有一个节点负责写即可



### 2.伪集群搭建

采用单线程IO模型时，为了提高利用率，一般会在一个主机中启动多台Redis服务器，构建一个Redis伪集群，这里我们构建一个一主两从的集群



**初步设置**

+ repl-disable-tcp-nodelay ：使用yes时，会使用更少的tcp包来发送数据，但是会造成主从节点之间的连接会有一些延迟，在网络流量较大或者主从节点之间有较多的跳跃时，建议是设置为yes，但是我们这里设置为no
+ replica-priority ：优先级，与后面的节点选举有关



**复制配置文件**

+ 首先我们要将配置文件`redis.conf`复制到我们想要操作的地方，最好是一个空文件夹，这里我们是`/cluster`

+ 创建一个文件`redis6380.conf`，设置内容为

    ```
    #包含公共配置文件
    include redis.conf
    
    #设置端口
    port 6380
    
    #设置线程文件
    pidfile pid/pid_6380.pid
    
    #设置RDB持久化文件位置
    dbfilename dump_6380.rdb
    
    #设置AOF持久化文件位置
    appendfilename appendonly_6380.aof
    
    #设置优先级
    replica-priority 90
    ```

    然后将这个文件复制多两份，分别命名为`redis6381.conf`和`redis6382.conf`然后修改里面的内容



**启动Redis服务器**

+ 使用命令启动三个服务器

    ```
    redis-server redis6380.conf
    redis-server redis6381.conf
    redis-server redis6382.conf
    ```



**设置主从关系**

+ 进入每个服务器的cli界面

    ```
    redis-cli -p 端口号
    ```

+ 将端口为6381和6382的服务器设置为6380的子节点

    ```
    slaveof localhost 6380
    ```
    
+ 当我们不想让节点作为其他节点的子节点时可以使用命令

    ```
    slaveof no one
    ```

    



**查看主从关系**

+ 在每个节点中使用命令查看关系

    ```
    info replication
    ```

    查看子节点的数量，如果为2，那么证明我们的集群搭建成功了



### 3.集群的多级管理

在实际开发中为了提高redis的同步效率，会采用分级的管理策略，也就是说Master下的Slave节点还会有自己的Slave节点

![RedisMutiHierarchy.drawio](https://cdn.jsdelivr.net/gh/Aurora0201/ImageStore@main/img/upgit_20230401_1680346046.png)



### 4.主从复制详解

