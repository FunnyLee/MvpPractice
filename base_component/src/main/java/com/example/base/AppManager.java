package com.example.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
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

        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(AppManager.this); // 尽可能早，推荐在Application中初始化

        //初始化GreenDao
        GreenDaoManager.getInstance().initGreenDao(this);
    }

    public static Context getAppManagerContext() {
        return appContext;
    }


}
