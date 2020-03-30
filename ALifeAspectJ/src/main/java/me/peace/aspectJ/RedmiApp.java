package me.peace.aspectJ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RedmiApp {
    private static final String TAG = RedmiApp.class.getSimpleName();

    private int id;

    private String name = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void div(){
        LogUtils.i(TAG,"5 / 0 = " + (5 / 0));
    }

    public void openFile(){
        try {
            FileInputStream inputStream = new FileInputStream(new File("a.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
