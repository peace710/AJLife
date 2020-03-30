package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

public class AspectJThisTarget {
    public static void main(String[] args) {
        NovaApp app = new NovaApp();
        app.show();
    }
}

@Aspect
class ThisTargetAspect{
    private static final String TAG = ThisTargetAspect.class.getSimpleName();


    @After("call(* me.peace.aspectJ.Version.*(..))")
    public void aroundShow(JoinPoint joinPoint){
        //持有切入点的引用this
        LogUtils.i(TAG, "aroundShow() called with: this = [" + joinPoint.getThis() + "]");
        //切入点自身
        LogUtils.i(TAG, "aroundShow() called with: this = [" + joinPoint.getTarget() +
            "]");
    }

    @After("call(* me.peace.aspectJ.Version.*(..)) && this(me.peace.aspectJ.NovaApp) && target(me" +
        ".peace.aspectJ.Version)")
    public void show(JoinPoint joinPoint){
        LogUtils.i(TAG, "show() called with: this = [" + joinPoint + "]");
    }
}
