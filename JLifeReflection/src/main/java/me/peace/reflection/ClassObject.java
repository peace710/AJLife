package me.peace.reflection;

public class ClassObject {
    private static final String TAG = ClassObject.class.getSimpleName();
    public static void main(String[] args) {
        LogUtils.i(TAG,"enter into main");

        new Android();
        LogUtils.i(TAG,"after create android object");

        try {
            Class.forName(IPhone.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LogUtils.i(TAG,"after create iphone object(Class.forName)");
        new WPhone();
        LogUtils.i(TAG,"after create wphone object");

        LogUtils.i(TAG,"leave out main");
        new Android();
    }
}

class Android{
    private static final String TAG = Android.class.getSimpleName();
    static {
        LogUtils.i(TAG,"loading android");
    }
}

class IPhone{
    private static final String TAG = WPhone.class.getSimpleName();
    static {
        LogUtils.i(TAG,"loading iphone");
    }
}

class WPhone{
    private static final String TAG = WPhone.class.getSimpleName();
    static {
        LogUtils.i(TAG,"loading wphone");
    }
}
