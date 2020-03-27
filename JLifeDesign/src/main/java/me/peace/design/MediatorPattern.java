package me.peace.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义：定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。中介者模式又叫调停模式，它是迪米特法则的典型应用。
 * 通过使对象明确地相互引用来促进松散耦合，并允许独立地改变它们的交互
 *
 * 1)松散耦合
 * 中介者模式通过把多个同事对象之间的交互封装到中介者对象里面，从而使得同事对象之间松散耦合，基本上可以做到互不依赖。这样一来，同事对象就可以独立的变化和复用，而不再像以前那样“牵一发而动全身”了。
 *
 * 2)集中控制交互
 * 多个同事对象的交互，被封装在中介者对象里面集中管理，使得这些交互行为发生变化的时候，只需要修改中介者对象就可以了，当然如果是已经做好的系统，那就扩展中介者对象，而各个同事类不需要做修改。
 *
 * 3)多对多变成一对多
 * 没有使用中介者模式的时候，同事对象之间的关系通常是多对多的，引入中介者对象过后，中介者对象和同事对象的关系通常变成了双向的一对多，这会让对象的关系更容易理解和实现。
 *
 * 4)过度集中化
 * 中介者模式的一个潜在缺点是，如果同事对象的交互非常多，而且比较复杂，当这些复杂性全部集中到中介者的时候，会导致中介者对象变得十分的复杂，而且难于管理和维护。
 */
public class MediatorPattern {
    public static void main(String[] args) {
        Mediator mediator = new NetMediator();
        Colleague token = new TokenColleague();
        Colleague data = new DataColleague();
        mediator.register(TokenColleague.TAG,token);
        mediator.register(DataColleague.TAG,data);

//        mediator.command(TokenColleague.TAG);
        mediator.command(DataColleague.TAG);
        mediator.clear();
    }

    interface Mediator{
        void register(String name,Colleague colleague);

        void command(String name);

        void clear();
    }

    interface Colleague{
        void request(String... strings);

        String response();
    }

    static class NetMediator implements Mediator{
        private static final String TAG = NetMediator.class.getSimpleName();
        private Map<String,Colleague> map;

        @Override
        public void register(String name, Colleague colleague) {
            if (map == null){
                map = new HashMap<>();
            }
            map.put(name,colleague);
        }

        @Override
        public void command(String name) {
            if (map != null){
                String tag = TokenColleague.TAG;
                Colleague colleague = map.get(name);
                String data;
                if (!name.equalsIgnoreCase(tag)){
                   Colleague token = map.get(tag);
                   token.request();
                   colleague.request(token.response());
                   data = colleague.response();
                }else{
                    colleague.request();
                    data = colleague.response();
                }
                LogUtils.i(TAG,"data : " + data);
            }
        }

        @Override
        public void clear() {
            if (map != null){
                map.clear();
            }
        }
    }

    static class TokenColleague implements Colleague{
        private static final String TAG = TokenColleague.class.getSimpleName();

        @Override
        public void request(String... strings) {
            LogUtils.i(TAG,"start request token");
        }

        @Override
        public String response() {
            return "token-key";
        }
    }

    static class DataColleague implements Colleague{
        private static final String TAG = DataColleague.class.getSimpleName();

        @Override
        public void request(String... strings) {
            if (strings.length > 0) {
                LogUtils.i(TAG, "start request data with token[" + strings[0] + "]");
            }
        }

        @Override
        public String response() {
            return "this is mediator pattern demo";
        }
    }
}
