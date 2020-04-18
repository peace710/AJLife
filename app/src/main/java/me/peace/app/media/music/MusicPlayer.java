package me.peace.app.media.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class MusicPlayer implements IMedia {
    private static final String TAG = "MusicPlayer";
    private MediaPlayer player;

    @Override
    public void init(Context context) {
        player = new MediaPlayer();
    }

    @Override
    public void setDataSource(String path) {
        try {
            player.reset();
            player.setDataSource(path);
            player.prepare();
//            player.prepareAsync();
//            player.setOnPreparedListener(mp -> mp.start());
            player.setVolume(0.7f, 0.7f);
//            player.setLooping(true);
            player.setOnCompletionListener(
              mp -> {
                  Log.d(TAG, "onCompletion() called with: mp = [" + mp + "]");
                  mp.start();
              }
            );
            player.setOnErrorListener((mp, what, extra) ->{
                Log.d(TAG, "onError() called with: mp = [" + mp + "], what = [" + what + "], extra = [" + extra + "]");
                return false;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataSource(int resId) {

    }

    @Override
    public void play() {
        if (player != null){
            player.start();
        }
    }

    @Override
    public void stop() {
        if (player != null){
            player.pause();
        }
    }

    @Override
    public void pause() {
        if (player != null){
            player.pause();
        }
    }

    @Override
    public void release() {
        if (player != null){
            player.release();
        }
    }
}
