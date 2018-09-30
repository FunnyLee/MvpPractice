package com.example.think.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.think.R;
import com.example.think.bean.ChannelUIBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is NewsChannleAdapter
 */
public class NewsChannelAdapter extends BaseQuickAdapter<ChannelUIBean, BaseViewHolder> {

    public NewsChannelAdapter(@Nullable List<ChannelUIBean> data) {
        super(data);

        setMultiTypeDelegate(new MultiTypeDelegate<ChannelUIBean>() {
            @Override
            protected int getItemType(ChannelUIBean channelBean) {
                return channelBean.itemType;
            }
        });

        getMultiTypeDelegate()
                .registerItemType(ChannelUIBean.ITEM_MY_CHANNEL_HEADER, R.layout.item_my_channel_header)
                .registerItemType(ChannelUIBean.ITEM_HIDE_CHANNEL_HEADER, R.layout.item_hide_channel_header)
                .registerItemType(ChannelUIBean.ITEM_MY_CHANNEL, R.layout.item_my_channel_view)
                .registerItemType(ChannelUIBean.ITEM_HIDE_CHANNEL, R.layout.item_hide_channel_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChannelUIBean item) {

    }
}
