package me.peace.app.media.music;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class MusicMediaPlayer implements IMedia {
    private Context context;
    private MediaPlayer player;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void setDataSource(String path) {
        player = MediaPlayer.create(context, Uri.parse(path));
        player.setVolume(0.3f, 0.3f);
        player.setOnCompletionListener(player-> player.start());
    }

    @Override
    public void setDataSource(int resId) {
        player = MediaPlayer.create(context, resId);
        player.setVolume(0.3f, 0.3f);
        player.setOnCompletionListener(player-> player.start());
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
            player.stop();
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
