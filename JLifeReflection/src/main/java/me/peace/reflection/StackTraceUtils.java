package me.peace.reflection;


public class StackTraceUtils {
    private static final String TAG = StackTraceUtils.class.getSimpleName();
    private static final String CLASS_NAME = StackTraceUtils.class.getName();
    private static final String THREAD_CLASS_NAME = Thread.class.getName();

    private static final String VM_STACK_CLASS_NAME = "dalvik.system.VMStack";
    private static final int TRACE_LIMIT = 5;

    public static void trace(Class clazz){
        trace(clazz,TRACE_LIMIT);
    }

    private static void trace(Class clazz,int limit){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements != null){
            StringBuffer sb = new StringBuffer();
            int length = 0;
            String currentClassName = clazz.getName();
            for (int i = 0 ; i < stackTraceElements.length ; i++){
                StackTraceElement element = stackTraceElements[i];
                if (element != null){
                    String className = element.getClassName();
                    if (filter(className)){
                        continue;
                    }
                    if (!currentClassName.equals(className)){
                        sb.append(className).append(".");
                    }
                    sb.append(element.getMethodName());
                    length++;
                    if (length >= limit) break;
                    sb.append(" \u21E0 ");
                }
            }
           LogUtils.i(asTag(clazz),sb.toString());
        }
    }

    private static boolean filter(String className){
        if (className.equalsIgnoreCase(CLASS_NAME) ||
            className.equalsIgnoreCase(THREAD_CLASS_NAME) ||
            className.equalsIgnoreCase(VM_STACK_CLASS_NAME)){
            return true;
        }
        return false;
    }

    private static String asTag(Class<?> cls) {
        if (cls.isAnonymousClass()) {
            return asTag(cls.getEnclosingClass());
        }
        return cls.getSimpleName();
    }
}
