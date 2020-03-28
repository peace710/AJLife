package me.peace.design.extension_type;

import me.peace.design.LogUtils;

/**
 * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。
 * 这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
 *
 * 这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        DayNote dayNote = new DayNote();
        DecoratorDayNote decoratorDayNote = new DecoratorDayNote(dayNote);
        decoratorDayNote.note();
    }

    interface Note{
        void note();
    }

    static class DayNote implements Note{
        private static final String TAG = DayNote.class.getSimpleName();

        @Override
        public void note() {
            LogUtils.i(TAG,"note some content");
        }
    }

    static abstract class DecoratorNote implements Note{
        private Note note;

        public DecoratorNote(Note note) {
            this.note = note;
        }

        @Override
        public void note() {
            note.note();
        }
    }

    static class DecoratorDayNote extends DecoratorNote{
        private static final String TAG = DecoratorDayNote.class.getSimpleName();

        public DecoratorDayNote(Note note) {
            super(note);
        }

        @Override
        public void note() {
            LogUtils.i(TAG,"note start");
            super.note();
            LogUtils.i(TAG,"note end");
        }
    }
}
