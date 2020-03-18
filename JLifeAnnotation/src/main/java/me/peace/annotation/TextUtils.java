package me.peace.annotation;

public class TextUtils {
    public static boolean isEmpty(String str){
        if (str == null || str.length() == 0){
            return true;
        }
        return false;
    }
}
