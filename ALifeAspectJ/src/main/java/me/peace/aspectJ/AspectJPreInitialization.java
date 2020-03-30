package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJPreInitialization {
    private static final String TAG = AspectJPreInitialization.class.getSimpleName();

    public static void main(String[] args) {
        XiaomiApp app = new XiaomiApp();
    }
}

@Aspect
class PreInitializationAspect{
    private static final String TAG = PreInitializationAspect.class.getSimpleName();

    //调用Constructor super方法之前的代码块
    @Pointcut("preinitialization(me.peace.aspectJ.XiaomiApp.new(..))")
    public void preinitializationConstructor(){
    }

    @Before("preinitializationConstructor()")
    public void beforePreinitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforePreinitializationConstructor() called");
    }

    @After("preinitializationConstructor()")
    public void afterPreinitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterPreinitializationConstructor() called");
    }


    //@Around不生效
    @Around("preinitializationConstructor()")
    public void aroundPreinitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundPreinitializationConstructor() called");
    }
}

