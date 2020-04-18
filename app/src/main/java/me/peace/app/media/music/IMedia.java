package me.peace.app.media.music;

import android.content.Context;

public interface IMedia {
    public void init(Context context);

    public void setDataSource(String path);

    public void setDataSource(int resId);

    public void play();

    public void pause();

    public void stop();

    public void release();
}
