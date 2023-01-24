## 1.什么是JavaScript

JavaScript是一门脚本语言，是用来操作HTML和CSS的

脚本语言都有一个特点：脚本语言最终运行的程序是以普通文本的形式来保存的

JS的语法同样不严格，字符串可以用双引号也可以用单引号，每个语句后面也可以不写分号

---

## 2.JavaScript的嵌入

### 1.在事件句柄中嵌入JS代码

JavaScript是一门事务驱动型的语言，一般都是依靠事件触发执行JS代码的，例如鼠标单击事件click，鼠标双击事件dbclick，获取焦点事件focus，失去焦点事件blur...

在JS中任何一个事件都有一个事件句柄，所有的事件句柄都是在事件名称前加on：

> click 对应 onclick
>
> focus对应onfucus
>
> ...

所有的事件句柄都是以标签属性的形式存在

```html
<input type"button" value="hello" onclick="JS代码">
```

onclick后面的JS代码会在鼠标单机事件之后执行，而不是加载页面时执行

---

### 2.使用脚本块嵌入JS代码

在脚本块中的代码会在页面加载的时候自上而下逐行执行，不需要事件

```html
<script type="text/javascript">
    //或者我们可以这样使用，在页面加载完毕后运行匿名函数
    window.onload = function(){
        //对某个标签绑定事件，当事件发生函数就会运行
        document.getElementByID.事件(onblur, onfocus...) = function(){
            函数体
        }
    }
    
</script>
```

---

### 3.引入外部JS文件

 ```html
 <script src="JS文件路径" type="text/javascript">
 	console.log(123) //输出到控制台
 </script>
 ```

从外部引入的JS文件同样会自上而下执行

----

## 3.JavaScript语法

### 1.变量

JS是一种若类型语言，JS中的变量不需要指定类型，可以赋值为任何类型

```javascript
var age = 20, x//定义一个age变量赋值为20，x变量没有赋值默认值为undefined
```

---

#### 2.全局变量和局部变量

全局变量在函数体之外，全局变量的生命周期是：浏览器打开时声明，浏览器关闭时销毁，尽量少用，太占内存

局部变量是函数体中的声明，函数执行时局部变量开辟控件，函数执行之后销毁，生命周期较短

---

### 2.函数

函数的定义优先级最高的，即使先调用再定义也能执行

函数调用之后才会执行

```javascript
function 函数名(参数列表){
    函数体
}
//另一种方式定义函数
sum = function(参数列表){
    console.log()
}
//匿名函数，有点类似匿名内部类，不声明函数而是让他直接执行
var div1 = function(){
	函数体
}


//调用函数
函数名()
```



---

### 3.函数的扩展

某些浏览器可能不支持新的函数，所以我们可以自己扩展函数

比如当IE不支持`trim()`函数我们可以这样写

```js
String.prototype.trim = function(){
    //this代表当前字符串，即调用trim的字符串
    return s.replace(/^\s*/, "").replace(/\s*$/, "")
}
```



---

### 4.常用函数与事件

```js
window.onload //页面加载事件
document.getElement //获取标签对象
console.log //在控制台打印
alert() //弹窗
正则表达式.test() //正则匹配
字符串.trim() //去除字符串前后的空格
```

---

### 5.运算符

#### 1.`void()`

`void()`运算符会执行括号内的表达式但是不返回任何结果

---

## 4.JavaScript常用事件

```javascript
bulr //失去焦点
change //下拉列表选中项改变或文本框改变
click //鼠标单击
dblclick //鼠标双击
focus //获得焦点
keydown //键盘按下
keyup //键盘弹起
load //页面加载完毕
mousedown //鼠标按下
mouseover //鼠标经过
mousemove //鼠标移动
mouseout //鼠标离开
mouseup //鼠标弹起
reset //重置表单
select //文本选中
submit //表单提交 
```

### 1.键盘类事件的特殊性

当使用键盘类事件时，可以使用函数来捕捉键盘输入

```js
document.getElementById("123").onkeydown = function (e){
	alert(e.key)
}
//这里的e就是键盘传入的参数，键盘事件对象，使用e.key可以获得键盘输入
```

---

## 5.JavaScript对象和属性

### 1.JS对于属性的修改

