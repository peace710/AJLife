package me.peace.design;

import java.util.HashMap;

/**
 * 享元模式（Flyweight Pattern）
 * 主要用于减少创建对象的数量，以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了减少对象数量从而改善应用所需的对象结构的方式。
 * 享元模式尝试重用现有的同类对象，如果未找到匹配的对象，则创建新对象。
 *
 */
 public class FlyWeightPattern {
    public static void main(String[] args) {
        ColorPaint paint1 = PaintFactory.getPaint("red");
        paint1.draw();

        ColorPaint paint2 = PaintFactory.getPaint("green");
        paint2.draw();

        ColorPaint paint3 = PaintFactory.getPaint("blue");
        paint3.draw();

        ColorPaint paint4 = PaintFactory.getPaint("yellow");
        paint4.draw();

        ColorPaint paint5 = PaintFactory.getPaint("red");
        paint5.draw();
    }

     static class ColorPaint{
         private static final String TAG = ColorPaint.class.getSimpleName();

         private String color;

         public ColorPaint(String color) {
             this.color = color;
         }

         public void draw(){
             LogUtils.i(TAG,"draw with " + color + " paint");
         }
     }

     static class PaintFactory{
         private static final String TAG = PaintFactory.class.getSimpleName();

         static HashMap<String,ColorPaint> map = new HashMap<>();

         public static ColorPaint getPaint(String color){
             ColorPaint paint = map.get(color);
             if (paint == null){
                 paint = new ColorPaint(color);
                 map.put(color,paint);
                 LogUtils.i(TAG,"create " + color + " paint");
             }
             return paint;
         }
     }
 }
