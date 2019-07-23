package com.example.base.greendao.daoManager;

import com.example.base.greendao.entity.NewsChannel;
import com.example.base.greendao.generate.NewsChannelDao;

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
     *
     * @return
     */
    public static List<NewsChannel> queryAll() {
        List<NewsChannel> channelEntities = sNewsChannelDao.loadAll();
        return channelEntities;
    }

    /**
     * 根据是否显示，来查询频道
     *
     * @return
     */
    public static List<NewsChannel> queryIsShowChannel(boolean isShow) {
        List<NewsChannel> list = sNewsChannelDao.queryBuilder().where(NewsChannelDao.Properties.IsShow.eq(isShow)).list();
        return list;
    }

    /**
     * 删除所有
     */
    public static void deleteAll() {
        sNewsChannelDao.deleteAll();
    }


}
