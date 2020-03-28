package me.peace.design.responsibility_type;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import me.peace.design.LogUtils;

/**
 * 深克隆原型模式
 *
 * 浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制
 *
 * 深克隆可以完成 对象内的引用 也 clone 一份
 * 在 Java 语言中，如果需要实现深克隆，可以通过序列化 ( Serialization ) 等方式来实现。
 * 序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个拷贝，而原对象仍然存在
 * 于内存中。通过序列化实现的拷贝不仅可以复制对象本身，而且可以复制其引用的成员对象，因
 * 此通过序列化将对象写到一个流中，再从流里将其读出来，可以实现深克隆。需要注意的是能够
 * 实现序列化的对象其类必须实现 Serializable 接口，否则无法实现序列化操作。
 *
 */
public class DeepClonePrototypePattern {
    private static final String TAG = DeepClonePrototypePattern.class.getSimpleName();

    public static void main(String[] args) {
        Room room = new Room();
        Sofa sofa = new Sofa();
        room.setSofa(sofa);

        Room cloneRoom = (Room)room.clone();
        LogUtils.i(TAG,"room == cloneRoom :" + (room == cloneRoom));
        LogUtils.i(TAG,"room.sofa == cloneRoom.sofa :" + (room.getSofa() == cloneRoom.getSofa()));

        room.getSofa().setId(6);
        LogUtils.i(TAG,"room.sofa.id:" + room.getSofa().getId());
        LogUtils.i(TAG,"cloneRoom.sofa.id :" + cloneRoom.getSofa().getId());
    }

    static class Room implements Serializable{
        private int id;

        private String address;

        private Sofa sofa;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Sofa getSofa() {
            return sofa;
        }

        public void setSofa(Sofa sofa) {
            this.sofa = sofa;
        }

        @Override
        protected Object clone()  {
            try {
                // 写当前对象
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    byteArrayOutputStream);
                objectOutputStream.writeObject(this);

                // 读当前对象
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    byteArrayOutputStream.toByteArray());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                return objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static class Sofa implements Serializable {
        private int id;

        private String brand = "";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }
    }
}
