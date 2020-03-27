package me.peace.design;


/**
 * 责任链模式
 *
 *
 * 职责链模式 (Chain of Responsibility Pattern)：避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求
 * 将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。职责链模式是一种对象行为型模式
 *
 * 职责链模式总结
 * 职责链模式通过建立一条链来组织请求的处理者，请求将沿着链进行传递，请求发送者无须知道请求在何时、何处以及如何被处
 * 理，实现了请求发送者与处理者的解耦。
 * 1.主要优点
 * 优点：
 * (1) 职责链模式使得一个对象无须知道是其他哪一个对象处理其请求，对象仅需知道该请求会被处理即可，接收者和发送者都没
 * 有对方的明确信息，且链中的对象不需要知道链的结构，由客户端负责链的创建，降低了系统的耦合度
 * (2) 请求处理对象仅需维持一个指向其后继者的引用，而不需要维持它对所有的候选处理者的引用，可简化对象的相互连接。
 * (3) 在给对象分派职责时，职责链可以给我们更多的灵活性，可以通过在运行时对该链进行动态的增加或修改来增加或改变处理
 * 一个请求的职责
 * (4) 在系统中增加一个新的具体请求处理者时无须修改原有系统的代码，只需要在客户端重新建链即可，从这一点来看是符合
 * “开闭原则” 的
 * 2.主要缺点
 * 缺点：
 * (1) 由于一个请求没有明确的接收者，那么就不能保证它一定会被处理，该请求可能一直到链的末端都得不到处理；一个请求也
 * 可能因职责链没有被正确配置而得不到处理
 * (2) 对于比较长的职责链，请求的处理可能涉及到多个处理对象，系统性能将受到一定影响，而且在进行代码调试时不太方便
 * (3) 如果建链不当，可能会造成循环调用，将导致系统陷入死循环
 * 3.适用场景
 * 在以下情况下可以考虑使用职责链模式：
 * (1) 有多个对象可以处理同一个请求，具体哪个对象处理该请求待运行时刻再确定，客户端只需将请求提交到链上，而无须关心
 * 请求的处理对象是谁以及它是如何处理的。
 * (2) 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求
 * (3) 可动态指定一组对象处理请求，客户端可以动态创建职责链来处理请求，还可以改变链中处理者之间的先后次序
 *
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Log.log(AbstractLogger.INFO,"hello world");
        Log.log(AbstractLogger.DEBUG,"method invoke called");
        Log.log(AbstractLogger.ERROR,"low on memory");
    }

    static class Log{
        public static void log(int level,String message){
            InfoLogger info = new InfoLogger(AbstractLogger.INFO);
            DebugLogger debug = new DebugLogger(AbstractLogger.DEBUG);
            ErrorLogger error = new ErrorLogger(AbstractLogger.ERROR);

            info.setNextLogger(debug);
            debug.setNextLogger(error);

            info.log(level,message);
        }
    }

    interface Logger{
        void print(String message);
    }

    static abstract class AbstractLogger implements Logger{
        public static final int INFO = 0;
        public static final int DEBUG = 1;
        public static final int ERROR = 2;

        protected AbstractLogger nextLogger;
        protected int level;

        public void setNextLogger(AbstractLogger nextLogger) {
            this.nextLogger = nextLogger;
        }

        abstract void log(int level, String message);
    }

    static class InfoLogger extends AbstractLogger{
        private static final String TAG = InfoLogger.class.getSimpleName();

        public InfoLogger(int level) {
            this.level = level;
        }

        @Override
        void log(int level, String message) {
            if (level > INFO){
               if (nextLogger != null){
                   nextLogger.log(level,message);
               }
            }else{
                print(message);
            }
        }

        @Override
        public void print(String message) {
            LogUtils.i(TAG,"[info]"+ message);
        }
    }

    static class DebugLogger extends AbstractLogger{
        private static final String TAG = DebugLogger.class.getSimpleName();

        public DebugLogger(int level) {
            this.level = level;
        }

        @Override
        void log(int level, String message) {
            if (level > DEBUG){
                if (nextLogger != null){
                    nextLogger.log(level,message);
                }
            }else{
                print(message);
            }
        }

        @Override
        public void print(String message) {
            LogUtils.i(TAG,"[debug]"+ message);
        }
    }

    static class ErrorLogger extends AbstractLogger{
        private static final String TAG = ErrorLogger.class.getSimpleName();

        public ErrorLogger(int level) {
            this.level = level;
        }

        @Override
        void log(int level, String message) {
            print(message);
        }

        @Override
        public void print(String message) {
            LogUtils.i(TAG,"[error]"+ message);
        }
    }

}
