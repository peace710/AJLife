package me.peace.design.build_type;

import me.peace.design.LogUtils;

/**
 * 建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。
 * 这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Player player =
            new Player.Builder().name("Kobe Bean Bryant").sex("male").age(41).weight(96).height(198).build();
        player.info();
    }

    static class Player {
        private static final String TAG = Player.class.getSimpleName();

        private String name = "";
        private String sex = "";
        private int age;
        private int weight;
        private int height;

        private Player(Builder builder) {
            this.name = builder.name;
            this.sex = builder.sex;
            this.age = builder.age;
            this.weight = builder.weight;
            this.height = builder.height;
        }

        @Override
        public String toString() {
            return "Player{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                '}';
        }

        public void info(){
            LogUtils.i(TAG,this.toString());
        }

        static class Builder{
            private String name = "";
            private String sex = "";
            private int age;
            private int weight;
            private int height;

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder sex(String sex) {
                this.sex = sex;
                return this;
            }

            public Builder age(int age) {
                this.age = age;
                return this;
            }

            public Builder weight(int weight) {
                this.weight = weight;
                return this;
            }

            public Builder height(int height) {
                this.height = height;
                return this;
            }

            public Player build(){
                return new Player(this);
            }
        }
    }
}
