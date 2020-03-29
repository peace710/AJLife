package me.peace.aspectJ;

import android.util.Log;

public class App {
    private static final String TAG = App.class.getSimpleName();

    public void callLogin(String name,String pwd){
        LogUtils.i(TAG, "callLogin() called with: name = [" + name + "], pwd = [" + pwd + "]");
    }

    public void callAround(){
        LogUtils.d(TAG, "callAround() called");
    }

}
