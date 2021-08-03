### MonkeyRunner
MonkeyRunner常用的API
#### MonkeyRunner API - alert
```
警告框
void alert(string message,string tilte,string okTitle)
``` 
举个栗子
```
#!/usr/bin/python
# -*- coding: utf-8 -*
from com.android .monkeyrunner import MonkeyRunner
MonkeyRunner.alert('Hello Monkey','Monkey','OK')
```
新建个文件命名为hello.py<br>
进入monkeyrunner所在目录输入如下命令
```
monkeyrunner F:\python\monkey\hello.py
```
效果如下（确认已具备python环境，环境变量配置完成）

![1858781-f0061764c4388bb4.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0ab597f1e39b455fa38ec59fee73243b~tplv-k3u1fbpfcp-watermark.image)

### 运行脚本方法
```
monkeyrunner  <script-file path>
```

#### MonkeyRunner API - waitForConnect
```
等待设备连接，有多个device id,需要指明具体哪个设备
waitForConnection(float timeout,string deviceid)
``` 
#### MonkeyDevice API - drag
```
拖动
drag(tuple start,tuple end,float duration,integer steps)
start 起点位置，
end 终点位置，
duration 手势持续的时间
step 插值点的步数，默认10
``` 

#### MonkeyDevice API - press
```
按键
press(string keycode,dictionary type)
keycode名,DOWN、UP、DOWN_AND_UP
``` 

#### MonkeyDevice API - startActivity
```
启动
startActivity(package+'/'+activity)
``` 

#### MonkeyDevice API - touch
```
点击
touch(integer x,integer y,integer type)
x坐标值 ,y坐标值 
type:DOWN、UP、DOWN_AND_UP
``` 

#### MonkeyDevice API - type
```
输入
type(string message)
``` 

#### MonkeyImage API - takeSnapshot
```
截屏
MonkeyImage takeSnapshot()
``` 

#### MonkeyImage API -sameAs
```
图像对比
boolean sameAs(MonkeyImage other,float percent)
``` 

#### MonkeyImage API -writeToFile
```
保存图像文件
void writeToFile(string path,string format)
``` 


![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_runner_1.png)

![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_runner_2.png)

参照如上的图，我们现在需要通过monkeyrunner来模拟操作一系列事件<br>
1)打开这个应用<br>
2)点击Receiver广播<br>
3)点击REGISTER A RECEIVER<br>
4)编辑框输入Hello，截图<br>
5)点击SEND A RECEIVER<br>
6)点击UNREGISTER A RECEIVER<br>
7)点击CLEAR清空编辑框<br>
8)返回退出回到首页<br>
需要完成这8个过程<br>
新建个文件命名为monkey.py,编辑如下代码，运行看效果吧
```
#!/usr/bin/python
# -*- coding: utf-8 -*
from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice,MonkeyImage
#连接设备
device = MonkeyRunner.waitForConnection(3,"emulator-5554")
#启动App
device.startActivity("me.peace.ka/me.peace.ka.MainActivity")
MonkeyRunner.sleep(2)
#点击Receiver广播
device.touch(100,400,"DOWN_AND_UP")
MonkeyRunner.sleep(1)
#点击REGISTER A RECEIVER
device.touch(100,600,"DOWN_AND_UP")
MonkeyRunner.sleep(1)
#点击编辑框
device.touch(60,300,"DOWN_AND_UP")
MonkeyRunner.sleep(1)
#输入Hello
device.type("Hello")
MonkeyRunner.sleep(1)
#截图
image = device.takeSnapshot()
image.writeToFile("D:/test.png","png")
#返回键隐藏键盘
device.press("KEYCODE_BACK","DOWN_AND_UP")
MonkeyRunner.sleep(1)
#点击SEND A RECEIVER
device.touch(100,400,"DOWN_AND_UP")
MonkeyRunner.sleep(1)
#点击UNREGISTER A RECEIVER
device.touch(100,800,"DOWN_AND_UP")
MonkeyRunner.sleep(1)
#点击CLEAR
device.touch(100,900,"DOWN_AND_UP")
MonkeyRunner.sleep(5)
#返回键返回上级页面
device.press("KEYCODE_BACK","DOWN_AND_UP")
MonkeyRunner.sleep(5)
#返回键退出应用
device.press("KEYCODE_BACK","DOWN_AND_UP")
MonkeyRunner.sleep(5)
```
