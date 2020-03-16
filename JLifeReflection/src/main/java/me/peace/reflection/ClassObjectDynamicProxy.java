package me.peace.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClassObjectDynamicProxy {
    private static void play(ActionOnLine actionOnLine){
        actionOnLine.shopping();
        actionOnLine.watchTv();
        actionOnLine.chat("James");
    }

    public static void main(String[] args) {
       proxy();
    }

    private static void proxy(){
        SmartPhone phone = new SmartPhone();
        play(phone);
        ActionOnLine proxyInstance = (ActionOnLine) Proxy.newProxyInstance(
            ActionOnLine.class.getClassLoader(),new Class[]{ActionOnLine.class},
            new DynamicProxyHandler(phone)
        );
        play(proxyInstance);
    }
}

class DynamicProxyHandler implements InvocationHandler{
    private static final String TAG = DynamicProxyHandler.class.getSimpleName();

    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtils.i(TAG,"proxy class is " + proxy.getClass() + ",method is " + method +
            ", args is " + args);
        if (args != null){
            for (Object arg: args) {
                LogUtils.i(TAG,"arg is " + arg);
            }
        }
        return method.invoke(proxied,args);
    }
}

class SmartPhone implements ActionOnLine{
    private static final String TAG = SmartPhone.class.getSimpleName();

    @Override
    public void shopping() {
        LogUtils.i(TAG,"shopping with smartphone");
    }

    @Override
    public void watchTv() {
        LogUtils.i(TAG,"watchTv with smartphone");
    }

    @Override
    public void chat(String friend) {
        LogUtils.i(TAG,"chat  with " + friend);
    }
}

interface ActionOnLine{

    void shopping();

    void watchTv();

    void chat(String friend);
}
