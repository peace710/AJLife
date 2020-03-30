package me.peace.aspectJ.app;

import me.peace.aspectJ.LogUtils;

public class XiaomiApp extends IosApp {
    private static final String TAG = XiaomiApp.class.getSimpleName();

    public XiaomiApp() {
        LogUtils.i(TAG, "XiaomiApp() called");
    }
}
