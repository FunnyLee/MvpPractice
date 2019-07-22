package com.example.base.greendao.daoManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.base.greendao.generate.DaoMaster;
import com.example.base.greendao.generate.DaoSession;
import com.example.base.greendao.generate.NewsChannelDao;
import com.example.base.greendao.generate.PictureChannelDao;
import com.example.base.greendao.generate.VideoChannelDao;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is GreenDaoManager
 */
public class GreenDaoManager {

    /**
     * 数据库名称
     */
    public static final String DB_NAME = "channel.db";

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
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
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
