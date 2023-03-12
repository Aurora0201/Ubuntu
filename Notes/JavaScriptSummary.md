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