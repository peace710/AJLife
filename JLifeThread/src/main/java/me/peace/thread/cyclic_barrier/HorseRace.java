package me.peace.thread.cyclic_barrier;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class HorseRace {
    private static final String TAG = "HorseRace";
    public static final int FINISH_LINE  = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService service = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses,final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            @Override
            public void run() {
                StringBuilder builder = new StringBuilder();
                for (int i = 0 ; i < FINISH_LINE ;i++){
                    builder.append("=");
                }
                LogUtils.i(TAG,builder.toString());
                for (Horse horse : horses){
                    LogUtils.i(TAG,horse.tracks());
                    if (horse.getStrides() >= FINISH_LINE){
                        LogUtils.i(TAG,horse + "won!");
                        service.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    LogUtils.i(TAG,"barrier-action sleep interrupted");
                }
            }
        });
        for (int i = 0 ;i < nHorses;i++){
            Horse horse = new Horse(barrier);
            horses.add(horse);
            service.execute(horse);
        }
    }
}
