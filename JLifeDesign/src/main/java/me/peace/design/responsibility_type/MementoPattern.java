
package me.peace.design.responsibility_type;

import java.util.HashMap;
import java.util.Map;

import me.peace.design.LogUtils;

/**
 * 备忘录模式（Memento Pattern）保存一个对象的某个状态，以便在适当的时候恢复对象。备忘录模式属于行为型模式。
 *
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 * 所谓备忘录模式就是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。
 * 很多时候我们总是需要记录一个对象的内部状态，这样做的目的就是为了允许用户取消不确定或者错误的操作，能够恢复到他原先的状态，使得他有"后悔药"可吃。
 *
 * 优点：
 * 1、给用户提供了一种可以恢复状态的机制，可以使用户能够比较方便地回到某个历史的状态。
 * 2、实现了信息的封装，使得用户不需要关心状态的保存细节。
 *
 * 缺点：
 * 消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。
 *
 * 使用场景：
 * 1、需要保存/恢复数据的相关状态场景。 2、提供一个可回滚的操作。
 *
 */
public class MementoPattern {

    public static void main(String[] args) {
        GameRecordManager manager = new GameRecordManager();
        Game dragon = new Game("1","Dragon Quest Builders 2");
        dragon.loadGame();
        manager.save(dragon.getId(), dragon.saveState(30));


        Game callOfDuty = new Game("2","Call of Duty");
        callOfDuty.loadGame();
        manager.save(callOfDuty.getId(), callOfDuty.saveState(50));

        Game builder = new Game("1","Dragon Quest Builders 2");
        builder.loadState(manager.load(builder.getId()));
        builder.loadGame();

        Game call = new Game("2","Call of Duty");
        call.loadState(manager.load(call.getId()));
        call.loadGame();
    }

    //备忘录对象
    static class GameRecord{
        private GameState state;

        public GameRecord(GameState state) {
            this.state = state;
        }

        public GameState getState() {
            return state;
        }

        public void setState(GameState state) {
            this.state = state;
        }
    }

    //对象状态属性
    static class GameState implements Cloneable{
        private String id;
        private int progress;

        public GameState(String id, int progress) {
            this.id = id;
            this.progress = progress;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    //需要被备份的对象
    static class Game{
        private static final String TAG = Game.class.getSimpleName();

        private String id;

        private String name;

        private GameState state;

        public Game(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void loadGame(){
            if (state == null){
                state = new GameState(id,0);
            }
            LogUtils.i(TAG,name +" is loading,last progress is " + state.getProgress());
        }

        public GameRecord saveState(int progress) {
            if (state == null){
                state = new GameState(id,progress);
            }else{
                state.setProgress(progress);
            }
            return new GameRecord(state);
        }

        public void loadState(GameRecord record) {
            this.state = record.getState();
        }
    }

    //备忘录管理对象，维护所有备忘录
    static class GameRecordManager{
        Map<String,GameRecord> map;

        public void save(String id,GameRecord record){
            if (map == null){
                map = new HashMap<>();
            }
            map.put(id,record);
        }

        public GameRecord load(String id){
            if (map != null){
                return map.get(id);
            }
            return null;
        }
    }
}
