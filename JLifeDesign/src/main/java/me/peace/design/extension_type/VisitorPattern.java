package me.peace.design.extension_type;

import java.util.ArrayList;
import java.util.List;

import me.peace.design.LogUtils;

/**
 * 在访问者模式（Visitor Pattern）中，我们使用了一个访问者类，它改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变。
 * 这种类型的设计模式属于行为型模式。根据模式，元素对象已接受访问者对象，这样访问者对象就可以处理元素对象上的操作。
 */
public class VisitorPattern {
    public static void main(String[] args) {
        VisitReport report = new VisitReport();
        report.visit(new DoctorVisitor());
        report.visit(new MayorVisitor());
    }

    interface Masses{
        void accept(Visitor visitor);
    }

    static class Family implements Masses{
        private static final String TAG = Family.class.getSimpleName();
        private String name;

        public Family(String name) {
            this.name = name;
        }

        public void health(){
            LogUtils.i(TAG,name + " is healthy");
        }

        public void life(){
            LogUtils.i(TAG,name + " is very happy");
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    static class Company implements Masses{
        private static final String TAG = Company.class.getSimpleName();
        private String name;

        public Company(String name) {
            this.name = name;
        }

        public void health(){
            LogUtils.i(TAG,name + " factory staff are healthy");
        }

        public void efficiency(){
            LogUtils.i(TAG,name + " factory efficiency is very good");
        }

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }

    interface Visitor{
        void visit(Family family);

        void visit(Company company);
    }

    static class DoctorVisitor implements Visitor{
        @Override
        public void visit(Family family) {
            family.health();
        }

        @Override
        public void visit(Company company) {
            company.health();
        }
    }

    static class MayorVisitor implements Visitor{
        @Override
        public void visit(Family family) {
            family.life();
        }

        @Override
        public void visit(Company company) {
            company.efficiency();
        }
    }

    static class VisitReport{
        List<Masses> list = new ArrayList<>();

        public VisitReport() {
            list.add(new Family("Tom"));
            list.add(new Company("IBM"));
            list.add(new Family("Kobe"));
            list.add(new Company("Google"));
            list.add(new Family("James"));
            list.add(new Company("AMD"));
        }

        public void visit(Visitor visitor){
            for (Masses masses : list){
                masses.accept(visitor);
            }
        }
    }
}
