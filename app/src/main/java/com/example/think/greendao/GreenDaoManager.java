package com.example.think.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.think.Constants;
import com.example.think.database.ChannelEntity;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is GreenDaoManager
 */
public class GreenDaoManager {

    private static volatile GreenDaoManager sInstance;
    private ChannelEntityDao mChannelDao;

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
        DaoSession daoSession = daoMaster.newSession();
        mChannelDao = daoSession.getChannelEntityDao();
    }

    /**
     * 增
     *
     * @param entity
     */
    public long insert(ChannelEntity entity) {
        long insert = mChannelDao.insert(entity);
        return insert;
    }

    /**
     * 查询所有
     */
    public List<ChannelEntity> queryAll() {
        List<ChannelEntity> channelEntities = mChannelDao.loadAll();
        return channelEntities;
    }

    public void deleteAll(){
        mChannelDao.deleteAll();
    }

}
