package me.peace.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * 代理模式
 *
 * 就和 JDK 自带的动态代理（InvocationHandler）一样
 * 可以做任何事情自己调用方法，或者在方法前后做很多事情
 *
 * 代理模式是一种对象结构型模式。在代理模式中引入了一个新的代理对象，代理对象在客户端对象和目标对象之间起到中介的作
 * 用，它去掉客户不能看到的内容和服务或者增添客户需要的额外的新服务
 *
 * 代理模式的共同优点如下：
 * (1) 能够协调调用者和被调用者，在一定程度上降低了系统的耦合度。
 * (2) 客户端可以针对抽象主题角色进行编程，增加和更换代理类无须修改源代码，符合开闭原则，系统具有较好的灵活性和可扩展性。
 * 代理模式的主要缺点如下：
 * (1) 由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢，例如保护代理。
 * (2) 实现代理模式需要额外的工作，而且有些代理模式的实现过程较为复杂，例如远程代理。
 *
 */
public class ProxyPattern {
    public static void main(String[] args) {
        //静态代理
        App app = new App("QQ");
        AppManager manager = new AppManager(app);
        manager.run();

        //动态代理
        WebFunction proxyBrowser = ProxyPool.create(new Browser());
        proxyBrowser.request("http://www.baidu.com");
        proxyBrowser.request("http://www.taobao.com");
        proxyBrowser.request("http://www.google.com");
    }


    interface Function {
        void run();
    }

    static class App implements Function {
        private static final String TAG = App.class.getSimpleName();
        private String name;

        public App(String name){
            this.name = name;
        }

        @Override
        public void run() {
            LogUtils.i(TAG,name + " is  running!");
        }
    }

    static class AppManager implements Function {
        private static final String TAG = AppManager.class.getSimpleName();
        private App delegate;

        public AppManager(App delegate){
            this.delegate = delegate;
        }

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            LogUtils.i(TAG,"App starts at " + new Date(startTime));
            delegate.run();
        }
    }

    interface WebFunction {
        void request(String url);
    }

    static class Browser implements WebFunction{
        private static final String TAG = Browser.class.getSimpleName();

        @Override
        public void request(String url){
            LogUtils.i(TAG,url);
        }
    }

    static class ProxyInvocationHandler implements InvocationHandler {
        private static final String TAG = ProxyInvocationHandler.class.getSimpleName();

        private Object delegate;

        public ProxyInvocationHandler(Object delegate) {
            this.delegate = delegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String url = args[0].toString();
            LogUtils.i(TAG,"Check the url:" + url);
            if (!url.toString().contains("taobao")) {
                return method.invoke(delegate, args);
            }
            LogUtils.i(TAG,"Mo permission to request the website!");
            return null;
        }
    }

    static class ProxyPool {
        private ProxyPool(){
            throw new IllegalStateException();
        }

        static <T> T create(Object obj){
            return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new ProxyInvocationHandler(obj));
        }
    }

}
