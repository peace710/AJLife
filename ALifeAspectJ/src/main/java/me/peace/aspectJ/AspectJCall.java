package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import me.peace.aspectJ.app.App;

public class AspectJCall {
    private static final String TAG = AspectJCall.class.getSimpleName();

    public static void main(String[] args) {
        App app = new App();
        //beforeCallLogin
        app.callLogin("android","diordna");
        //afterCallLogin
        app.callAround();
    }
}

@Aspect
class CallAspect{
    private static final String TAG = CallAspect.class.getSimpleName();


    /**
     *  如下是@Before、@After织入代码，代码会插入在调用方法的一前一后
     public static void main(String[] args) {
     App app = new App();
     String var3 = "diordna";
     String var4 = "android";
     App var5 = app;
     JoinPoint var2 = Factory.makeJP(ajc$tjp_0, (Object)null, app, var4, var3);

     try {
     CallAspect.aspectOf().beforeCallLogin(var2);
     var5.callLogin(var4, var3);
     } catch (Throwable var7) {
     CallAspect.aspectOf().afterCallLogin(var2);
     throw var7;
     }

     CallAspect.aspectOf().afterCallLogin(var2);
     }
     */

    // @Pointcut代表被切入的点
    // call是指方法被调用时
    // public是指切入的方法访问权限
    // *是通配符，代表任意返回类型，也可以指定明确的返回类型
    // me.peace.aspectJ.app.App.callLogin指定类的方法名
    // (..)代表方法可以是任意参数，可以有，也可以没有
    @Pointcut("call(public * me.peace.aspectJ.app.App.callLogin(..))")
    public void callLogin(){

    }


    // @Before表示在切入之前执行,这里是指调用方法之前
    // callLogin()引用之前的定义,即调用App类的名为callLogin的方法,且访问权限为public,返回类型任意
    // 可以省略callLogin定义,直接写成如下的方式
    // @Before("call(public * me.peace.aspectJ.app.App.callLogin(..))")
    @Before("callLogin()")
    public void beforeCallLogin(JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeCallLogin() called");
    }

    // @After表示在切入之后执行,这里是指调用方法之前
    @After("callLogin()")
    public void afterCallLogin(JoinPoint joinPoint){
        LogUtils.i(TAG, "afterCallLogin() called");
    }


    /**
     public static void main(String[] args) {
     App app = new App();
     String var3 = "diordna";
     String var4 = "android";
     App var5 = app;
     JoinPoint var2 = Factory.makeJP(ajc$tjp_0, (Object)null, app, var4, var3);

     try {
     CallAspect.aspectOf().beforeCallLogin(var2);
     var5.callLogin(var4, var3);
     } catch (Throwable var9) {
     CallAspect.aspectOf().afterCallLogin(var2);
     throw var9;
     }

     CallAspect.aspectOf().afterCallLogin(var2);
     //如下替换原来callAround的方法
     JoinPoint var8 = Factory.makeJP(ajc$tjp_1, (Object)null, app);
     callAround_aroundBody1$advice(app, var8, CallAspect.aspectOf(), var8);
     }
     */

    // @Around表示替换原来的方法,即替换了callAround方法
    @Around("call(public * me.peace.aspectJ.app.App.callAround(..))")
    public void aroundCallAround(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundCallAround() called");
    }

    /**
     * @Before
     * @After
     * @Around
     * 对同一个方法涉及到这3个注解，将会失效，切入代码不生效
     * 对同一个方法涉及到@After,@Around2个注解，只有生效@Around
     */
}
