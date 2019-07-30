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
 * Description: This is BannerBgAdapter
 */
public class BannerBgAdapter extends BaseQuickAdapter<HomeBannerInfo, BaseViewHolder> {

    public BannerBgAdapter(int layoutResId, @Nullable List<HomeBannerInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBannerInfo item) {
        ImageView bgIv = helper.getView(R.id.bg_iv);
        ImageHelper.loadBlurryImage(mContext, item.imagePath, bgIv);
    }
}
