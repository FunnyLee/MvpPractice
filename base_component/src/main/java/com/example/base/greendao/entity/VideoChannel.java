package com.example.base.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is VideoChannel
 */
@Entity
public class VideoChannel {

    @Id(autoincrement = true)
    public Long id;

    @NotNull
    @Unique
    public String channelName;

    @NotNull
    @Unique
    public String channelId;

    @Generated(hash = 1910739118)
    public VideoChannel(Long id, @NotNull String channelName,
            @NotNull String channelId) {
        this.id = id;
        this.channelName = channelName;
        this.channelId = channelId;
    }

    @Generated(hash = 261073973)
    public VideoChannel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
