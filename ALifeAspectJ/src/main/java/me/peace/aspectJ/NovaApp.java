package me.peace.aspectJ;

public class NovaApp {
    private static final String TAG = NovaApp.class.getSimpleName();

    public void show(){
        Version version = new Version();
        version.show();
    }
}

class Version{
    private static final String TAG = Version.class.getSimpleName();

    public void show(){
        LogUtils.i(TAG, "show() called");
    }
}
