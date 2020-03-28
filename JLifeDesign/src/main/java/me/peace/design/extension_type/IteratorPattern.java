package me.peace.design.extension_type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.peace.design.LogUtils;

/**
 * 迭代器模式
 *
 * 迭代器模式(Iterator Pattern)：提供一种方法来访问聚合对象，而不用暴露这个对象的内部表示，其别名为游标(Cursor)
 * 迭代器模式是一种对象行为型模式
 *
 * 主要优点
 * 迭代器模式的主要优点如下：
 * (1) 它支持以不同的方式遍历一个聚合对象，在同一个聚合对象上可以定义多种遍历方式。在迭代器模式中只需要用一个不同的迭代器来替换原有迭代器即可改变遍历算法，我们也可以自己定义迭代器的子类以支持新的遍历方式
 * (2) 迭代器简化了聚合类。由于引入了迭代器，在原有的聚合对象中不需要再自行提供数据遍历等方法，这样可以简化聚合类的设计
 * (3) 在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便，无须修改原有代码，满足“开闭原则”的要求
 * 主要缺点
 * 迭代器模式的主要缺点如下：
 * (1) 由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性
 * (2) 抽象迭代器的设计难度较大，需要充分考虑到系统将来的扩展，例如 JDK 内置迭代器 Iterator 就无法实现逆向遍历，如果需要实现逆向遍历，只能通过其子类 ListIterator 等来实现，而 ListIterator 迭代器无法用于操作 Set 类型的聚合对象。在自定义迭代器时，创建一个考虑全面的抽象迭代器并不是件很容易的事情
 * 适用场景
 * 在以下情况下可以考虑使用迭代器模式：
 * (1) 访问一个聚合对象的内容而无须暴露它的内部表示。将聚合对象的访问与内部数据的存储分离，使得访问聚合对象时无须了解其内部实现细节
 * (2) 需要为一个聚合对象提供多种遍历方式
 * (3) 为遍历不同的聚合结构提供一个统一的接口，在该接口的实现类中为不同的聚合结构提供不同的遍历方式，而客户端可以一致性地操作该接口
 *
 */
public class IteratorPattern {
    private static final String TAG = IteratorPattern.class.getSimpleName();

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1.NBA");
        list.add("2.FIBA");
        list.add("3.FIFA");
        list.add("4.CBA");
        list.add("5.NFL");
        list.add("6.NHL");

        StringIteratorImpl iterator = new StringIteratorImpl(list);
        LogUtils.i(TAG,"isFirst = "  + iterator.first());
        LogUtils.i(TAG,"isLast = "  + iterator.last());
        while (iterator.next()){
            LogUtils.i(TAG,iterator.iterator());
        }

        LogUtils.i(TAG,"isFirst = "  + iterator.first());
        LogUtils.i(TAG,"isLast = "  + iterator.last());
        while (iterator.previous()){
            LogUtils.i(TAG,iterator.iterator());
        }

        LogUtils.i(TAG,"isFirst = "  + iterator.first());
        LogUtils.i(TAG,"isLast = "  + iterator.last());
        while (iterator.next()){
            LogUtils.i(TAG,iterator.iterator());
        }
    }

    interface Iterator<T>{
        boolean first();
        boolean last();

        boolean next();
        boolean previous();

        T iterator();
    }

    static class IteratorImpl<T> implements Iterator<T>{
        List<T> list;
        int index;

        public IteratorImpl(List<T> list) {
            this.list = list;
            index = -1;
        }

        @Override
        public boolean first() {
            return index == -1;
        }

        @Override
        public boolean last() {
            if (list == null || list.isEmpty()){
                return true;
            }
            return index == list.size();
        }

        @Override
        public boolean next() {
            if (list == null || list.size() == 0) {
                return false;
            }
            if (index < list.size()){
                index++;
                return index != list.size();
            }
            return false;
        }

        @Override
        public boolean previous() {
            if (list == null || list.size() == 0) {
                return false;
            }
            if (index > -1){
                index--;
                return index != -1;
            }
            return false;
        }

        @Override
        public T iterator() {
            return (list == null || list.size() == 0) ? null : list.get(index);
        }


    }

    static class StringIteratorImpl extends IteratorImpl<String>{
        public StringIteratorImpl(List<String> list) {
            super(list);
        }
    }
}
