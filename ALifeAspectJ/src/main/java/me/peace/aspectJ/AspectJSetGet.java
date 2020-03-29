package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJSetGet {
    private static final String TAG = AspectJSetGet.class.getSimpleName();

    public static void main(String[] args) {
        IosApp app = new IosApp();
        app.setId(4);
//        app.getId();
//        app.setName("ios");
//        app.getName();
    }
}

@Aspect
class SetGetAspect{
    private static final String TAG = SetGetAspect.class.getSimpleName();

    //缺省了访问权限代表任意访问权限
    //第一个*代表任意类型
    //第二个*代表任意字段
    @Pointcut("set(* me.peace.aspectJ.IosApp.*)")
    public void setField(){

    }

    @Pointcut("get(* me.peace.aspectJ.IosApp.*)")
    public void getField(){

    }

    @Before("setField()")
    public void beforeSet(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeSet() called");
    }

    @After("setField()")
    public void afterSet(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterSet() called");
    }

    @Around("setField()")
    public void aroundSet(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundSet() called");
    }

    @Before("getField()")
    public void beforeGet(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeGet() called");
    }

    @After("getField()")
    public void afterGet(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterGet() called");
    }

    @Around("getField()")
    public void aroundGet(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundGet() called");
    }
}
