package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wan.R;
import com.example.wan.entity.BannerIndicatorInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/2
 * Description: This is BannerIndicatorAdapter
 */
public class BannerIndicatorAdapter extends BaseQuickAdapter<BannerIndicatorInfo, BaseViewHolder> {

    public BannerIndicatorAdapter(int layoutResId, @Nullable List<BannerIndicatorInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BannerIndicatorInfo item) {
        ImageView indicatorIv = helper.getView(R.id.indicator_iv);
        if(item.isSelected){
            indicatorIv.setBackgroundResource(R.drawable.indicator_point_white_shape);
        }else {
            indicatorIv.setBackgroundResource(R.drawable.indicator_point_grey_shape);
        }
    }

//    @Override
//    public int getItemCount() {
//        return Integer.MAX_VALUE;
//    }
//
//    @Nullable
//    @Override
//    public BannerIndicatorInfo getItem(int position) {
//        int newPosition = position % getData().size();
//        return getData().get(newPosition);
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        int count = getHeaderLayoutCount() + getData().size();
//        //刚开始进入包含该类的activity时,count为0。就会出现0%0的情况，这会抛出异常，所以我们要在下面做一下判断
//        if (count <= 0) {
//            count = 1;
//        }
//        int newPosition = position % count;
//        return super.getItemViewType(newPosition);
//    }
}
