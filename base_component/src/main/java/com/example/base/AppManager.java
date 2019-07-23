package com.example.base;

import android.app.Application;
import android.content.Context;

import com.example.base.greendao.daoManager.GreenDaoManager;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is AppManager
 */
public class AppManager extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();

        //初始化GreenDao
        GreenDaoManager.getInstance().initGreenDao(this);
    }

    public static Context getAppContext() {
        return appContext;
    }


}
