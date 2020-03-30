package me.peace.aspectJ;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJAfterThrowing {
    private static final String TAG = AspectJAfterThrowing.class.getSimpleName();
    public static void main(String[] args) {
        RedmiApp app = new RedmiApp();
        app.openFile();
        app.div();
    }

}

@Aspect
class AfterThrowingAspect{
    private static final String TAG = AfterThrowingAspect.class.getSimpleName();

    @Pointcut("execution(* me.peace.aspectJ.RedmiApp.*(..))")
    public void afterThrow(){

    }

    // @AfterThrowing 用于拦截系统未捕获到的异常
    @AfterThrowing(pointcut = "afterThrow()",throwing = "e")
    public void afterThrowException(Throwable e){
        LogUtils.i(TAG, "afterThrowException() called with: e = [" + e + "]");
    }
}
