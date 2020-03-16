package me.peace.reflection;

import java.util.ArrayList;
import java.util.List;

public class ClassObjectGenericity {
    private static final String TAG = ClassObjectGenericity.class.getSimpleName();

    //泛型通配符?支持匹配所有的类型
    private static void allInfo(){
        LogUtils.i(TAG,"enter into allInfo");
        Class<?> clazz = OS.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = Linux.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = Windows.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = AndroidOS.class;
        LogUtils.i(TAG,clazz.toString());

        LogUtils.i(TAG,"leave out allInfo");
    }

    //<? extends >泛型的下限，只能是自身和子类
    private static void extendsInfo(){
        LogUtils.i(TAG,"enter into extendsInfo");
        Class<? extends Linux> clazz = Linux.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = AndroidOS.class;
        LogUtils.i(TAG,clazz.toString());

        LogUtils.i(TAG,"leave out extendsInfo");
    }

    //<? super >泛型的上限，只能是自身和基类
    private static void superInfo(){
        LogUtils.i(TAG,"enter into superInfo");
        Class<? super AndroidOS> clazz = OS.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = Linux.class;
        LogUtils.i(TAG,clazz.toString());

        clazz = AndroidOS.class;
        LogUtils.i(TAG,clazz.toString());

        LogUtils.i(TAG,"leave out superInfo");
    }


    private static void listInfo(){
        LogUtils.i(TAG,"enter into listInfo");
        OSList<AndroidOS> os = new OSList<>(AndroidOS.class);
        LogUtils.i(TAG,os.create(6).toString());

        LogUtils.i(TAG,"leave out listInfo");
    }


    private static void castInfo(){
        LogUtils.i(TAG,"enter into castInfo");
        OS os = new Windows();
        Class<Windows> clazz = Windows.class;
        //cast方法可用于类型转换
        Windows windows = clazz.cast(os);
        LogUtils.i(TAG,windows.toString());

        os = new Linux();
        //也可用此方法类型转换
        Linux linux = (Linux)os;
        LogUtils.i(TAG,linux.toString());

        os = new Linux();
        //使用instanceof关键字校验类型的正确性，否则会报ClassCastException
        if (os instanceof AndroidOS) {
            AndroidOS android = (AndroidOS) os;
            LogUtils.i(TAG, android.toString());
        }else{
            LogUtils.i(TAG,"class not match");
        }

        LogUtils.i(TAG,"leave out castInfo");
    }

    public static void main(String[] args) {
        allInfo();
        extendsInfo();
        superInfo();
        listInfo();
        castInfo();
    }
}


class OS{
    static long counter = 1;
    final long id = counter++;

    @Override
    public String toString() {
        return "OS" + id;
    }
}
class Linux extends OS{
    @Override
    public String toString() {
        return "Linux" + id;
    }
}

class Windows extends OS{
    @Override
    public String toString() {
        return "Windows" + id;
    }
}

class AndroidOS extends Linux{
    @Override
    public String toString() {
        return "AndroidOS" + id;
    }
}

class OSList<T>{
    Class<T> type;

    public OSList(Class<T> type) {
        this.type = type;
    }

    //当Class类型明确时，newInstance创建出来的对象就是指定的Class类，而非通用的Object
    List<T> create(int size){
        List<T> list = new ArrayList<>();
        try {
            for (int i = 0 ; i < size ;i++){
                list.add(type.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }
}

