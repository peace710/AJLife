package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJInitialization {
    private static final String TAG = AspectJInitialization.class.getSimpleName();

    public static void main(String[] args) {
        HuaweiApp app = new HuaweiApp();
    }
}

@Aspect
class InitializationAspect{
    private static final String TAG = InitializationAspect.class.getSimpleName();

    //调用Constructor super方法之后的代码块
    @Pointcut("initialization(me.peace.aspectJ.HuaweiApp.new(..))")
    public void initializationConstructor(){
    }

    @Before("initializationConstructor()")
    public void beforeInitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeInitializationConstructor() called");
    }

    @After("initializationConstructor()")
    public void afterInitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterInitializationConstructor() called");
    }

    //@Around不生效
    @Around("initializationConstructor()")
    public void aroundInitializationConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundInitializationConstructor() called");
    }
}

