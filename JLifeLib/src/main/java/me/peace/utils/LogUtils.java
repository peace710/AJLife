package me.peace.utils;

public class LogUtils {
    private static boolean isDebug = false;

    public static void d(String tag,String msg){
        if (isDebug) {
            System.out.println(wrapTag(tag) + " -> " + wrapMsg(msg));
        }
    }

    public static void i(String tag,String msg){
        System.out.println(wrapTag(tag) + " -> " + wrapMsg(msg));
    }

    private static String wrapMsg(String msg) {
        return msg + " \u300CThreadName \u21E2 " + Thread.currentThread().getName() + "\u300D ";
    }

    private static String wrapTag(String tag) {
        return "\u300C" + tag + "\u300D";
    }
}
