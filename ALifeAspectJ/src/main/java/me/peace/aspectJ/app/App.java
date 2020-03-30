package me.peace.aspectJ.app;

import me.peace.aspectJ.LogUtils;

public class App {
    private static final String TAG = App.class.getSimpleName();

    static {
        LogUtils.i(TAG, "static initializer() called");
    }

    public App() {
        LogUtils.i(TAG, "App() called");
    }

    public void callLogin(String name, String pwd){
        LogUtils.i(TAG, "callLogin() called with: name = [" + name + "], pwd = [" + pwd + "]");
    }

    public void callAround(){
        LogUtils.i(TAG, "callAround() called");
    }

    public void executionLogin(String name,String pwd){
        LogUtils.i(TAG, "executionLogin() called with: name = [" + name + "], pwd = [" + pwd + "]");
    }

    public void executionAround(){
        LogUtils.i(TAG, "executionAround() called");
    }

}
