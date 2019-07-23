package com.example.base.greendao.daoManager;

import com.example.base.greendao.entity.VideoChannel;
import com.example.base.greendao.generate.VideoChannelDao;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is VideoDao
 */
public class VideoDao {

    private static VideoChannelDao sVideoChannelDao = GreenDaoManager.getInstance().getVideoChannelDao();

    public static long insert(VideoChannel entity) {
        return sVideoChannelDao.insert(entity);
    }

    public static List<VideoChannel> queryAll() {
        List<VideoChannel> videoChannels = sVideoChannelDao.loadAll();
        return videoChannels;
    }

    public static void deleteAll(){
        sVideoChannelDao.deleteAll();
    }

}
