package me.peace.design.interface_type;

import java.util.ArrayList;

import me.peace.design.LogUtils;

/**
 * 组合模式
 *
 * 一个抽象的实现，一堆这种抽象，组装在一起
 * 然后这个持有一堆抽象的 这个抽象实现，就实现了组合模式
 *
 * 组合模式总结
 * 组合模式使用面向对象的思想来实现树形结构的构建与处理，描述了如何将容器对象和叶子对象进行递归组合，实现简单，灵活性好
 *
 * 主要优点
 * 组合模式的主要优点如下：
 * (1) 组合模式可以清楚地定义分层次的复杂对象，表示对象的全部或部分层次，它让客户端忽略了层次的差异，方便对整个层次结构进行控制。
 * (2) 客户端可以一致地使用一个组合结构或其中单个对象，不必关心处理的是单个对象还是整个组合结构，简化了客户端代码。
 * (3) 在组合模式中增加新的容器构件和叶子构件都很方便，无须对现有类库进行任何修改，符合“开闭原则”。
 * (4) 组合模式为树形结构的面向对象实现提供了一种灵活的解决方案，通过叶子对象和容器对象的递归组合，可以形成复杂的树形结构，但对树形结构的控制却非常简单。
 * 主要缺点
 * 组合模式的主要缺点如下：
 * 在增加新构件时很难对容器中的构件类型进行限制。有时候我们希望一个容器中只能有某些特定类型的对象，例如在某个文件夹中
 * 只能包含文本文件，使用组合模式时，不能依赖类型系统来施加这些约束，因为它们都来自于相同的抽象层，在这种情况下，必须
 * 通过在运行时进行类型检查来实现，这个实现过程较为复杂。
 *
 */
public class CompositePattern {
    public static void main(String[] args) {
        Component school = new GroupComponent("School");

        Component grade1 = new GroupComponent("grade1");
        Component grade2 = new GroupComponent("grade2");
        Component grade3 = new GroupComponent("grade3");

        Component class1 = new BaseComponent("class1");
        Component class2 = new BaseComponent("class2");
        Component class3 = new BaseComponent("class3");
        Component class4 = new BaseComponent("class4");
        Component class5 = new BaseComponent("class5");
        Component class6 = new BaseComponent("class6");

        grade1.add(class1);
        grade1.add(class2);

        grade2.add(class3);
        grade2.add(class4);

        grade3.add(class5);
        grade3.add(class6);

        school.add(grade1);
        school.add(grade2);
        school.add(grade3);

        school.showSelf();
    }


    interface Component{
        String getName();

        void add(Component component);

        void remove(Component component);

        Component get(int i);

        void removeSelf();

        void showSelf();
    }

    static class BaseComponent implements Component{
        private static final String TAG = BaseComponent.class.getSimpleName();

        private final String name;

        public BaseComponent(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void add(Component component) {
            LogUtils.i(TAG,"add is not support");
        }

        @Override
        public void remove(Component component) {
            LogUtils.i(TAG,"remove is not support");
        }

        @Override
        public Component get(int i) {
            LogUtils.i(TAG,"get is not support");
            return null;
        }

        @Override
        public void removeSelf() {
            LogUtils.i(TAG,"removeSelf[" + name + "]");
        }

        @Override
        public void showSelf() {
            LogUtils.i(TAG,"showSelf[" + name + "]");
        }
    }

    static class GroupComponent implements Component{
        private static final String TAG = GroupComponent.class.getSimpleName();
        private ArrayList<Component> components;

        private final String name;

        public GroupComponent(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public void add(Component component) {
            if (components == null){
                components = new ArrayList<>();
            }
            components.add(component);
        }

        @Override
        public void remove(Component component) {
            if (components != null && components.indexOf(component) >= 0){
                components.remove(component);
            }
        }

        @Override
        public Component get(int i) {
            if (components != null){
                return components.get(i);
            }
            return null;
        }

        @Override
        public void removeSelf() {
            for (Component component : components){
                component.removeSelf();
            }
        }

        @Override
        public void showSelf() {
            LogUtils.i(TAG,"showSelf[" + name + "]");
            for (Component component : components){
                component.showSelf();
            }
        }
    }
}
