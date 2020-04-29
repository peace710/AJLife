package me.peace.app.media.component;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class AudioMediaPlayer implements IAudioMedia {
    private static final String TAG = "AudioMediaPlayer";
    private MediaPlayer player;
    private IAudioListener listener;
    private int state = STATE_IDLE;

    @Override
    public void init(IAudioListener listener) {
        Log.d(TAG, "init() called with: listener = [" + listener + "]");
        state = STATE_IDLE;
        player = new MediaPlayer();
        if (listener != null){
            this.listener = listener;
        }else{
            this.listener = new DefaultAudioListener();
        }
        player.setOnPreparedListener(mp-> {
            state = STATE_PREPARED;
            this.listener.onPrepared(mp); }
        );

        player.setOnInfoListener((mp, what, extra) ->{
            return this.listener.onInfo(mp,what,extra);
        });

        player.setOnErrorListener((mp, what, extra) ->{
            state = STATE_ERROR;
            return this.listener.onError(mp,what,extra);
        });

        player.setOnCompletionListener(mp -> {
            state = STATE_COMPLETE;
            this.listener.onCompletion(mp);
        });
    }

    @Override
    public void loop(boolean isLoop) {
        Log.d(TAG, "loop() called with: isLoop = [" + isLoop + "]");
        if (player != null){
            player.setLooping(isLoop);
        }
    }

    @Override
    public boolean isPlaying() {
        Log.d(TAG, "isPlaying() called");
        return player != null && player.isPlaying();
    }

    @Override
    public void setDataSource(String path) {
        Log.d(TAG, "setDataSource() called with: path = [" + path + "]");
        if (player != null){
            switch (state){
                case STATE_IDLE:
                    try {
                        player.setDataSource(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    state = STATE_INITIALIZED;
                    break;
            }
        }
    }

    @Override
    public void prepareAsync() {
        Log.d(TAG, "prepareAsync() called");
        if (player != null){
            switch (state){
                case STATE_INITIALIZED:
                case STATE_STOPPED:
                    try {
                        player.prepareAsync();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    state = STATE_PREPARING;
                    break;
            }
        }
    }

    @Override
    public void start() {
        Log.d(TAG, "start() called");
        if (player != null){
            switch (state){
                case STATE_PREPARED:
                case STATE_STARTED:
                case STATE_PAUSED:
                case STATE_COMPLETE:
                    try {
                        player.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    state = STATE_STARTED;
                    break;
            }
        }
    }

    @Override
    public void stop() {
        Log.d(TAG, "stop() called");
        if (player != null){
            switch (state){
                case STATE_PREPARED:
                case STATE_STARTED:
                case STATE_PAUSED:
                case STATE_COMPLETE:
                    try {
                        player.stop();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    state = STATE_STOPPED;
                    break;
            }
        }
    }

    @Override
    public void pause() {
        Log.d(TAG, "pause() called");
        if (player != null){
            switch (state){
                case STATE_STARTED:
                case STATE_PAUSED:
                    try {
                        player.pause();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    state = STATE_PAUSED;
                    break;
            }
        }
    }

    @Override
    public void release() {
        Log.d(TAG, "release() called");
        if (player != null){
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            state = STATE_END;
        }
    }

    @Override
    public void reset() {
        Log.d(TAG, "reset() called");
        if (player != null){
            try {
                player.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
            state = STATE_IDLE;
        }
    }

    @Override
    public void seekTo(int msec) {
        Log.d(TAG, "seekTo() called with: msec = [" + msec + "]");
        if (player != null){
            switch (state){
                case STATE_PREPARED:
                case STATE_STARTED:
                case STATE_PAUSED:
                case STATE_COMPLETE:
                    try {
                        player.seekTo(msec);
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public int getCurrentPosition() {
        Log.d(TAG, "getCurrentPosition() called");
        try {
            return player != null ? player.getCurrentPosition() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getDuration() {
        Log.d(TAG, "getDuration() called");
        try {
            return player != null ? player.getDuration() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    static class DefaultAudioListener implements IAudioListener{
        private static final String TAG = "DefaultAudioListener";

        @Override
        public void onPrepared(MediaPlayer mp) {
            Log.d(TAG, "onPrepared() called with: mp = [" + mp + "]");
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.d(TAG, "onCompletion() called with: mp = [" + mp + "]");
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Log.d(TAG, "onError() called with: mp = [" + mp + "], what = [" + what + "], extra = [" + extra + "]");
            return false;
        }

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            Log.d(TAG, "onInfo() called with: mp = [" + mp + "], what = [" + what + "], extra = [" + extra + "]");
            return false;
        }
    }
}
