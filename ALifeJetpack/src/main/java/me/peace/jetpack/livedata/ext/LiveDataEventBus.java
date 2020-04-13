package me.peace.jetpack.livedata.ext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import androidx.lifecycle.MutableLiveData;

public class LiveDataEventBus {
    private final Map<String, LiveEventData<Object>> bus;

    private LiveDataEventBus() {
        bus = new ConcurrentHashMap<>();
    }

    private static class LiveDataBusHolder{
        private static LiveDataEventBus instance = new LiveDataEventBus();
    }

    public static LiveDataEventBus get(){
        return LiveDataBusHolder.instance;
    }

    public <T> LiveEventData<T> with(String key,Class<T> clazz){
        if (!bus.containsKey(key)){
            bus.put(key,new LiveEventData<>());
        }
        return (LiveEventData<T>) bus.get(key);
    }

    public LiveEventData<Object> with(String key){
        return with(key,Object.class);
    }

    public void leave(String key){
        if (bus.containsKey(key)){
            bus.remove(key);
        }
    }
}
