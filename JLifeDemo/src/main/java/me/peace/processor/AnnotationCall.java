package me.peace.processor;

import me.peace.annotation.Call;
import utils.LogUtils;

public class AnnotationCall {
    private static final String TAG = AnnotationCall.class.getSimpleName();

    @Call
    public void log(){
        LogUtils.i(TAG,"log");
    }

    public void err(){
        LogUtils.i(TAG,"err");
    }

    @Call
    public void info(){
        LogUtils.i(TAG,"info");
    }
}
