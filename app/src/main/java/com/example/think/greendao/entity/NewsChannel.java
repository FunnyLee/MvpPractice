package com.example.think.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is GreenDao的实体类
 */
@Entity
public class NewsChannel {

    //@Id：通过这个注解标记的字段必须是Long类型的，这个字段在数据库中表示它就是主键，并且它默认就是自增的
    @Id(autoincrement = true)
    public Long id;

    @NotNull
    public String channelId;

    @NotNull
    public String channelName;

    @Generated(hash = 1167317767)
    public NewsChannel(Long id, @NotNull String channelId,
            @NotNull String channelName) {
        this.id = id;
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Generated(hash = 566079451)
    public NewsChannel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    
}
