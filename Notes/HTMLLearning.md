

[TOC]

## 1.什么是HTML

Hyper Text Markup Language (超文本标记语言)

由大量的标签组成，每一个标签都有开始和结束标签

超文本：流媒体，图片，声音，视频...



HTML开发使用普通的文本编辑器就行，创建的文件扩展名是.html或.htm

## 2.HTML语法基础

### 1.HTML的主体部分

```html
<!doctype html>
<html>
	<head>
        <title>标题，HTML的学习</title>
    </head>
    <body>
        网页主体内容，欢迎学习HTML！
    </body>
</html>
    
```

值得注意的是，HTML不区分大小写，语法松散不严格，不想让用户看到的内容一般放到head中

### 2.HTML文字标签

#### 1.p标签（段落标签）

```html
<p>
    这是一个段落
</p>
```

使用段落标签来分隔一个个段落

---

#### 2.h标签（标题标签）

```html
<h>
	一级标题
</h>
<h2>
    二级标题
</h2>
以此类推...
```

使用标题字来建立一个标题

---

#### 3.br标签（换行标签）

```html
<br>
```

使用换行标签来换行

换行标签是一个独目标签

---

#### 4.hr标签（横线标签）

```html
<hr color="red" width="50%">
<hr color=‘red’ width=50%>
```

使用横线标签来创建横线

可以使用属性来改变颜色和长度，语法松散

---

#### 5.pre标签（预留格式标签）

```html
<pre>
	for(int i = 0; i != 10; i++){
		System.out.println(i)
	}
</pre>
```

使用预留标签来显示原先的结果

---

#### 6.del标签（删除字标签）

```html
<del>
	删除字
</del>
```

使用删除字标签，创建删除字

---

#### 7.ins标签（插入字标签）

```html
<ins>
	插入字
</ins>
```

使用插入字标签创建插入字

---

#### 8.strong标签（粗体字标签）

```html
<strong>
	粗体字
</strong>
```

---

#### 9.em标签（斜体字标签）

```html
<em>
	斜体字
</em>
```

---

#### 10.sup标签和sub标签（上下标标签）

```html
10<sup>2</sup>
a<sub>1</sub>
```

---

#### 11.font标签（字体标签）

```html
<font color='red' size=20>
	字体标签
</font>
```

---

#### 12.meta标签（指定字符集标签）

```html
<meta charset='utf-8'>
```

通过meta标签来告诉浏览器用什么字符集打开

```html
<meta name="keywords" content='关键词内容'>
```

name属性为keyword时能被搜索引擎搜索到content的内容

----

#### 13.div标签和span标签（图层标签）

div和span都可以称为图层，图层的作用可以保证页面的灵活布局，图层就是一个一个盒子，div嵌套就是盒子套盒子

div和span可以定位，通过X轴和Y轴坐标即可定位

div和span区别：div会独占一行，span不会

---

### 3.HTML标签属性

#### 1.id属性

在HTML中，每一个标签（节点）都有id属性，id属性是节点的唯一标识。所以在同一个HTML文档中id值不能重复

id属性是为了JavaScript可以对HTML的任意节点进行增删改

```html
<标签 id="name"></标签>
```



---

#### 2.class属性

可以将不同的标签归为一类，对于类选择器来说可以一起进行样式的设计

```html
<标签 class="第一个名字 第二个名字 ..."
```

---

### 4.HTML实体符号

#### 1.实体符号特点

实体符号一般以&开始，以;结束

#### 2.<号与>号

```html
&lt; = <
```

```html
&gt; = >
```

#### 3.空格

```html
&nbsp; = ' '
```

---

### 5.HTML表格

#### 1.table标签（表格标签）

```html
<table borde='1px' width='300px' height='150px'>
    
</table>
```

使用表标签可以创建一个表格

表格内部属性borde表示表格边框的宽度为1个像素

width属性表示表格宽度300像素，属性值可以替换为百分比表示占据窗口的百分比，可以随窗口的变化变化

height属性表示高度150像素

---

#### 2.tr标签（行标签）

```html
<tr align='center'>
	行标签
</tr>
```

在表格标签的下一级可以创建一行

align属性表示行居中

---

#### 3.td标签（单元格标签）

```html
<td align='center'>
	单元格标签
</td>
```

在行标签的下面创建一个单元格

align属性表示单元格居中

---

#### 4.th标签（单元格头标签）

```html
<th>
	单元格头标签，自动加粗，居中
</th>
```

---

#### 5.单元格合并

```html
<td rowspan='3'>
	表示与下面两个单元格合并，但是下面两个单元格要删除
</td>
```

```html
<td colspan='3'>
    表示与右边两个单元格合并，但是右边两个单元格要删除
</td>
```

---

#### 6.thead tbody tfooy（表格分割标签）

```html
<thead>
    头
</thead>
<tbody>
    身体
</tbody>
<tfoot>
    脚
</tfoot>
```

这几个标签是为了分割表格，方便以后编写js代码

---

### 6.背景颜色和背景图片

在body标签通过属性的方式来更换背景颜色和背景图片

```html
<body bgcolor='red' backgroud='path'>
    
</body>
```

