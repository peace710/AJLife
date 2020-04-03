package me.peace.hook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookUtils {
    public static Object getObjectWithMethod(Class clazz,Object obj,String name,Object... args){
        try {
            Method method = clazz.getDeclaredMethod(name);
            method.setAccessible(true);
            return method.invoke(obj,args);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Field getField(Class clazz,String name){
        try {
            Field field = clazz.getDeclaredField(name);
            return field;
        }catch (NoSuchFieldException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void setField(Object obj,Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
