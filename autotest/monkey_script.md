### MonkeyScript常用命令
```
adb shell monkey -f <scriptfile> <event-count>
```
执行Monkey脚本的命令

#### DispatchTrackball命令
轨迹球事件
```
DispatchTrackBall(long downTime,long eventide,int action,float x ,float y,float pressure,float size,
int metastate,float xprecision,float yprecision,int device,int edgeflags)
action 0 代表按下，1代表弹起，x和y代表坐标点，主要关注这3个参数，其余可通过命令设置默认值 ，一次事件需要action 0与1成对出现
```

#### DispatchPointer命令
点击事件
```
DispatchPointer(long downTime,long eventide,int action,float x ,float y,float pressure,float size,
int metastate,float xprecision,float yprecision,int device,int edgeflags)
action 0 代表按下，1代表弹起，x和y代表坐标点，主要关注这3个参数，其余可通过命令设置默认值，一次事件需要action 0与1成对出现 
```

#### DispatchString命令
输入字符串事件
```
DispatchString(String text)
```

#### LaunchActivity命令
启动应用
```
LaunchActivity(package,Activity)
```

#### UserWait命令
等待事件
```
UserWait(1000) 
等待1s时间，单位为ms
```

#### DispatchPress命令
按下键值
```
DispatchPress(int keycode) #keycode 66 回车键
```

举个栗子<br>
UI Automator View可分析程序在手机布局信息， 在android sdk\tools\bin这个目录下可找到
uiautomatorviewer.bat文件，打开即能弹出UI Automator View之个工具，点击左上的手机图标
可获取当前手机屏幕图，选择屏幕图中的控件，则会在右侧展示这个控件对应的布局信息和一些属性信息，比如这个选择了Receiver广播，右侧信息告诉这个位于RecyclerView中，是其中的一个item并且是个TextView控件，而bounds[48,377][325,442]这项属性告诉这个TextView的边框位置左上角起始点是(48,377),右下角结束点是(325,442)。之前提到的轨迹球事件、点击事件需要中这个范围选取一个点来操作，即参数x，y。比如选择点(100,400)，这个点位于这个TextView之内。


![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_script_1.png)

![](https://github.com/peace710/AJLife/blob/master/autotest/image/monkey_script_2.png)

参照如上的图<br>
我们现在需要通过monkey script来模拟操作一系列事件<br>
1)打开这个应用<br>
2)点击Receiver广播<br>
3)点击REGISTER A RECEIVER<br>
4)编辑框输入Hello<br>
5)点击SEND A RECEIVER<br>
6)点击UNREGISTER A RECEIVER<br>
7)点击CLEAR清空编辑框<br>
8)返回退出回到首页<br>
需要完成这8个过程<br>
### 代码实现
```
typ=user
count=10
speed=1.0
start data >>
#启动应用跳转至目标界面
LaunchActivity(me.peace.ka,me.peace.ka.MainActivity)
UserWait(2000)
#点击Receiver广播
DispatchPointer(10,10,0,100,400,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,100,400,1,1,-1,1,1,0,0)
UserWait(1500)
#点击REGISTER A RECEIVER
DispatchPointer(10,10,0,100,600,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,100,600,1,1,-1,1,1,0,0)
UserWait(1500)
#点击编辑框
DispatchPointer(10,10,0,60,300,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,60,300,1,1,-1,1,1,0,0)
#输入Hello
DispatchString(Hello)
UserWait(1000)
#返回键隐藏键盘
DispatchPress(4)
UserWait(1000)
#点击SEND A RECEIVER
DispatchPointer(10,10,0,100,400,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,100,400,1,1,-1,1,1,0,0)
UserWait(1500)
#点击UNREGISTER A RECEIVER
DispatchPointer(10,10,0,100,800,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,100,800,1,1,-1,1,1,0,0)
UserWait(1500)
#点击CLEAR
DispatchPointer(10,10,0,100,900,1,1,-1,1,1,0,0)
DispatchPointer(10,10,1,100,900,1,1,-1,1,1,0,0)
UserWait(5000)
#返回键返回上级页面
DispatchPress(4)
UserWait(5000)
#返回键退出应用
DispatchPress(4)
UserWait(5000)
```
新建个文件编辑如上的代码，文件命名为monkey.script<br>
使用adb命令将脚本push至手机中
```
adb push D:\pdf\monkey\monkey.script /sdcard/
```
然后执行monkey命令，比如执行2次
```
adb shell monkey -f /sdcard/monkey.script 2
```



