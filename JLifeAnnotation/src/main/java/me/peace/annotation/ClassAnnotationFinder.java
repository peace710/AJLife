package me.peace.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassAnnotationFinder {
    private static final String TAG = ClassAnnotationFinder.class.getSimpleName();

    public static void main(String[] args) {
        ImportantUtils.find(PayUtils.class);
        findFieldAnnotation();
    }

    private static void findFieldAnnotation(){
        AppHelper helper = new AppHelper();
        Field[] fields = helper.getClass().getDeclaredFields();
        if (fields != null){
            for (Field field: fields) {
                if (field != null) {
                    helper.find(field);
                }
            }
        }
    }
}

class ImportantUtils{
    private static final String TAG = ImportantUtils.class.getSimpleName();

    public static void find(Class clazz){
        if (clazz != null){
            Method[] methods = clazz.getDeclaredMethods();
            if (methods != null && methods.length > 0){
                for (Method method : methods){
                    if (method != null){
                        //获取方法上定义的Important注解
                        Important annotation = method.getAnnotation(Important.class);
                        if (annotation != null){
                            LogUtils.i(TAG,
                                "import id = " + annotation.id() + ", description = " + annotation.description());
                        }
                    }
                }
            }
        }
    }
}

class PayUtils{
    private static final String TAG = PayUtils.class.getSimpleName();

    @Important(id = 1,description = "pay method")
    public boolean pay(String name,String pwd){
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            return false;
        }
        LogUtils.i(TAG,"pay call with name = " + name + ",pwd = " + pwd);
        return true;
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Important{
    /**
     *  注解元素
     *  类型可以为所有基本类型(int,float,boolean等）
     *  String
     *  Class
     *  enum
     *  Annotation
     *  以上类型的数组
     *
     *  default为注解元素的默认值，默认值有限制，不能用null为其值
     *
     */
    //id,description为注解元素，其类型分别为int,String,default为该两元素的默认值
    int id() default -1;
    String description() default "no description";
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Model{
    boolean isLogin() default true;
    //注解元素为枚举
    UseModel model() default UseModel.PASSWORD;
}

enum UseModel{
    PASSWORD,QRCODE,FACE;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface AppModel{
    String name() default "";
    //注解元素为Annotation
    Model model() default @Model;
    //通过该种方式更改注解的默认值
    Model registerModel() default @Model(isLogin = false);
}


class App{
    int appId = 0;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }
}

class AppHelper{
    private static final String TAG = AppHelper.class.getSimpleName();

    @AppModel(name = "weixin",model = @Model(isLogin = false,model = UseModel.PASSWORD))
    private App weixinId;
    @AppModel(name = "dingding",model = @Model(model = UseModel.FACE))
    private App dingdingId;
    @AppModel(name = "weibo",model = @Model(isLogin = false,model = UseModel.QRCODE))
    private App weiboId;


    public void find(Field field){
        if (field != null){
            AppModel annotation = field.getAnnotation(AppModel.class);
            if (annotation != null){
                String name = annotation.name();
                boolean isLogin = annotation.model().isLogin();
                boolean isRegister = annotation.registerModel().isLogin();
                UseModel model = annotation.model().model();
                LogUtils.i(TAG,
                    "name = " + name + ", isLogin = " + isLogin + ",isRegister = " + isRegister + ", model = " + model);
            }
        }
    }
}

