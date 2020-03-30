package me.peace.aspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJIfArgs {
    private static final String TAG = AspectJIfArgs.class.getSimpleName();

    public static void main(String[] args) {
        RedmiApp app = new RedmiApp();
        app.setId(-1);
        app.setId(10);
    }
}

@Aspect
class IfArgsAspect{

    private static final String TAG = IfArgsAspect.class.getSimpleName();


    //if() 方法必须为public static boolean这种类型
    //args() 则为入参的值 args括号中的变量与方法里的变更对应，且类型也需要对应
    //带有条件的织入代码
    @Pointcut("execution(* me.peace.aspectJ.RedmiApp.*(..)) && args(i) && if()")
    public static boolean ifArgs(int i, JoinPoint joinPoint){
        return i > 0;
    }

    @Before("ifArgs(i,joinPoint)")
    public void beforeIfArgs(int i,JoinPoint joinPoint){
        LogUtils.i(TAG, "beforeIfArgs() called with: i = [" + i + "], joinPoint = [" + joinPoint +
            "]");

    }
}
