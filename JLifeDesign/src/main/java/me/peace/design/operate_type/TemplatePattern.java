package me.peace.design.operate_type;

import me.peace.design.LogUtils;

/**
 * 在模板模式（Template Pattern）中，一个抽象类公开定义了执行它的方法的方式/模板。
 * 它的子类可以按需要重写方法实现，但调用将以抽象类中定义的方式进行。这种类型的设计模式属于行为型模式。
 */
public class TemplatePattern {
    public static void main(String[] args) {
        OS os = new WindowOS();
        os.start();

        os = new AndroidOS();
        os.start();
    }

    static abstract class OS{
        abstract void init();
        abstract void loadDrivers();
        abstract void showDesktop();

        public void start(){
            init();
            loadDrivers();
            showDesktop();
        }
    }

    static class WindowOS extends OS{
        private static final String TAG = WindowOS.class.getSimpleName();

        @Override
        void init() {
            LogUtils.i(TAG,"init");
        }

        @Override
        void loadDrivers() {
            LogUtils.i(TAG,"loadDrivers");
        }

        @Override
        void showDesktop() {
            LogUtils.i(TAG,"showDesktop");
        }
    }

    static class AndroidOS extends OS{
        private static final String TAG = AndroidOS.class.getSimpleName();

        @Override
        void init() {
            LogUtils.i(TAG,"init");
        }

        @Override
        void loadDrivers() {
            LogUtils.i(TAG,"loadDrivers");
        }

        @Override
        void showDesktop() {
            LogUtils.i(TAG,"showDesktop");
        }
    }
}
