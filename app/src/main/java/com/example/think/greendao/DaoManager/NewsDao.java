package com.example.think.greendao.DaoManager;

import com.example.think.greendao.entity.NewsChannel;
import com.example.think.greendao.generate.NewsChannelDao;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is NewsDao操作类
 */
public class NewsDao {

    private static NewsChannelDao sNewsChannelDao = GreenDaoManager.getInstance().getNewsChannelDao();

    /**
     * 增
     *
     * @param entity
     * @return
     */
    public static long insert(NewsChannel entity) {
        long insert = sNewsChannelDao.insert(entity);
        return insert;
    }

    /**
     * 查询所有
     */
    public static List<NewsChannel> queryAll() {
        List<NewsChannel> channelEntities = sNewsChannelDao.loadAll();
        return channelEntities;
    }

    public static void deleteAll() {
        sNewsChannelDao.deleteAll();
    }


}
