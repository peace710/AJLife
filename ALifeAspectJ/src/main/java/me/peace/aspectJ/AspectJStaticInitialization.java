package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJStaticInitialization {
    private static final String TAG = AspectJStaticInitialization.class.getSimpleName();

    public static void main(String[] args) {
        App app = new App();
    }
}

@Aspect
class StaticInitializationAspect{
    private static final String TAG = StaticInitializationAspect.class.getSimpleName();

    @Pointcut("staticinitialization(me.peace.aspectJ.App)")
    public void staticInitialization(){
    }

    @Before("staticInitialization()")
    public void beforeStaticInitialization(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeStaticInitialization() called");
    }

    @After("staticInitialization()")
    public void afterStaticInitialization(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterStaticInitialization() called");
    }

    @Around("staticInitialization()")
    public void aroundStaticInitialization(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundStaticInitialization() called");
    }
}
