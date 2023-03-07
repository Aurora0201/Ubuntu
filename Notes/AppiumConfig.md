[TOC]

# 1.Appium的配置

## 1.JDK的安装

+ 需要注意的是，Android目前还是使用JDK8比较好



## 2.node的安装

+ 先从官网上下载合适的node包，解压缩，并放到合适的位置[下载地址](https://nodejs.org/en/)

+ 配置环境变量，在配置文件中添加下面的语句

    ```
    export NODE_HOME=/usr/local/node
    export PATH=$PATH:$NODE_HOME/bin
    ```

+ 验证安装，在terminal打出`node -v`和`npm -v`，出现版本信息证明安装成功



## 3.SDK的安装

+ 从网站上下载包，解压缩，放到合适的位置[点击下载](http://dl.google.com/android/android-sdk_r24.4.1-linux.tgz)

+ 配置环境变量

    ````
    export ANDROID_HOME=/usr/local/android-sdk
    export PATH=$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$PATH
    ````

+ 使用命令`android update sdk --no-ui`等待其他工具补充完整即可完成安装

+ 验证安装，在终端输入下面的命令

    + 输入`android`出现安卓管理窗口则安装成功
    + 输入`adb devices`验证adb工具是否安装成功
    + 输入`monkeyrunner`验证monkeyrunner工具是否安装成功
    + 输入`uiautomatorviewer`验证抓图工具是否安装成功



## 4.Appium的安装

+ 使用npm安装即可`npm install -g appium`

+ 终端输入`appium`验证是否安装成功

    