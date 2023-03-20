[TOC]



# 1.HTML基础

## 1.页面结构

一般来说所有的html标签都会包含在`<html>`标签中，并且大致结构如下

```html
<html>
    <head>
       <title></title> 
       <meta charset=""/>
    </head>
    <body>
        
    </body>
</html>
```

head代表页面的头部，body代表页面的主体部分



## 2.HTML头部

我们知道，HTML的头部就是用`<head>`包裹的部分，在页面加载时，一般不会被用户所知，所以我们会将一些特殊的操作定义在HTML的头部中



### 1.title标题标签

页面所显示的标题就是使用title标签来定义的

```html
<title>Page</title>
```



### 2.meta元数据标签

meta标签的用处很多，最常见的就是用来指定页面的字符集

```html
<meta charset="utf-8"/>
<meta name="author" content="Chris Mills" />
<meta
  name="description"
  content="The MDN Web Docs Learning Area aims to provide
complete beginners to the Web with all they need to know to get
started with developing web sites and applications." />
```

常用属性：

+ charset：指定字符集
+ name：指定meta标签的类型；说明该标签包含什么类型的数据
+ content：元数据内容



### 3.link链接标签

link标签通常可以设置一些页面的元素属性，或者链接一些外部的资源（CSS），最长见的还是改变页面的图标

```html
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="my-css-file.css" />
```

常用属性：

+ ref：影响的部分
+ href：引用的部分



### 4.script标签

一般在里面写js脚本，也可以从外部引入

```html
<script src="my-js-file.js" defer></script>
```





## 3.HTML文本处理

主体部分就是我们用户看到的部分



### 1.p段落标签

每一个段落最好都使用p标签括起来



### 2.h标题标签

使用`<h1>`代表一级标签，`<h2>`代表二级标签以此类推，一个页面中最好不要出现超过三个标题，`<h1>`最好不要超过一次



### 3.ul无序列表

无序列表整体使用`<ul>`括起来，每个元素则使用`<li>`括起来



### 4.ol有序列表

无序列表整体使用`<ol>`括起来，每个元素则使用`<li>`括起来



### 5.strong粗体标签

粗体就是**强调**



### 6.em斜体标签

语气加重



### 7.mark标记标签

就像被记号笔标记



### 8.u下划线标签



## 4.外部链接

链接外部的资源

### 1.a超链接标签

我们可以通过创建一个超链接标签来访问URL

```xml
<p>
  我创建了一个指向<a href="https://www.mozilla.org/zh-CN/"
   title="了解 Mozilla 使命以及如何参与贡献的最佳站点。">Mozilla 主页</a>的超链接。
</p>
```

使用路径访问，**contacts.html**是跟**index.html**同一目录下的

```html
<p>
  要联系某位工作人员，请访问我们的<a href="contacts.html">联系人页面</a>。
</p>
```

链接文档片段

```html
<h2 id="Mailing_address">邮寄地址</h2>
<p>
  要提供意见和建议，请将信件邮寄至<a href="contacts.html#Mailing_address">我们的地址</a>。
</p>
```

下载链接

```html
<a href="https://download.mozilla.org/?product=firefox-latest-ssl&os=win64&lang=zh-CN"
   download="firefox-latest-64bit-installer.exe">
  下载最新的 Firefox 中文版 - Windows（64 位）
</a>
```

下载链接可以使用download属性给下载的文件命名



常用属性：

href：可以填入URL，路径，文档片段

title：鼠标悬停时，显示的内容

download：下载文件命名



## 5.文本格式进阶

### 1.描述列表

描述列表可以让文本信息产生缩进的效果

```html
<dl>
  <dt>内心独白</dt>
    <dd>戏剧中，某个角色对自己的内心活动或感受进行念白表演，这些台词只面向观众，而其他角色不会听到。</dd>
  <dt>语言独白</dt>
    <dd>戏剧中，某个角色把自己的想法直接进行念白表演，观众和其他角色都可以听到。</dd>
  <dt>旁白</dt>
    <dd>戏剧中，为渲染幽默或戏剧性效果而进行的场景之外的补充注释念白，只面向观众，内容一般都是角色的感受、想法、以及一些背景信息等。</dd>
</dl>
```



### 2.cite引用标签

引用标签默认让引用的文字变为斜体



### 3.abbr略缩语标签

我们可以使用一个略缩语标签来表示略缩语，在网页上显示的效果为下划虚线

```html
<p>
  我们使用 <abbr title="超文本标记语言（Hyper text Markup Language）">HTML</abbr> 来组织网页文档。
</p>

<p>
  第 33 届<abbr title="夏季奥林匹克运动会">奥运会</abbr>将于 2024 年 8 月在法国巴黎举行。
</p>
```



常用属性

title：鼠标悬停时展示的内容



### 4.address地址标签

使用address来表示地址

```html
<address>Chris Mills, Manchester, The Grim North, UK</address>
<address>
  <p>
    Chris Mills<br />
    Manchester<br />
    The Grim North<br />
    UK
  </p>

  <ul>
    <li>Tel: 01234 567 890</li>
    <li>Email: me@grim-north.co.uk</li>
  </ul>
</address>
```

​	

### 5.上标和下标

上标sup，下标sub



### 6.日期和时间

在页面上显示的时间可能会不符合的计算的时间格式，所以我们会使用时间标签来让计算机识别

```html
<time datetime="2016-01-20">2016 年 1 月 20 日</time>
```



## 6.网站架构

为了实现语义标记化，html提供了某些区域专用的标签：

+ header：页眉
+ nav：导航栏
+ main：主内容，其中还有各种子内容，可以用article，section，div等元素分隔
+ aside：侧边栏，一般放在main中
+ footer：页脚



## 7.无语意元素

当我们需要相应CSS或者JS时，可以使用一些无语意元素来包裹内容



**内联无语意元素**

span是内联无语意元素



**块级无语意元素**

div是块级无语意元素



## 8.多媒体嵌入

### 1.img图片标签

我们可以使用img标签来嵌入图片，同时添加对图片的一些描述

```html
<img src="images/dinosaur.jpg"
     alt="一只恐龙头部和躯干的骨架，它有一个巨大的头，长着锋利的牙齿。"
     width="400"
     height="341"
     title="A T-Rex on display in the Manchester University Museum">
```

上面的html代码引入了一张图片，同时设置了图片的大小，还有鼠标悬停时显示的内容



常用属性：

+ src：图片链接
+ alt：图片失效时显示的文字
+ title：鼠标悬停时显示的文字
