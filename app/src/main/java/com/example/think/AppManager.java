package com.example.think;

import android.app.Application;
import android.content.Context;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is AppManager
 */
public class AppManager extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
