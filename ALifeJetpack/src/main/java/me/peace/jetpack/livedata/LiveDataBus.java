package me.peace.jetpack.livedata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import androidx.lifecycle.MutableLiveData;

public class LiveDataBus {
    private final Map<String, MutableLiveData<Object>> bus;

    private LiveDataBus() {
        bus = new ConcurrentHashMap<>();
    }

    private static class LiveDataBusHolder{
        private static  LiveDataBus instance = new LiveDataBus();
    }

    public static LiveDataBus getInstance(){
        return LiveDataBusHolder.instance;
    }

    public <T> MutableLiveData<T> with(String key,Class<T> clazz){
        if (!bus.containsKey(key)){
            bus.put(key,new MutableLiveData<>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }

    public MutableLiveData<Object> with(String key){
        return with(key,Object.class);
    }

    public void remove(String key){
        if (bus.containsKey(key)){
            bus.remove(key);
        }
    }
}
