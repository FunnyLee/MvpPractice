package com.example.think.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is GreenDao的实体类
 */
@Entity
public class ChannelEntity {

    @Id(autoincrement = true)
    public Long id;

    @NotNull
    public String channelId;

    @NotNull
    public String channelName;

    @Generated(hash = 1739004396)
    public ChannelEntity(Long id, @NotNull String channelId,
            @NotNull String channelName) {
        this.id = id;
        this.channelId = channelId;
        this.channelName = channelName;
    }

    @Generated(hash = 781881457)
    public ChannelEntity() {
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
