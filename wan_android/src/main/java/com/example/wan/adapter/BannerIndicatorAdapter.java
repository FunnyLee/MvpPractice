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
}
