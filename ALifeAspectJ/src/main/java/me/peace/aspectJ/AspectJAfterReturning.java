package me.peace.aspectJ;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJAfterReturning {
    private static final String TAG = AspectJAfterReturning.class.getSimpleName();
    public static void main(String[] args) {
        RedmiApp app = new RedmiApp();
        LogUtils.i(TAG, "main() called with: id = [" + app.getId() + "]");
        LogUtils.i(TAG, "main() called with: name = [" + app.getName() + "]");
    }

}

@Aspect
class AfterReturningAspect{
    private static final String TAG = AfterReturningAspect.class.getSimpleName();

    @Pointcut("execution(* me.peace.aspectJ.RedmiApp.*(..))")
    public void afterRet(){

    }

    // @AfterReturning 用于拦截在执行return代码，获取返回值信息
    // pointcut为织入代码的方法
    // returning为下方方法内的参数变量名，名称要对应，参数类型与返回值类型对应
    @AfterReturning(pointcut = "afterRet()",returning = "ret")
    public void afterRetValue(int ret){
        LogUtils.i(TAG, "afterRetValue() called with: ret = [" + ret + "]");
    }

    @AfterReturning(pointcut = "afterRet()",returning = "ret")
    public void afterRetStrValue(String ret){
        LogUtils.i(TAG, "afterRetStrValue() called with: ret = [" + ret + "]");
    }
}
