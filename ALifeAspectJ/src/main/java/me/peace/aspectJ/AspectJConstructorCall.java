package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import me.peace.aspectJ.app.App;

public class AspectJConstructorCall {
    private static final String TAG = AspectJConstructorCall.class.getSimpleName();

    public static void main(String[] args) {
        App app = new App();
    }
}

@Aspect
class ConstructorCallAspect{
    private static final String TAG = ConstructorCallAspect.class.getSimpleName();

    /**
     *     public static void main(String[] args) {
     *         JoinPoint var2 = Factory.makeJP(ajc$tjp_0, (Object)null, (Object)null);
     *
     *         try {
     *             ConstructorCallAspect.aspectOf().beforeCallConstructor(var2);
     *             new App();
     *         } catch (Throwable var4) {
     *             ConstructorCallAspect.aspectOf().afterCallConstructor(var2);
     *             throw var4;
     *         }
     *
     *         ConstructorCallAspect.aspectOf().afterCallConstructor(var2);
     *     }
     */

    //构造方法没有返回值
    // me.peace.aspectJ.app.App.new代表App的构造方法（.new代表为构造方法）
    @Pointcut("call(public me.peace.aspectJ.app.App.new(..))")
    public void callConstructor(){

    }


    @Before("callConstructor()")
    public void beforeCallConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeCallConstructor() called");
    }

    @After("callConstructor()")
    public void afterCallConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterCallConstructor() called");
    }


    //针对构造方法@Around不生效
    @Around("callConstructor()")
    public void aroundCallConstructor(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundCallConstructor() called");
    }

}
