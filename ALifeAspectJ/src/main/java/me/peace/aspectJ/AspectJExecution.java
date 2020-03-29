package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJExecution {
    private static final String TAG = AspectJExecution.class.getSimpleName();

    public static void main(String[] args) {
        App app = new App();
        app.executionLogin("iphone","enohpi");
        app.executionAround();
    }
}

@Aspect
class ExecutionAspect{
    private static final String TAG = ExecutionAspect.class.getSimpleName();

    /**
     *     public void executionLogin(String name, String pwd) {
     *         try {
     *             //@Before织入的代码
     *             ExecutionAspect.aspectOf().beforeExecutionLogin();
     *             LogUtils.i(TAG, "executionLogin() called with: name = [" + name + "], pwd = [" + pwd + "]");
     *         } catch (Throwable var4) {
     *             ExecutionAspect.aspectOf().afterExecutionLogin();
     *             throw var4;
     *         }
     *         //@After织入的代码
     *         ExecutionAspect.aspectOf().afterExecutionLogin();
     *     }
     */


    //execution表求执行方法内部
    @Pointcut("execution(* me.peace.aspectJ.App.executionLogin(..))")
    public void executionLogin(){
        LogUtils.i(TAG, "executionLogin() called");
    }

    @Before("executionLogin()")
    public void beforeExecutionLogin(){
        LogUtils.i(TAG, "beforeExecutionLogin() called");
    }

    @After("executionLogin()")
    public void afterExecutionLogin(){
        LogUtils.i(TAG, "beforeExecutionLogin() called");
    }

    /**
     *     在方法内替换织入的代码
     *     public void executionAround() {
     *         JoinPoint var1 = Factory.makeJP(ajc$tjp_0, this, this);
     *         executionAround_aroundBody1$advice(this, var1, ExecutionAspect.aspectOf(), var1);
     *     }
     */
    @Around("execution(public * me.peace.aspectJ.App.executionAround(..))")
    public void aroundExecutionAround(JoinPoint joinPoint){
        LogUtils.i(TAG, "aroundExecutionAround() called");
    }
}
