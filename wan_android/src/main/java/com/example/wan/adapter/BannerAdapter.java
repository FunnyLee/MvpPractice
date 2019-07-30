package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.utils.ImageHelper;
import com.example.wan.R;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/7/30
 * Description: This is BannerAdapter
 */
public class BannerAdapter extends BaseQuickAdapter<HomeBannerInfo, BaseViewHolder> {

    public BannerAdapter(int layoutResId, @Nullable List<HomeBannerInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBannerInfo item) {
        ImageView picIv = helper.getView(R.id.pic_iv);
        ImageHelper.loadCenterCrop(mContext, item.imagePath, picIv);
    }
}
