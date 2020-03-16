package me.peace.reflection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ClassObjectFactory {
    private static final String TAG = ClassObjectFactory.class.getSimpleName();

    public static void main(String[] args) {
        BallFactory factory = new BallFactory();
        factory.showBalls(7);
        BallSafeFactory safeFactory = new BallSafeFactory();
        safeFactory.showBalls(7);
        compare(new RedBall());
    }

    private static void compare(Object object){
        LogUtils.i(TAG,"object instanceOf RedBall is " + (object instanceof RedBall));
        LogUtils.i(TAG,"object instanceOf Ball is " + (object instanceof Ball));
        LogUtils.i(TAG,"RedBall.class.isInstance(object) is " + (RedBall.class.isInstance(object)));
        LogUtils.i(TAG,"Ball.class.isInstance(object) is " + (Ball.class.isInstance(object)));
        LogUtils.i(TAG,
            "object.getClass() == RedBall.class is " + (object.getClass() == RedBall.class));
        LogUtils.i(TAG,
            "object.getClass() == Ball.class is " + (object.getClass() == Ball.class));
        LogUtils.i(TAG,
            "object.getClass().equals(RedBall.class) is " + (object.getClass().equals(RedBall.class)));
        LogUtils.i(TAG,
            "object.getClass().equals(Ball.class) is " + (object.getClass().equals(Ball.class)));
    }
}

class BallFactory{
    private static final String TAG = BallFactory.class.getSimpleName();

    static final String[] BALL_TYPES = {
        "me.peace.reflection.WhiteBall",
        "me.peace.reflection.BlackBall",
        "me.peace.reflection.RedBall",
        "me.peace.reflection.YellowBall",
    };

    int random(){
        Random random = new Random();
        return random.nextInt(BALL_TYPES.length);
    }

    Ball create(){
        Ball ball = null;
        try {
            Class<? extends Ball> clazz = (Class<? extends Ball>)Class.forName(BALL_TYPES[random()]);
            ball = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return ball;
    }

    void showBalls(int size){
        for (int i = 0 ;i < size ;i++){
            LogUtils.i(TAG,create().toString());
        }
    }
}


class BallSafeFactory{
    private static final String TAG = BallSafeFactory.class.getSimpleName();


    static final List<Class<? extends Ball>> BALL_CLASSES =
        Collections.unmodifiableList(Arrays.asList(WhiteBall.class,BlackBall.class,RedBall.class,
            YellowBall.class));


    int random(){
        Random random = new Random();
        return random.nextInt(BALL_CLASSES.size());
    }

    //使用些用方法获取类对象，不存在ClassNotFoundException
    Ball create(){
        Ball ball = null;
        try {
            Class<? extends Ball> clazz = BALL_CLASSES.get(random());
            ball = clazz.newInstance();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return ball;
    }

    void showBalls(int size){
        for (int i = 0 ;i < size ;i++){
            LogUtils.i(TAG,create().toString());
        }
    }
}


class Ball{

}

class WhiteBall extends Ball{
    @Override
    public String toString() {
        return "WhiteBall";
    }
}

class BlackBall extends Ball{
    @Override
    public String toString() {
        return "BlackBall";
    }
}

class RedBall extends Ball{
    @Override
    public String toString() {
        return "RedBall";
    }
}

class YellowBall extends Ball{
    @Override
    public String toString() {
        return "YellowBall";
    }
}