---

### 7.HTML图片img标签

```html
<img src='path' width='100px' title='鼠标放到图片上显示的文字' alt='当图片加载失败时显示的文字'>
```

---

### 8.HTMLa标签（超链接标签）

```html
<a href="https://">超链接文字</a>
<a href="https://">
	<img src='path'/>
</a>
```

> href可以跟网络资源也可以跟本地资源
>
> 超链接有个target属性，可取值
>
> _blank：新窗口
>
> _self：当前窗口
>
> _top：顶级窗口
>
> _parent：父窗口

超链接的作用：浏览器向服务器发送请求(request)，服务器向浏览器发送数据(response)

超链接可以发送数据，但是数据都是固定的且都是以get的方式发送

---

### 9.HTML列表

#### 1.无序列表

```html
<ul type=''>
    <li>
    	一项内容    
    </li>
    <li>
    	另一项内容    
    </li>
</ul>
```

type属性代表前面的图形，取值可以是circle（圆形），squre（方形），disc（点）

---

#### 2.有序列表

```html
<ol type=''>
    <li>
    	内容
    </li>
</ol>
```

此时的type不在代表图形，而是下标，可以是英文，数字

列表都可以嵌套使用

---

### 10.HTML表单（重要内容）

#### 1.表单有什么用

收集用户信息，表单展现之后，用户填写表单，点击提交按钮提交给服务器

---

#### 2.表单的绘制

```html
<form action='url' method='post'>
    表单内容
</form>
<form>
    
</form>
```

表单需要提交给服务器，form标签中的action属性和href差不多，在里面填写服务器的地址，属性method默认为get，提交的表单数据不会隐藏，为post时会隐藏，为了对用户的敏感信息隐藏，建议使用post方式提交

---

#### 3.按钮和文本框

当填写完表单后还要绘制按钮和文本框供用户交互，用户点击按钮后能向服务器提交表单

```html
<form action='url' method='post'>
    <input type='text' name='userName'>
    <input type='password' name='password'>
    <input type='submit' value='按钮显示的文本'/>
</form>
```

标签后有name属性的标签一定会提交给服务器 ，当value没有填写时，会提交空字符串 ，java代码会得到空的字符串

```html
<input type='text'> 文本框
<input type='password'> 密码框
<input type='checkbox'> 选择框
<input type='radio'> 单选按钮，当多个个radio的属性name相同时代表他们是同一组的，此时我们只能从他们之间选择一个，同时radio的value应该提前指定
<input type='button'> 普通按钮（无提交能力）
<input type='reset'> 清空文本框
<input type='submit'> 只为submit时，才表示这个按钮有提供表单的能力 
```

---

#### 4.下拉列表

当我们有可数种选择时可以绘制下拉列表供用户选择

```html
<select name="Edcation">
    <option value="1">博士</option>
    <option value="2">硕士</option>
    <option value="3" selected>本科</option>
    <option value="4">带专</option>
</select>
```

选框同样要提前指定value，带有selected属性的是默认选中

多选下拉列表

```html
<select name="Edcation" multiple='mutiple' size='2'>
    <option value="1">博士</option>
    <option value="2">硕士</option>
    <option value="3" selected>本科</option>
    <option value="4">带专</option>
</select>
```

属性multiple可以让下拉列表可以多选，size可以调整下拉列表的条目数

---

#### 5.文本域

当用户需要输入大量文本时，可以选用文本域

```html
<textarea rows="10" cols="20" name="interst"></textarea>
```

文本域没有value属性，用户填写的内容就是value，可以预先指定行数和列数

---

#### 6.File文件控件

```html
<input type='file'/>
```

使用文件控件可以向服务器上传文件

---

#### 7.隐藏域控件

当我们想让网页提交数据但是不想让用户看到时，使用隐藏域控件

```html
<form>
    <input type='hidden' name='userId' value='123'>
</form>
```

input标签的type属性为hidden时，网页上不会显示这个条目，但是表单提交时这个条目会提交数据

---

#### 8.readonly和disable属性

当我们在input标签中使用这两个属性，那么用户是无法对这个标签进行操作的

```html
<input type='text' name='userId' value='123' readonly>
<input type='text' value='123' disabled>
```

他们的共同点是，都能让标签变为只读属性

不同点是，readonly可以提交数据，disable不能提交

---

#### 9.maxlength控件

maxlength控件可以限制文本框的最大输入长度

```html
<input type='text' name='userId' value='123' maxlength='3'>
```

---

#### 10.`submit()`方法

HTML中的表单对象，有一个方法`submit()`调用这个方法可以直接提交表单，我们可以将原来的`submit`按钮提交改为在`button`上绑定一个`click`事件，验证表单重点条目是否符合规则，符合提交，不符合则退回

```js
document.getElementById("signUp").onclick = function () {
    if(pok && uok && mok){
        document.getElementById("form").submit()
    }else{
        alert("Illegal submit, Please modify your input")
    }
}
```

---

#### .提交格式（重点）

HTTP协议规定的的**提交格式action?name=value&name=value&name=value...**

在java中我们需要对字符串切割来获得name，value

---



