package com.example.think.greendao.DaoManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.think.Constants;
import com.example.think.greendao.generate.DaoMaster;
import com.example.think.greendao.generate.DaoSession;
import com.example.think.greendao.generate.NewsChannelDao;
import com.example.think.greendao.generate.PictureChannelDao;
import com.example.think.greendao.generate.VideoChannelDao;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is GreenDaoManager
 */
public class GreenDaoManager {

    private static volatile GreenDaoManager sInstance;
    private DaoSession mDaoSession;

    private GreenDaoManager() {
    }

    public static GreenDaoManager getInstance() {
        if (sInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (sInstance == null) {
                    sInstance = new GreenDaoManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化GreenDao
     */
    public void initGreenDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME, null);
        SQLiteDatabase database = helper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public NewsChannelDao getNewsChannelDao() {
        return mDaoSession.getNewsChannelDao();
    }

    public PictureChannelDao getPictureChannelDao() {
        return mDaoSession.getPictureChannelDao();
    }

    public VideoChannelDao getVideoChannelDao() {
        return mDaoSession.getVideoChannelDao();
    }

}
