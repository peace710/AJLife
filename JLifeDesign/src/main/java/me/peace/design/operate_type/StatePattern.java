package me.peace.design.operate_type;

import me.peace.design.LogUtils;

/**
 * 状态模式
 * 在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。
 * 在状态模式中，我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的 context 对象。
 *
 * 主要解决：对象的行为依赖于它的状态（属性），并且可以根据它的状态改变而改变它的相关行为。
 * 优点：
 * 1、封装了转换规则。
 * 2、枚举可能的状态，在枚举状态之前需要确定状态种类。
 * 3、将所有与某个状态有关的行为放到一个类中，并且可以方便地增加新的状态，只需要改变对象状态即可改变对象的行为。
 * 4、允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。
 * 5、可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
 *
 * 缺点：
 * 1、状态模式的使用必然会增加系统类和对象的个数。
 * 2、状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱。
 * 3、状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需修改对应类的源代码。
 *
 */
public class StatePattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();
        player.init();
        player.prepare("1.mp4");
        player.start();
        player.pause();
        player.resume();
        player.stop();
        player.release();
    }


    //Context对象
    static class MediaPlayer implements Media{
        private MediaState state;

        public MediaPlayer() {
            this.state = new IdleState(this);
        }

        private void setState(MediaState state) {
            this.state = state;
        }

        @Override
        public void init() {
            this.state.init();
        }

        @Override
        public void prepare(String url) {
            this.state.prepare(url);
        }

        @Override
        public void start() {
            this.state.start();
        }

        @Override
        public void resume() {
            this.state.resume();
        }

        @Override
        public void pause() {
            this.state.pause();
        }

        @Override
        public void stop() {
            this.state.stop();
        }

        @Override
        public void release() {
            this.state.release();
        }
    }

    interface Media{
        void init();
        void prepare(String url);
        void start();
        void resume();
        void pause();
        void stop();
        void release();
    }

    static class MediaState implements Media{
        protected MediaPlayer player;

        @Override
        public void init() {

        }

        @Override
        public void prepare(String url) {

        }

        @Override
        public void start() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void pause() {

        }

        @Override
        public void stop() {

        }

        @Override
        public void release() {

        }
    }

    static class IdleState extends MediaState{
        private static final String TAG = IdleState.class.getSimpleName();

        public IdleState(MediaPlayer player) {
           this.player = player;
        }

        @Override
        public void init() {
            LogUtils.i(TAG,"init media player");
            player.setState(new ReadyState(player));
        }
    }

    static class ReadyState extends MediaState{
        private static final String TAG = ReadyState.class.getSimpleName();

        public ReadyState(MediaPlayer player) {
            this.player = player;
        }

        @Override
        public void prepare(String url) {
            LogUtils.i(TAG,"set data source : " + url);
            player.setState(new PlayState(player));
        }
    }

    static class PlayState extends MediaState{
        private static final String TAG = PlayState.class.getSimpleName();

        public PlayState(MediaPlayer player) {
            this.player = player;
        }

        @Override
        public void start() {
            LogUtils.i(TAG,"media player start ");
        }

        @Override
        public void resume() {
            LogUtils.i(TAG,"media player resume ");
        }

        @Override
        public void pause() {
            LogUtils.i(TAG,"media player pause ");
        }

        @Override
        public void stop() {
            LogUtils.i(TAG,"media player stop ");
        }

        @Override
        public void release() {
            LogUtils.i(TAG,"media player release ");
            player.setState(new IdleState(player));
        }
    }
}
