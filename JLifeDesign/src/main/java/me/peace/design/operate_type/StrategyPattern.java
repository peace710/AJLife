package me.peace.design.operate_type;

import me.peace.design.LogUtils;

/**
 * 在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
 *
 * 在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。
 * 策略对象改变 context 对象的执行算法。
 *
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.gift(Hero.GIFT_POWER);
        mobilizeSkill(hero);
        hero.gift(Hero.GIFT_MAGIC);
        mobilizeSkill(hero);
    }

    private static void mobilizeSkill(Hero hero){
        hero.skillA();
        hero.skillB();
        hero.skillC();
        hero.skillD();
    }


   interface Skill{
       void skillA();

       void skillB();

       void skillC();

       void skillD();
   }

   static class MagicSkill implements Skill{
       private static final String TAG = MagicSkill.class.getSimpleName();

       @Override
       public void skillA() {
           LogUtils.i(TAG,"magic skill A");
       }

       @Override
       public void skillB() {
           LogUtils.i(TAG,"magic skill B");
       }

       @Override
       public void skillC() {
           LogUtils.i(TAG,"magic skill C");
       }

       @Override
       public void skillD() {
           LogUtils.i(TAG,"magic skill D");
       }
   }

    static class PowerSkill implements Skill{
        private static final String TAG = PowerSkill.class.getSimpleName();

        @Override
        public void skillA() {
            LogUtils.i(TAG,"power skill A");
        }

        @Override
        public void skillB() {
            LogUtils.i(TAG,"power skill B");
        }

        @Override
        public void skillC() {
            LogUtils.i(TAG,"power skill C");
        }

        @Override
        public void skillD() {
            LogUtils.i(TAG,"power skill D");
        }
    }



    static class Hero implements Skill{
        public static final int GIFT_MAGIC = 0;
        public static final int GIFT_POWER = 1;

        Skill skill;

        public void gift(int gift){
            if (gift == GIFT_MAGIC){
                skill = new MagicSkill();
            }else if (gift == GIFT_POWER){
                skill = new PowerSkill();
            }else{
                skill = new Skill() {
                    @Override
                    public void skillA() {

                    }

                    @Override
                    public void skillB() {

                    }

                    @Override
                    public void skillC() {

                    }

                    @Override
                    public void skillD() {

                    }
                };
            }
        }

        @Override
        public void skillA() {
            skill.skillA();
        }

        @Override
        public void skillB() {
            skill.skillB();
        }

        @Override
        public void skillC() {
            skill.skillC();
        }

        @Override
        public void skillD() {
            skill.skillD();
        }
    }
}
