[TOC]

# upupor

## 1.data模块

### 1.引入的依赖

**单独的依赖**

+ *mysql*驱动
+ *druid*连接池
+ *pagehelper*分页管理
+ *lucence*全文检索
+ *mybatis-plus*MyBatis增强版
+ *jdbc*数据连接依赖



**其他模块**

+ *upupor-framework*
+ *upupor-security*



### 2.包结构

+ *com.upupor.data*
    + *component*
        + *model*
        + *service*
    + *dao*
        + *entity*
        + *mapper*
        + *query*
    + *dto*
        + *cache*
        + *dao*
        + *page*
        + *query*
        + *seo*
    + *types*
    + *utils*
        + *page*



### 3.接口中提供的主要功能

+ 提供了用户注册和登录的接口