package me.peace.app.router;

import android.content.Context;
import android.content.Intent;

public class Router {
    private static Router sInstance;

    private Router(){

    }

    public static Router getInstance(){
        if (sInstance == null){
            synchronized (Router.class){
                if (sInstance == null){
                    sInstance = new Router();
                }
            }
        }
        return sInstance;
    }

    public void go(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }
}
