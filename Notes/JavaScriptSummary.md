[TOC]

# 1.JavaScript初步

## 1.JS脚本的引入

使用JS脚本的第一步肯定引入脚本，一般可以直接在页面中编写，或者从外部文件引入

```js
<script async src="js/vendor/jquery.js"></script>
<script defer src="js/vendor/jquery.js"></script>
<script>
    function (){
    
	}
</script>
```

其中async表示异步的加载，**只适用于外部引入的文件**，js的执行顺序不会自上而下的执行，defer表示阻塞，js会自上而下的执行

脚本调用策略小结：

- 如果脚本无需等待页面解析，且无依赖独立运行，那么应使用 `async`。
- 如果脚本需要等待页面解析，且依赖于其他脚本，调用这些脚本时应使用 `defer`，将关联的脚本按所需顺序置于 HTML 中。



## 2.运算符

JS中的运算符，与Java不同的只有

+ 等于运算符 ===
+ 不等运算符!== 



## 3.数字类型

JS中所有的数字类型都叫做Number

### 1.字符串转化为数字

```js
let num = Number('123');
```



## 4.JS字符串

JS中的字符串也像Java一样，是一个对象，这个对象内置了很多方法

### 1.获取字符串长度

```js
let s = '123';
s.length
```



### 2.下标访问

```js
s[0] = 1;
```



### 3.查找子串

使用indexOf可以寻找子串开始的位置

```js
s.indexOf('12'); //return 0
```



### 4.获取子串

```js
s.slice(3); //return 123
s.slice(1, 2); // return 23
```



### 5.大小写转换

```js
s.toLowerCase();
s.toUpperCase();
```



### 6.字符替换

使用replace返回替换第一个匹配的位置的字符串，replaceAll返回替换所有匹配的地方的字符串

```js
s.replace('12', '1');
s.replaceAll('1', '12');
```



### 7.字符串分割

使用split分割字符串，并返回一个字符串数组

```js
let myData = 'Manchester,London,Liverpool,Birmingham,Leeds,Carlisle';
data = myData.split(',');
```



## 5.JS数组

JS数组与Java，C++的数组不太一样，他使用中括号括起来，并且可以同时存储多种元素

```js
let sequence = [1, 1, 2, 3, 5, 8, 13];
let random = ['tree', 795, [0, 1, 2]];
```



### 1.获取数组长度

```js
sequence.length
```



### 2.数组转换为字符串

使用join可以自定义分隔符，使用toString则默认为','

```js
data = [123, 'abd', 4124];
let s = data.join(','); //return "123,adb,4124"
```



### 3.删除和添加元素

首先是从尾部添加和从尾部删除

```js
data.push('123');
data.pop();
```

从头部添加和删除

```js
data.unshift('123');
data.shift();
```



### 4.获取数组元素

使用下标获取

```js
data[0];
```



## 6.Math库

Math库中有许多内置方法，下面会列出一些较为常用的方法

### 1.随机数生成

使用random生成一个0-1直接的随机数

```js
let a = Math.random();
```



### 2.去除小数部分

使用floor去除小数部分

```js
Math.floor(Math.random()*100);
```



## 7.事件

我们在之前的例子中已经学习了很多使用事件触发的例子，比如说下面的内容



### 1.常见事件

- [`btn.onfocus`](https://developer.mozilla.org/zh-CN/docs/Web/API/Window/focus_event)及[`btn.onblur`](https://developer.mozilla.org/zh-CN/docs/Web/API/Window/blur_event) — 颜色将于按钮被置于焦点或解除焦点时改变（尝试使用 Tab 移动至按钮上，然后再移开）。这些通常用于显示有关如何在置于焦点时填写表单字段的信息，或者如果表单字段刚刚填入不正确的值，则显示错误消息。
- [`btn.ondblclick`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/dblclick_event) — 颜色将仅于按钮被双击时改变。
- [`window.onkeypress`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/keypress_event), [`window.onkeydown`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/keydown_event), [`window.onkeyup`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/keyup_event) — 当按钮被按下时颜色会发生改变。`keypress` 指的是通俗意义上的按下按钮 (按下并松开), 而 `keydown` 和 `keyup` 指的是按键动作的一部分，分别指按下和松开。注意如果你将事件处理器添加到按钮本身，它将不会工作 — 我们只能将它添加到代表整个浏览器窗口的 [window](https://developer.mozilla.org/zh-CN/docs/Web/API/Window)对象中。
- [`btn.onmouseover`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/mouseover_event) 和 [`btn.onmouseout`](https://developer.mozilla.org/zh-CN/docs/Web/API/Element/mouseout_event) — 颜色将会在鼠标移入按钮上方时发生改变，或者当它从按钮移出时。



### 2.事件的使用

我们可以像下面一样给一个按钮或者其他对象添加事件触发

```js
const btn = document.querySelector('button');

function bgChange() {
  const rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
  document.body.style.backgroundColor = rndCol;
}

btn.onclick = bgChange;
```





### 3.addEventListener和removeEventListener

使用事件监听器可以比上面的的方法更加高效，同时在大型项目中，添加和移除监听器也变得更为的方便

对于上面的事件的改写

```js
const btn = document.querySelector('button');

function bgChange() {
  const rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
  document.body.style.backgroundColor = rndCol;
}

btn.addEventListener('click', bgChange);
```

或者

```js
btn.addEventListener('click', function() {
  var rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
  document.body.style.backgroundColor = rndCol;
});
```



您也可以给同一个监听器注册多个处理器，下面这种方式不能实现这一点：

```js
myElement.onclick = functionA;
myElement.onclick = functionB;
```

第二行会覆盖第一行，但是下面这种方式就会正常工作了：

```js
myElement.addEventListener('click', functionA);
myElement.addEventListener('click', functionB);
```



### 4.事件对象

有时候在事件处理函数内部，您可能会看到一个固定指定名称的参数，例如`event`，`evt`或简单的`e`。这被称为**事件对象**，它被自动传递给事件处理函数，以提供额外的功能和信息。例如，让我们稍稍重写一遍我们的随机颜色示例：

```js
function bgChange(e) {
  const rndCol = 'rgb(' + random(255) + ',' + random(255) + ',' + random(255) + ')';
  e.target.style.backgroundColor = rndCol;
  console.log(e);
}

btn.addEventListener('click', bgChange);
```

在这里，您可以看到我们在函数中包括一个事件对象`e`，并在函数中设置背景颜色样式在 `e.target` 上——它指的是按钮本身。事件对象 `e` 的 `target` 属性始终是事件刚刚发生的元素的引用。所以在这个例子中，我们在按钮上设置一个随机的背景颜色，而不是页面