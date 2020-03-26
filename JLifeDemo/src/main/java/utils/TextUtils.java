package utils;

public class TextUtils {
    public static boolean isEmpty(String str){
        if (str == null || str.equalsIgnoreCase("")){
            return true;
        }
        return false;
    }
}
