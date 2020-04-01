package com.example.wan.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.utils.ImageHelper;
import com.example.wan.R;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

import androidx.annotation.Nullable;

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

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Nullable
    @Override
    public HomeBannerInfo getItem(int position) {
        int newPosition = position % getData().size();
        return getData().get(newPosition);
    }

    @Override
    public int getItemViewType(int position) {
        int count = getHeaderLayoutCount() + getData().size();
        //刚开始进入包含该类的activity时,count为0。就会出现0%0的情况，这会抛出异常，所以我们要在下面做一下判断
        if (count <= 0) {
            count = 1;
        }
        int newPosition = position % count;
        return super.getItemViewType(newPosition);
    }
}
