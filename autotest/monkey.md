#### 为什么要开展压力测试？
1.提高产品的稳定性 <br>2.提高产品的留存率
#### 什么时开展压力测试？
1.首轮功能测序通过后<br>  2.下班后的夜间进行

#### 什么是Monkey?
Monkey是发送伪随机用户事件的工具。

#### 什么是MonkeyScript
MonkeyScript是一组可以被Monkey识别的命令集合。<br>
MonkeyScript可以完成重复固定的操作。

#### 什么是MonkeyRunner？
MonkeyRunner提供一系列的API。<br>
MonkeyRunner可以完成模拟事件及截图操作。

#### Monkey和MonkeyRunner的区别
Monkey：在adb shell中，生成用户或系统的伪随机事件。<br>
MonkeyRunner：通过API定义特定命令和事件控制设备。

#### MonkeyRunner APIs
MonkeyRunner：用来连接设备或模拟器。<br>
MonkeyDevice：提供安装、卸载应用、发送模拟事件。<br>
MonkeyImage：完成图像保存，及对比的操作。

#### 压力测试结果
1.Crash：即崩溃，应用程序在使用过程中，非正常退出。<br>
2.ANR：程序未响应（Application Not Responding）

#### 环境
Andrid SDK（ADB、Monkey)  Python

#### 简单使用
adb 连接上手机/盒子，输入如下指令，随机执行1000条指令
```
adb shell monkey 1000
```
![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_1.png)

activityResuming（com.android.settings)<br>
表示com.android.settings这个应用曾从后台到切换到前台，括号中内容为应用包名。<br>
Events injected 1000与adb shell monkey 1000相对应，1000代表该1000条事件执行应用正常，并无异常。如果产生异常情况，那么数字将于小于adb shell monkey后的指令数。 

 #### 指定应用monkey测试
```
adb shell monkey -p packageName 1000
```
monkey测试指定应用，packageName替换测试应用的真实包名<br>
举个栗子：adb shell monkey -p com.android.settings 1000 <br>
该条命令表示在com.android.settings这个应用随机执行1000事件

## Monkey高级参数的应用
#### throttle参数
```   
adb shell monkey --throttle <milliseconds>
```
指定事件之间的间隔</br>
举个栗子：</br>
adb shell monkey --throttle 5000 1000</br>
monkey随机产生1000条事件，每个事件间隔5s

#### seed参数
```   
adb shell monkey -s <seed> <event-count>
```
指定随机生成数的seed值</br>
举个栗子：</br>
adb shell monkey -p me.peace.ka -s 100 50</br>
monkey同个seed值执行的操作内容是相同，栗子中的seed值是1000，事件数为50

#### 触摸事件
```   
adb shell monkey --pct-touch <percent>
```
设定触摸事件的百分比</br>
举个栗子：</br>
adb shell monkey -v -p me.peace.ka --pct-touch 100 1000</br>
-v 参数会展示所有事件参数</br>
--pct-touch 指定事件为触摸事件</br>
100 事件百分比为100%</br>
1000 事件数</br>
cmd窗口回显</br>
Event Percentages 事件完成百分比

#### 动作事件
```   
adb shell monkey --pct-motion <percent>
```
设定动作事件的百分比</br>
举个栗子：</br>
adb shell monkey -v -p me.peace.ka --pct-motion 100 1000</br>
-v 参数会展示所有事件参数</br>
--pct-motion 指定事件为动作事件</br>
100 事件百分比为100%</br>
1000 事件数

组合使用，触摸事件为30%、动作事件为50%
```
adb shell monkey -v -p me.peace.ka --pct-touch 30 --pct-motion 50 100
```
### 其他事件
#### 轨迹球事件
```   
adb shell monkey --pct-trackball <percent>
```
设定轨迹球事件百分比

#### 基本导航事件
```   
adb shell monkey --pct-nav <percent>
```
设定基本导航事件百分比，输入设备的上、下、左、右

#### 主要导航事件
```   
adb shell monkey --pct-majornav <percent>
```
设定主要导航事件百分比，兼容中间键、返回键、菜单按键

#### 系统导航事件
```   
adb shell monkey --pct-syskeys <percent>
```
设定系统导航事件百分比，HOME、BACK、拨号及音量键

#### 启动Activity事件
```   
adb shell monkey --pct-appswitch <percent>
```
设定启动Activity的事件百分比

#### 不常用事件
```   
adb shell monkey --pct-anyevent <percent>
```
设定不常用事件的百分比

#### 崩溃事件
```   
adb shell monkey --ignore-crashes <event-count>
```
忽略崩溃和异常

#### ANR超时事件
```   
adb shell monkey --ignore-timeouts <event-count>
```
忽略超时事件


### CRASH结果析取
举个栗子:</br>
预先的手机中装入一个有crash的apk(包名为me.peace.fly)</br>
输入如下monkey命令
```
adb shell monkey -p me.peace.fly -v 1000
```

![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_2.png)

图上NullPointerException红框部分，即monkey测试中产生crash时异常的打印信息，这段内容可拷贝至文本文件中提供给开发分析修复crash。

Events injected:373，表示monkey命令中的1000个事件，执行第373个事件，就产生crash
using seed 1577869486128 ，这个表示当前使用seed进行的随机monkey，记录该次seed可再次复现crash，待开发修复crash，也可使用验证，即使用如下的命令
```
adb shell monkey -p me.peace.fly -s 1577869486128 1000
```
使用如下的命令，monkey过程中产生crash也不会停止，直到定义的事件数达成为止
```
adb shell monkey -p me.peace.fly --ignore-crashes 1000
```
如果期间发生crash，程序会重启继续执行，但每次crash的日志都会被记录下来，如下图，这些内容均可以拷贝出来给到开发作分析


![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_3.png)


### ANR结果析取
举个栗子:</br>
预先的手机中装入一个能产生ANR的apk(包名为me.peace.fly)</br>
输入如下monkey命令
```
adb shell monkey -p me.peace.fly -v 1000
```

![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_4.png)
如果产生ANR，则会有图中类似的日志信息，可拷贝给开发分析处理

使用如下的命令可在monkey过程中避免crash和anr产生
```
adb shell monkey -p me.peace.fly --ignore-crashes --ignore-timeouts -v 1000
```
