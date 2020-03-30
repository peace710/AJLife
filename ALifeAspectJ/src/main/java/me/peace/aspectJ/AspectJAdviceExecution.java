package me.peace.aspectJ;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class AspectJAdviceExecution {
    public static void main(String[] args) {
        App app = new App();
    }

}

@Aspect
class AdviceExecutionAspect{

    private static final String TAG = AdviceExecutionAspect.class.getSimpleName();

    //within,表示包含某些类的JPoint，可以使用通配符
    @Pointcut("within(me.peace.aspectJ.AdviceExecutionAspect)")
    public void withinJPoint(){

    }


    //可使用&&、||、!组合生成织入JPoint类条件
    //此处是指不包AdviceExecutionAspect类的其他JPoint
    //使用adviceexecution,需要排除否则会产生重复递归造成stackoverflow的错误
    @Pointcut("adviceexecution() && !withinJPoint()")
    public void adviceExecutionPoint(){

    }


    //在执行其他JPoint方法之前，执行该段代码
    @Before("adviceExecutionPoint()")
    public void beforeAdviceExecution(){
        LogUtils.i(TAG, "beforeAdviceExecution() called");
    }

    //在执行其他JPoint方法之后，执行该段代码
    @After("adviceExecutionPoint()")
    public void afterAdviceExecution(){
        LogUtils.i(TAG, "afterAdviceExecution() called");
    }

    //替换其他JPoint方法，直接执行该段代码
    @Around("adviceExecutionPoint()")
    public void arroundAdviceExecution(){
        LogUtils.i(TAG, "arroundAdviceExecution() called");
    }
}
