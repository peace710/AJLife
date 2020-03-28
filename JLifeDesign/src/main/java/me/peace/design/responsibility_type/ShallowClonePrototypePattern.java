package me.peace.design.responsibility_type;

import me.peace.design.LogUtils;


/**
 * 浅克隆原型模式
 *
 * 浅克隆和深克隆的主要区别在于是否支持引用类型的成员变量的复制
 *
 */
public class ShallowClonePrototypePattern {
    private static final String TAG = ShallowClonePrototypePattern.class.getSimpleName();

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

    static class Room implements Cloneable{
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
            Room room = null;
            try {
                room = (Room) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return room;
        }
    }

    static class Sofa{
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
