package com.example.think.greendao.DaoManager;

import com.example.think.greendao.entity.VideoChannel;
import com.example.think.greendao.generate.VideoChannelDao;

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
