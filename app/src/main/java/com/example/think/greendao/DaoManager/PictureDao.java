package com.example.think.greendao.DaoManager;

import com.example.think.greendao.entity.PictureChannel;
import com.example.think.greendao.generate.PictureChannelDao;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is PictureDao操作类
 */
public class PictureDao {

    private static PictureChannelDao sPictureChannelDao = GreenDaoManager.getInstance().getPictureChannelDao();

    /**
     * 增
     * @param entity
     * @return
     */
    public static long insert(PictureChannel entity){
        long insert = sPictureChannelDao.insert(entity);
        return insert;
    }

    /**
     * 查询所有
     * @return
     */
    public static List<PictureChannel> queryAll() {
       return sPictureChannelDao.loadAll();
    }

    /**
     * 删除所有
     */
    public static void deleteAll() {
        sPictureChannelDao.deleteAll();
    }

}
