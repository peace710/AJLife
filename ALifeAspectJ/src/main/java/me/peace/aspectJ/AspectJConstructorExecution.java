package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJConstructorExecution {
    private static final String TAG = AspectJConstructorExecution.class.getSimpleName();

    public static void main(String[] args) {
        AndroidApp app = new AndroidApp();
    }
}

@Aspect
class ConstructorExecutionAspect{
    private static final String TAG = ConstructorExecutionAspect.class.getSimpleName();

    /**
     *     public AndroidApp() {
     *         JoinPoint var1 = Factory.makeJP(ajc$tjp_0, this, this);
     *
     *         try {
     *             ConstructorExecutionAspect.aspectOf().beforeExecutionConstructor(var1);
     *             LogUtils.i(TAG, "AndroidApp() called");
     *         } catch (Throwable var3) {
     *             ConstructorExecutionAspect.aspectOf().afterExecutionConstructor(var1);
     *             throw var3;
     *         }
     *
     *         ConstructorExecutionAspect.aspectOf().afterExecutionConstructor(var1);
     *     }
     */

    @Pointcut("execution(public me.peace.aspectJ.AndroidApp.new(..))")
    public void executionConstructor(){

    }

    @Before("executionConstructor()")
    public void beforeExecutionConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeExecutionConstructor() called");
    }

    @After("executionConstructor()")
    public void afterExecutionConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterExecutionConstructor() called");
    }


    /**
     *   public AndroidApp() {
     *         JoinPoint var1 = Factory.makeJP(ajc$tjp_0, ajc$this, ajc$this);
     *         init$_aroundBody1$advice(ajc$this, var1, ConstructorExecutionAspect.aspectOf(), var1);
     *     }
     */
    @Around("executionConstructor()")
    public void aroundExecutionConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundExecutionConstructor() called");
    }

}
