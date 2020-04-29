package me.peace.app.media.component;

public interface AudioComponent{
    public void start(String path);

    public void start(String path, boolean isLoop);

    public void start(String path, boolean isLoop, IAudioMedia.IAudioListener listener);

    public void stop();
}
