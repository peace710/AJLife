package me.peace.aspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

public class AspectJJoinPoint {
    public static void main(String[] args) {
        AppJoinPoint joinPoint = new AppJoinPoint();
        joinPoint.printAppInfo();
    }
}

@Aspect
class JoinPointAspect{
    private static final String TAG = JoinPointAspect.class.getSimpleName();


    @Around("execution(public * me.peace.aspectJ.AppJoinPoint.printAppInfo(..))")
    public void aroundJoinPointMethod(ProceedingJoinPoint joinPoint){
        LogUtils.i(TAG, "aroundJoinPointMethod() start called with: joinPoint = [" + joinPoint +
            "]");

        try {
            //ProceedingJoinPoint.proceed用于执行本该被替换方法的内容
            //这样就可以保留原方法的内容
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LogUtils.i(TAG, "aroundJoinPointMethod() end called with: joinPoint = [" + joinPoint +
            "]");
    }
}
