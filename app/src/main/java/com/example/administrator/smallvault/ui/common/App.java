package com.example.administrator.smallvault.ui.common;

import android.app.Application;

/**
 * Created by Administrator on 2016/5/5.
 */
public class App extends Application {




    private static volatile App intance = null;

    public App() {
        super();
    }

    private App(String i){

    }




    public static App getIntance() {

        if (intance == null) {
            synchronized (App.class) {
                if (intance == null) {
                    intance = new App("");
                }
            }
        }
        return intance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
