package me.peace.design;

public class SingletonPattern {
    public static void main(String[] args) {
        Singleton1.getInstance().print();
        Singleton2.getInstance().print();
        Singleton3.getInstance().print();
        Singleton4.getInstance().print();
        Singleton5.getInstance().print();
        Singleton6.instance.print();
    }
}


/**
 * 饿汉式单例
 * <p/>
 * 就是直接实例化 static instance
 * 这样，就能在类加载的时候，创建 instance
 * <p/>
 * 由于在定义静态变量的时候实例化单例类，因此在类加载的时候就已经创建了单例对象
 * 当类被加载时，静态变量 instance 会被初始化，此时类的私有构造函数会被调用，单例类的唯一实例将被创建
 * <p/>
 * 饿汉式单例类在类被加载时就将自己实例化，它的优点在于无须考虑多线程访问问题，可以确保实例的唯一性；从
 * 调用速度和反应时间角度来讲，由于单例对象一开始就得以创建，因此要优于懒汉式单例。但是无论系统在运行时
 * 是否需要使用该单例对象，由于在类加载时该对象就需要创建，因此从资源利用效率角度来讲，饿汉式单例不及懒
 * 汉式单例，而且在系统加载时由于需要创建饿汉式单例对象，加载时间可能会比较长
 *
 */
class Singleton1{
    private static final String TAG = Singleton1.class.getSimpleName();
    private static Singleton1 instance = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return instance;
    }

    public void print(){
        LogUtils.i(TAG,"饿汉式");
    }
}


/**
 * 懒汉式单例
 * <p/>
 * 懒汉式单例在第一次调用 getInstance() 方法时实例化，在类加载时并不自行实例化
 * 这种技术又称为延迟加载 (Lazy Load) 技术，即需要的时候再加载实例，为了避免多
 * 个线程同时调用 getInstance() 方法
 * <p/>
 * 懒汉式单例类在第一次使用时创建，无须一直占用系统资源，实现了延迟加载，但是必须处
 * 理好多个线程同时访问的问题，特别是当单例类作为资源控制器，在实例化时必然涉及资源
 * 初始化，而资源初始化很有可能耗费大量时间，这意味着出现多线程同时首次引用此类的机
 * 率变得较大，需要通过双重检查锁定等机制进行控制，这将导致系统性能受到一定影响
 *
 */
class Singleton2{
    private static final String TAG = Singleton2.class.getSimpleName();
    private static Singleton2 instance;

    private Singleton2(){

    }

    public static Singleton2 getInstance(){
        if (null == instance){
            instance = new Singleton2();
        }
        return instance;
    }

    public void print(){
        LogUtils.i(TAG,"懒汉式");
    }
}


class Singleton3{
    private static final String TAG = Singleton3.class.getSimpleName();
    private static Singleton3 instance;

    private Singleton3(){

    }

    public static Singleton3 getInstance(){
        // 锁 class
        synchronized (Singleton3.class) {
            if (null == instance) {
                instance = new Singleton3();
            }
        }
        return  instance;
    }


    public void print(){
        LogUtils.i(TAG,"懒汉式同步锁");
    }
}


/**
 * 懒汉式单例
 * 双重检查锁定
 * <p/>
 * 懒汉式单例在第一次调用 getInstance() 方法时实例化，在类加载时并不自行实例化
 * 这种技术又称为延迟加载 (Lazy Load) 技术，即需要的时候再加载实例，为了避免多
 * 个线程同时调用 getInstance() 方法
 * <p/>
 * 懒汉式单例类在第一次使用时创建，无须一直占用系统资源，实现了延迟加载，但是必须处
 * 理好多个线程同时访问的问题，特别是当单例类作为资源控制器，在实例化时必然涉及资源
 * 初始化，而资源初始化很有可能耗费大量时间，这意味着出现多线程同时首次引用此类的机
 * 率变得较大，需要通过双重检查锁定等机制进行控制，这将导致系统性能受到一定影响
 *
 */
class Singleton4{
    private static final String TAG = Singleton4.class.getSimpleName();
    private static volatile Singleton4 instance;

    private Singleton4(){

    }

    public static Singleton4 getInstance(){
        // 减少不必要的同步，volatile 能拿到最新的（第一检查）
        if (null == instance) {
            // 锁 class
            synchronized (Singleton4.class) {
                // 单个线程 初始化（第二检查）
                if (null == instance){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

    public void print(){
        LogUtils.i(TAG,"双重校验锁");
    }
}


/**
 * IoDH 单例
 * <p/>
 * 1. 和饿汉的区别，在于 IoDHSingleton 自身被加载的时候没实例化单例
 * 2. 和懒汉的区别，在于 不需要锁。控制一个内部类，在 getInstance 的时候让内部类加载，实例化单例
 * -  由 Java 虚拟机来保证其线程安全性，确保该成员变量只能初始化一次
 * <p/>
 * 通过使用 IoDH，既可以实现延迟加载，又可以保证线程安全，不影响系统性能，不失为一种最好的 Java
 * 语言单例模式实现方式（ 其缺点是与编程语言本身的特性相关，很多面向对象语言不支持 IoDH ）
 *
 */
class Singleton5{
    private static final String TAG = Singleton5.class.getSimpleName();
    private Singleton5(){

    }

    /**
     * 控制一个内部类，在 getInstance 的时候让内部类加载，实例化单例
     * 由 Java 虚拟机来保证其线程安全性，确保该成员变量只能初始化一次
     */
    private static class SingletonHolder{
        private static  Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }

    public void print(){
        LogUtils.i(TAG,"静态内部类方式");
    }
}

enum Singleton6{
    instance;

    private static final String TAG = Singleton6.class.getSimpleName();

    public void print(){
        LogUtils.i(TAG,"枚举方式");
    }
}
