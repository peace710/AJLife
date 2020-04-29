package me.peace.app.media.component;

import android.media.MediaPlayer;

public interface IAudioMedia {
    public void init(IAudioListener listener);

    public void loop(boolean isLoop);

    public boolean isPlaying();

    public void setDataSource(String path);

    public void prepareAsync();

    public void start();

    public void stop();

    public void pause();

    public void release();

    public void reset();

    public void seekTo(int msec);

    public int getCurrentPosition();

    public int getDuration();

    public interface IAudioListener{
        void onPrepared(MediaPlayer mp);

        void onCompletion(MediaPlayer mp);

        boolean onError(MediaPlayer mp, int what, int extra);

        boolean onInfo(MediaPlayer mp, int what, int extra);
    }

    public static final int STATE_ERROR = -1;//错误
    public static final int STATE_IDLE = 0;//初始状态
    public static final int STATE_INITIALIZED = 1;//初始完成状态
    public static final int STATE_PREPARING = 2;//加载源中
    public static final int STATE_PREPARED = 3;//准备播放
    public static final int STATE_STARTED = 4;//正在播放
    public static final int STATE_STOPPED = 5;//取消播放
    public static final int STATE_PAUSED = 6;//暂停中
    public static final int STATE_COMPLETE = 7;//播放完成
    public static final int STATE_END = 8;//结束
}
