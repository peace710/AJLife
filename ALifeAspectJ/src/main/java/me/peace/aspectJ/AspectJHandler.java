package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJHandler {
    public static void main(String[] args) {
        App.showVersion();
    }
}


@Aspect
class HandlerAspect{
    private static final String TAG = HandlerAspect.class.getSimpleName();


    //handler处理异常，必须指定异常类与catch的异常对应
    //只能与@Before使用
    @Pointcut("handler(java.lang.NullPointerException)")
    public void handlerException(){

    }

    @Before("handlerException()")
    public void beforeHandler(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeHandler() called");
    }
}
