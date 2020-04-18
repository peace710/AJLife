package me.peace.app.media.music;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class MusicSoundPool implements IMedia{
    private Context context;
    private SoundPool soundPool;
    private int id = 0;
    private int steamId = 0;

    @Override
    public void init(Context context) {
        this.context = context;
        if (Build.VERSION.SDK_INT >= 21){
            SoundPool.Builder builder = new SoundPool.Builder();
            builder.setMaxStreams(1);
            AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
            attrBuilder.setLegacyStreamType(AudioManager.STREAM_MUSIC);
            builder.setAudioAttributes(attrBuilder.build());
            soundPool = builder.build();
        }else{
            soundPool = new SoundPool(1,AudioManager.STREAM_MUSIC,0);
        }
    }

    @Override
    public void setDataSource(int resId) {
        if (soundPool != null){
            id = soundPool.load(context,resId,1);
        }
    }

    @Override
    public void setDataSource(String path) {
        if (soundPool != null){
            id = soundPool.load(path,1);
        }
    }

    @Override
    public void play() {
        if (soundPool != null){
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    if (status == 0 && id != 0){
                        steamId = soundPool.play(id,1,1,1,0,1);
                    }
                }
            });
        }
    }

    @Override
    public void stop() {
        if (soundPool != null){
            if (steamId != 0){
                soundPool.stop(steamId);
            }
        }
    }

    @Override
    public void pause() {
        if (soundPool != null){
            if (steamId != 0){
                soundPool.pause(steamId);
            }
        }
    }

    @Override
    public void release() {
        if (soundPool != null){
            if (id != 0){
                soundPool.unload(id);
            }
            soundPool.release();
        }
    }
}
