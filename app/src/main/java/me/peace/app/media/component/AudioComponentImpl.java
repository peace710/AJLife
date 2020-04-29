package me.peace.app.media.component;

import android.media.MediaPlayer;

public class AudioComponentImpl implements AudioComponent{
    private IAudioMedia media;

    private AudioComponentImpl() {
        this.media = new AudioMediaPlayer();
    }

    public static AudioComponentImpl get(){
        return Holder.sInstance;
    }

    static class Holder{
        private static AudioComponentImpl sInstance = new AudioComponentImpl();
    }

    @Override
    public void start(String path) {
        start(path,false);
    }

    @Override
    public void start(String path, boolean isLoop) {
        start(path,isLoop,new AudioComponentListener());
    }

    @Override
    public void start(String path, boolean isLoop, IAudioMedia.IAudioListener listener) {
        media.init(listener);
        media.setDataSource(path);
        media.loop(isLoop);
        media.prepareAsync();
    }

    @Override
    public void stop() {
        media.stop();
        media.reset();
//        media.release();
    }

    class AudioComponentListener extends AudioMediaPlayer.DefaultAudioListener{
        @Override
        public void onPrepared(MediaPlayer mp) {
            super.onPrepared(mp);
            mp.start();
        }
    }
}
