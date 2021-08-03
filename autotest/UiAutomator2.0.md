### UiAutomator2.0介绍
UiAutomator2.0是android的自动化测试框架，可跨APP。与instrumentation框架不同，UiAutomator2.0不需要测试对象源码，因此，为黑盒测试框架。<br>
同时，与Monkey不同，UiAutomator2.0不以坐标为主线，而是通过控件属性过滤（比如搜索文本为“提交”的按钮），获取控件本身。<br>

### UiAutomator2.0定位元素
UiAutomator2.0可以通过资源id,文本内容、属性描述、选择属性状态、焦点状态来定位控件，然后执行一些操作验证。<br>
如下定位元素的方式和对应的Api:<br>
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_1.png)
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_2.png)

### UiAutomator2.0常用操作
UiAutomator2.0定位元素后需要执行一些操作，主要包括点击、滑动、长按、输入、按键和拖动，其对应的Api分别如下 <br>
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_3.png)
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_4.png)


#### 实现个简单的栗子
需要使用到uiautomatorviewer工具<br>
依旧是之前MonkeyScript、MonkeyRunner实现的功能<br>
1)滑动拖出应用列表，点击打开KotAndord<br>
2)点击Receiver广播<br>
3)点击REGISTER A RECEIVER<br>
4)编辑框输入Hello<br>
5)点击SEND A RECEIVER<br>
6)点击UNREGISTER A RECEIVER<br>
7)点击CLEAR清空编辑框<br>
8)返回退出回到首页<br>

![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_5.png)

![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_6.png)

![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_7.png)

通过uiautomatorviewer工具，可以看到text、resource-id、content-desc、checked和focused属性值内容.<br>
通过这些可以定位出选择的控件，如下图我们可以定位出SEND A RECEIVER按钮<br>
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_8.png)



### UiAutomator2.0使用
创建android studio工程，在build.gradle中加入uiautomator2.0的依赖<br>
注意:uses-sdk:minSdkVersion 18，最小支持sdk需要是18<br>
```
androidTestImplementation 'androidx.test.uiautomator:uiautomator:2.2.0'
```

在androidTest新建KotAndroidTest测试类，编辑如下的代码，运行代码测试，观察手机运行效果是否按照脚本内容去执行输出。<br>
![](https://github.com/peace710/AJLife/blob/master/autotest/image/UiAutomator2.0_9.png)


```
@RunWith(AndroidJUnit4::class)
class KotAndroidTest {
    var instrumentation:Instrumentation ? = null
    var device:UiDevice ?= null

    @Before
    fun setUp(){
        instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)
    }

    @Test
    fun kotTest(){
        //滑动屏幕拉出应用列表
        device?.swipe(510,1527,510,527,10)
        //等待1s
        Thread.sleep(1000)
        //按坐标点击KotAndroid
        device?.click(600,1000)
        Thread.sleep(1000)
        //使用文本text定位到元素点击
        device?.findObject(By.text("Receiver广播"))?.click()
        Thread.sleep(2000)
        //使用资源id定位到元素点击
        device?.findObject(By.res("me.peace.ka:id/register"))?.click()
        Thread.sleep(1500)
        //使用资源id定位到元素输入hello
        device?.findObject(By.res("me.peace.ka:id/edit"))?.text = "Hello"
        Thread.sleep(1500)
        device?.findObject(By.res("me.peace.ka:id/send"))?.click()
        Thread.sleep(1500)
        device?.findObject(By.res("me.peace.ka:id/unregister"))?.click()
        Thread.sleep(1500)
        //组合使用资源id、文本text定位到元素
        device?.findObject(By.res("me.peace.ka:id/clear"))?.findObject(By.text("CLEAR"))?.click()
        Thread.sleep(1500)
        //按Home键
        device?.pressHome()

    }

    @After
    fun tearDown(){
        device = null
        instrumentation = null
    }
}
```



