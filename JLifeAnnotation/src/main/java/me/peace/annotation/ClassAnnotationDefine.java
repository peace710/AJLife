package me.peace.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ClassAnnotationDefine {
    public static void main(String[] args) {

    }
}

class TestDemo{
    private static final String TAG = TestDemo.class.getSimpleName();

    public void execute(){
        LogUtils.i(TAG,"invoke class TestDemo execute method");
    }

    @Test
    public void testExecute(){
        execute();
    }
}


/***
 * @Target
 * ElementType
 * TYPE 类、接口（包括注解类）声明
 * FIELD 域声明
 * METHOD 方法声明
 * PARAMETER 参数声明
 * CONSTRUCTOR 构造器的声明
 * LOCAL_VARIABLE 局部变量的声明
 * ANNOTATION_TYPE
 * PACKAGE 包声明
 * TYPE_PARAMETER
 * TYPE_USE
 */
@Target(ElementType.METHOD) //定义注解的作用域

/***
 * @Retention
 * RetentionPolicy
 * SOURCE 源码级别，注解将被编译器丢弃
 * CLASS 类级别，注解在class文件中可用，但会被VM丢弃
 * RUNTIME VM在运行期间注解会被保留，可以通过反射获取注解信息
 */
@Retention(RetentionPolicy.RUNTIME) //定义注解的级别

@Inherited //允许子类继承父类的注解
@interface Test{

}
