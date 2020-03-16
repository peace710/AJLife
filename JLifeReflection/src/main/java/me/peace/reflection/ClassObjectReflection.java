package me.peace.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassObjectReflection {
    private static final String TAG = ClassObjectReflection.class.getSimpleName();

    public static void main(String[] args) {
        reflectAllInfo();
        reflectConstructorNewInstance();
        reflectPrivateConstructorNewInstance();
        reflectPrivateField();
        reflectPrivateMethod();
        reflectMethod();
        reflectStaticMethod();
    }

    static void reflectAllInfo(){
        try {
            //Class.forName获取类对象
            Class clazz = Class.forName(View.class.getName());
            //获取该类的构造器(私有构造器无法获取)
            Constructor[] constructors = clazz.getConstructors();
            //获取该类的方法(私有方法无法获取)
            Method[] methods = clazz.getMethods();
            //获取该类的字段(私有字段无法获取)
            Field[] fields = clazz.getFields();

            for (Constructor constructor : constructors){
                LogUtils.i(TAG,"Constructor:" + constructor.toString());
            }

            for (Method method : methods){
                LogUtils.i(TAG,"Method:" + method.toString());
            }

            for (Field field : fields){
                LogUtils.i(TAG,"Field:" + field.toString());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void reflectConstructorNewInstance(){
        try {
            Class clazz = Class.forName(View.class.getName());
            //获取该Class的构造器
            Constructor constructor = clazz.getConstructor(Integer.TYPE,Integer.TYPE);
            //通过构造器创建实例
            View v = (View) constructor.newInstance(100,100);
            v.printInfo();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    //反射私有的构造器
    static void reflectPrivateConstructorNewInstance(){
        try {
            Class clazz = Class.forName(View.class.getName());
            //获取定义的构造方法
            Constructor constructor = clazz.getDeclaredConstructor();
            //设置该构造器是可访问的
            constructor.setAccessible(true);
            View v = (View) constructor.newInstance();
            v.printInfo();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    static void reflectPrivateField(){
        try {
            Class clazz = Class.forName(View.class.getName());
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            View v = (View) constructor.newInstance();
            Field field = clazz.getDeclaredField("color");
            field.setAccessible(true);
            field.set(v,"red");
            v.printInfo();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    static void reflectPrivateMethod(){
        try {
            Class clazz = Class.forName(View.class.getName());
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            View v = (View) constructor.newInstance();
            Method method = clazz.getDeclaredMethod("loadSize");
            method.setAccessible(true);
            method.invoke(v);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    static void reflectMethod(){
        try {
            Class clazz = Class.forName(View.class.getName());
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            View v = (View) constructor.newInstance();
            Method method = clazz.getDeclaredMethod("drawText",String.class);
            method.invoke(v,"sample");
            method = clazz.getDeclaredMethod("drawText",String.class,Integer.TYPE);
            method.invoke(v,"sample",72);
            method = clazz.getDeclaredMethod("getText");
            String text = (String)method.invoke(v);
            LogUtils.i(TAG,"reflectMethod text = " + text);
            method = clazz.getDeclaredMethod("getText",String.class);
            text = (String)method.invoke(v,"peace");
            LogUtils.i(TAG,"reflectMethod text = " + text);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }

    static void reflectStaticMethod(){
        try {
            Class clazz = Class.forName(View.class.getName());
            Method method = clazz.getDeclaredMethod("showType");
            method.invoke(null);
            method = clazz.getDeclaredMethod("getDescription");
            String text = (String)method.invoke(null);
            LogUtils.i(TAG,"reflectStaticMethod text = " + text);
            method = clazz.getDeclaredMethod("showDescription",String.class);
            method.invoke(null,"peace");
            method = clazz.getDeclaredMethod("showDescription");
            method.setAccessible(true);
            method.invoke(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
    }
}

class View{
    private static final String TAG = ClassObjectMethod.class.getSimpleName();

    private int width;
    private int height;
    private String color;
    private String text;

    private View() {
        this(50,50);
    }

    public View(int width, int height) {
       this(width,height,"black");
    }

    public View(int width, int height, String color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void printInfo(){
        LogUtils.i(TAG,"width = " + width + ",height = " + height + ",color = " + color);
    }

    private void loadSize(){
        LogUtils.i(TAG,"loadSize width = " + width + ",height = " + height);
    }

    public void drawText(String text){
        this.text = text;
        LogUtils.i(TAG,"drawText text = " + text);
    }

    public void drawText(String text,int textSize){
        this.text = text;
        LogUtils.i(TAG,"drawText text = " + text + ",textSize = " + textSize);
    }

    public String getText(){
        LogUtils.i(TAG,"getText text = " + text);
        return text;
    }

    public String getText(String author){
        LogUtils.i(TAG,"getText text = " + text + "[" + author +"]");
        return text + "[" + author +"]";
    }

    public static void showType(){
        LogUtils.i(TAG,"type is " + View.class.getTypeName());
    }

    public static String getDescription(){
        LogUtils.i(TAG,"getDescription");
        return "this is a button";
    }

    public static void showDescription(String author){
        LogUtils.i(TAG,"showDescription " + "[" + author + "]");
    }

    private static void showDescription(){
        LogUtils.i(TAG,"showDescription");
    }
}
