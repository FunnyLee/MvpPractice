package com.example.think.bean;

import com.example.think.greendao.entity.NewsChannel;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is ChannelBean
 */
public class ChannelUIBean {

    public static final int ITEM_MY_CHANNEL_HEADER = 1;
    public static final int ITEM_HIDE_CHANNEL_HEADER = 2;
    public static final int ITEM_MY_CHANNEL = 3;
    public static final int ITEM_HIDE_CHANNEL = 4;

    public static final int SPAN_HEADER = 4;
    public static final int SPAN_CHANNEL = 1;

    //条目类型
    public int itemType;
    //每个条目占宽度的几份
    public int spanSize;

    public NewsChannel newChannel;

    public ChannelUIBean buildMyChannelHeader() {
        this.spanSize = SPAN_HEADER;
        this.itemType = ITEM_MY_CHANNEL_HEADER;
        return this;
    }

    public ChannelUIBean buildHideChannerlHeader() {
        this.spanSize = SPAN_HEADER;
        this.itemType = ITEM_HIDE_CHANNEL_HEADER;
        return this;
    }

    public ChannelUIBean buildMyChannel(NewsChannel newChannel) {
        this.spanSize = SPAN_CHANNEL;
        this.itemType = ITEM_MY_CHANNEL;
        this.newChannel = newChannel;
        return this;
    }

    public ChannelUIBean buildHideChannel(NewsChannel newChannel){
        this.spanSize = SPAN_CHANNEL;
        this.itemType = ITEM_HIDE_CHANNEL;
        this.newChannel = newChannel;
        return this;
    }
}
