package me.peace.aspectJ.app;

import me.peace.aspectJ.LogUtils;

public class AndroidApp {
    private static final String TAG = AndroidApp.class.getSimpleName();

    public AndroidApp() {
        LogUtils.i(TAG, "AndroidApp() called");
    }
}
