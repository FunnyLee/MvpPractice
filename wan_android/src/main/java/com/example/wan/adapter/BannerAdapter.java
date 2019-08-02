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

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    @Nullable
    @Override
    public HomeBannerInfo getItem(int position) {
        //在这里处理postion，返回的对象就是convert方法中的对象
        int newPosition = position % getData().size();
        return getData().get(newPosition);
    }

    @Override
    public int getItemViewType(int position) {
        //处理条目类型
        int count = getHeaderLayoutCount() + getData().size();
        //刚开始进入包含该类的activity时,count为0。就会出现0%0的情况，这会抛出异常，所以我们要在下面做一下判断
        if (count <= 0) {
            count = 1;
        }
        int newPosition = position % count;
        return super.getItemViewType(newPosition);
    }
}
