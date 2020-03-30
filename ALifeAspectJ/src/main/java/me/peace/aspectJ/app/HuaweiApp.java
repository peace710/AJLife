package me.peace.aspectJ.app;

import me.peace.aspectJ.LogUtils;

public class HuaweiApp extends IosApp{
    private static final String TAG = HuaweiApp.class.getSimpleName();
    public HuaweiApp() {
        LogUtils.i(TAG, "HuaweiApp() called");
    }
}
