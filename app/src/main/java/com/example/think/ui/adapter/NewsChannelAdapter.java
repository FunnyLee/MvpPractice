package com.example.think.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.think.Constants;
import com.example.think.R;
import com.example.think.bean.ChannelUIBean;
import com.example.think.ui.news.NewsChannelActivity;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Author: Funny
 * Time: 2018/9/30
 * Description: This is NewsChannleAdapter
 */
public class NewsChannelAdapter extends BaseQuickAdapter<ChannelUIBean, BaseViewHolder> {

    private NewsChannelActivity mActivity;

    private List<ChannelUIBean> mDatas;

    public NewsChannelAdapter(NewsChannelActivity activity, @Nullable List<ChannelUIBean> data) {
        super(data);
        this.mActivity = activity;
        this.mDatas = data;

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

    @SuppressLint("CheckResult")
    @Override
    protected void convert(BaseViewHolder helper, ChannelUIBean item) {
        switch (helper.getItemViewType()) {
            case ChannelUIBean.ITEM_MY_CHANNEL_HEADER:
                TextView tvBtnEdit = helper.getView(R.id.tv_edit);
                RxView.clicks(tvBtnEdit)
                        .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                        .subscribe(o -> {
                            if (item.isEditMode) {
                                tvBtnEdit.setText("编辑");
                                item.isEditMode = false;
                            } else {
                                tvBtnEdit.setText("完成");
                                item.isEditMode = true;
                            }

                            for (ChannelUIBean data : mDatas) {
                                data.isEditMode = item.isEditMode;
                                notifyDataSetChanged();
                            }
                        });
                break;
            case ChannelUIBean.ITEM_MY_CHANNEL:
                ImageView ivFork = helper.getView(R.id.iv_fork);
                if(item.isEditMode){
                    ivFork.setVisibility(View.VISIBLE);
                }else {
                    ivFork.setVisibility(View.INVISIBLE);
                }

                helper.setText(R.id.tv_channel_name, item.newChannel.channelName);
                break;
            case ChannelUIBean.ITEM_HIDE_CHANNEL:
                helper.setText(R.id.tv_channel_name, item.newChannel.channelName);
                break;
            default:
                break;
        }

    }

}
