package me.peace.thread.semaphore;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<>();
    private volatile  boolean[] checkedOut;

    private Semaphore available;

    public Pool(Class<T> clazz,int size) {
        this.size = size;
        checkedOut = new boolean[size];
        available = new Semaphore(size,true);

        try {
            for (int i = 0; i<size;i++){
                items.add(clazz.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public T checkOut() throws InterruptedException{
        available.acquire();
        return getItem();
    }

    public void checkIn(T x){
        if (releaseItem(x)){
            available.release();
        }
    }

    private synchronized T  getItem() {
        for (int i = 0 ; i < size ;i++){
            if (!checkedOut[i]){
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    private synchronized boolean releaseItem(T item){
        int index = items.indexOf(item);
        if (index == -1){
            return false;
        }
        if (checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
