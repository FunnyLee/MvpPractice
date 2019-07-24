package com.example.toutiao.ui.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.example.base.Constants;
import com.example.toutiao.R;
import com.example.toutiao.bean.ChannelUIBean;
import com.example.toutiao.ui.news.NewsChannelActivity;
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

    private TextView mTvChannelName;

    private ItemTouchHelper mItemTouchHelper;

    public NewsChannelAdapter(NewsChannelActivity activity, @Nullable List<ChannelUIBean> data,ItemTouchHelper itemTouchHelper) {
        super(data);
        this.mActivity = activity;
        this.mDatas = data;
        this.mItemTouchHelper = itemTouchHelper;

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
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {


        return super.onCreateDefViewHolder(parent, viewType);
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
                mTvChannelName = helper.getView(R.id.tv_channel_name);
                if(item.isEditMode){
                    ivFork.setVisibility(View.VISIBLE);
                }else {
                    ivFork.setVisibility(View.INVISIBLE);
                }
                mTvChannelName.setText(item.newChannel.channelName);

                //长按
                RxView.longClicks(mTvChannelName)
                        .throttleFirst(Constants.CLICK_INTERVAL,TimeUnit.SECONDS)
                        .subscribe(o -> {

                        });

                break;
            case ChannelUIBean.ITEM_HIDE_CHANNEL:
                helper.setText(R.id.tv_channel_name, item.newChannel.channelName);
                break;
            default:
                break;
        }

    }

}
