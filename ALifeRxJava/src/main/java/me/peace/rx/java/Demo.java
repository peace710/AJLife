package me.peace.rx.java;

public class Demo {
    private static void p(String s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        DemoImpl demo = new DemoImpl();
        p("" + demo.is());
        demo.test();

        DemoImpl1 demo1 = new DemoImpl1();
        p("" + demo1.is());
        demo1.test();
    }


    interface IDemo{
        default boolean is(){
            return false;
        }

        default void test(){
            System.out.println("test");
        }
    }

    static class DemoImpl implements IDemo{
        @Override
        public boolean is() {
            return true;
        }
    }

    static class DemoImpl1 implements IDemo{

    }
}
