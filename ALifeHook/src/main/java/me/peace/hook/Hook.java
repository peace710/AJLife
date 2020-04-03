package me.peace.hook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Hook<T> {

    public static <T> T create(Object object){
        return create(object,null);
    }

    public static <T> T create(Object object,OnInvokeListener listener){
        if (object == null){
            throw new NullPointerException("delegate object can not be null");
        }
        return (T)Proxy.newProxyInstance(object.getClass().getClassLoader(),
            object.getClass().getInterfaces(),new HookInvocationHandler(object,listener));
    }

    static class HookInvocationHandler implements InvocationHandler{

        private Object delegate;
        private OnInvokeListener listener;


        public HookInvocationHandler(Object delegate, OnInvokeListener listener) {
            this.delegate = delegate;
            this.listener = listener;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (listener != null){
                listener.beforeInvoke(delegate,proxy,method,args);
            }
            Object result = method.invoke(delegate,args);
            if (listener != null){
                listener.afterInvoke(delegate,proxy,method,args,result);
            }
            return result;
        }
    }

    public static interface OnInvokeListener{
        public void beforeInvoke(Object delegate,Object proxy, Method method, Object[] args);

        public void afterInvoke(Object delegate,Object proxy, Method method, Object[] args,
                                Object result);
    }
}
