package me.peace.aspectJ;

public class AppJoinPoint {
    private static final String TAG = AppJoinPoint.class.getSimpleName();

    public void printAppInfo(){
        LogUtils.i(TAG, "printAppInfo() called");
    }
}