在JS中，我们一旦拿到了一个标签，我们就能通过标签对象来修改标签的所拥有的属性

```javascript
<input id='input' type='text' name='name' value='value'>
var inp = document.getElementById('input')//我们获取input标签后我们就能访问他的所有属性
inp.id = 
inp.type =
...
```

---

### 2.document对象

把网页中的文档抽象成`document`对象，这个对象中有很多关于操作文档的一些属性和方法，我们就可以通过代码操作文档上的属性和方法，动态的控制页面内容，这就是DOM编程

```javascript
document.getElementById("标签id")
document.write(打印内容)
```

通过document对象的方法，我们可以通过一个id获得一个元素

---

### 3.innerHTML属性

当我们拿到了一个标签后，我们可以通过innerHTML属性来获得标签里面的内容，同时也可以设置修改里面的内容

```javascript
var divIn = document.getElementById("")
console.log(divIn.innerHTML) //显示标签内容
divIn.innerHTML = "Hello World" //也可以是HTML代码 
onsole.log(divIn.innerHTML) //显示修改后的结果
```

需要注意的是，标签中还有一个属性叫做innerText，通过这个属性我们同样能修改HTML的内容，但是通过这个属性修改的内容即使是HTML代码不会作为HTML解释并执行，只会作为字符串展示

---

### 4.value属性

当我们获得一个HTML标签后，我们可以通过value属性来获得用户输入的内容

```javascript
var emt = document.getElementById()
console.log(emt.value) //输出value值
```

比如获取下拉列表的`value`

```html
<select onchange="alert(this.value)">
    <option value="1">广西</option>
    <option value="2">广东</option>
</select>
```



---

### 5.Date类

通过Date类我们可以获取系统时间

```js
var t = new Date()
```

具体使用查表

---

### 6.Array类

```js
var arr = [] //创建一个数组
arr.push() //尾部添加元素
arr.pop() //弹出尾部元素 js中的数组可以模拟栈

var a = [1,2,3,4]
var str = a.join('-') //str = "1-2-3-4"
```

---

### 7.window对象

整个浏览器抽象成`window`对象，其中有很多的属性和方法，可以通过这些方法控制浏览器窗口，这就是BOM编程

``` js
window.open(url, 属性) //在新窗口打开url，属性可以控制打开窗口的级别，查表
window.close() //关闭当前窗口

window.confirm(内容) //弹出确认框
window.eval(字符串) //可以将字符串当做js代码解释并执行
```



---

## 6.正则表达式

正则表达式一般是用来匹配字符串，用来判断字符串是否合法，需要时直接查表

### 1.在JS中用来匹配字符串

在JS中正则对象有test函数，会判断字符串是否符合正则表达式

```javascript
var email = "1212@qq.com"
var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ //正则表达式用斜杠括住
var ok = reg.test(email) //返回bool类型的值
```

---

## 7.什么是JSON

`JSON`是`JavaScript Object Notation`（JavaScript对象标记）是一种数据交换格式，JSON的主要作用是一种标准的数据交换格式

JSON是一种标准的轻量级数据交换格式：体积小，易解析

实际开发中使用较多的是JSON和XML，但是XML体积较大，解析麻烦，优点是语法严谨

```js
//一个json（无类型对象）
var json = {
    "sno":"100"
    "sname":"zhangsan"
    "sage":"16"
}
//json列表
var jsons = {
    {"son":"110", "sname":"Zhangsan", "gender":"1"},
	{"son":"110", "sname":"Zhangsan", "gender":"1"},
    {"son":"110", "sname":"Zhangsan", "gender":"1"}
}

//没有json对象之前构造函数，定义类创建对象
Student = function(sno, sname, sage){
    this.sno = sno
    this.name = name
    this.sage = sage
}
//麻烦
var stu = new Student("110","Lisi","17")

//Java程序将数据拼成json格式的字符串并响应给浏览器，此时数据只是一个json格式的字符串，还不是一个json对象，我们需要用eval函数把他转换成json对象

var fromJava = "{\"sno\":\"100\",\"sname\":\"Zhangsan\",\"sage\":\"16\"}"

//转换成json对象
window.eval("var json =" + fromJava)

//访问json对象的方法
alert(json.sno)
alert(json["sno"])
```

