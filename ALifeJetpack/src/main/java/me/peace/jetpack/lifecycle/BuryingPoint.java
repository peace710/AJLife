package me.peace.jetpack.lifecycle;

import android.util.Log;

public class BuryingPoint implements Command {

    public static BuryingPoint get(){
        return BuryingPointHolder.instance;
    }

    private BuryingPoint() {
    }

    private static class BuryingPointHolder{
        private static BuryingPoint instance = new BuryingPoint();
    }

    private BuryingPointCommand command;

    public void init(){
        command = new BuryingPointCommand();
    }

    public void destroy(){
        command = null;
    }

    @Override
    public void markLifecycle(String life) {
        if (command != null){
            command.markLifecycle(life);
        }
    }

    class BuryingPointCommand implements Command{
        private static final String TAG = "BuryingPointCommand";

        @Override
        public void markLifecycle(String life) {
            Log.d(TAG, "markLifecycle() called with: life = [" + life + "]");
        }
    }
}
