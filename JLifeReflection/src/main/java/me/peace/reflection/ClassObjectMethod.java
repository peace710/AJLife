package me.peace.reflection;

public class ClassObjectMethod {
    private static final String TAG = ClassObjectMethod.class.getSimpleName();

    static void printInfo(Class clazz){
        LogUtils.i(TAG,"Class Name : " + clazz.getName()); //获取全限定的类名（即包含包名）
        LogUtils.i(TAG,"Simple Name : " + clazz.getSimpleName()); //获取不包含包名的类名
        LogUtils.i(TAG,"Canonical Name : " + clazz.getCanonicalName()); //获取全限定的类名
        LogUtils.i(TAG,"Is interface : " + clazz.isInterface()); //用于判断是否是接口
    }

    public static void main(String[] args) {
        Class clazz = null;
        try {
            //Class.forName用于创建Class引用，参数须为全限定类名，否则会报ClassNotFoundException
            clazz = Class.forName("me.peace.reflection.AndroidPhone");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        printInfo(clazz);
        //getInterfaces用于该返回Class对象所包含的接口集合
        for (Class cls : clazz.getInterfaces()){
            printInfo(cls);
        }

        //getSuperclass用于获取该Class对象的直接基类
        Class superClazz = clazz.getSuperclass();
        printInfo(superClazz);

        Object object = null;

        try {
            //newInstance方法是实现虚拟构造器的一种途径
            //通过该构造器可以创建一个Object引用，这个引用指向的是其真实对应的Class对象
            //如果想操作该对象调用真实Class对象方法，则需要进行类型转换
            //通过newInstance构造的对象，必须带有默认的构造器
            object = superClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        printInfo(object.getClass());
    }
}

interface Call{}

interface Message{}

interface Net{}

class Phone{
    public Phone() {
    }

    public Phone(int memorySize){

    }
}

class AndroidPhone extends Phone implements Call, Message,Net{
    public AndroidPhone() {
        super(64);
    }
}



