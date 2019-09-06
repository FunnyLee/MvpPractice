package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wan.R;
import com.example.wan.entity.NavigationContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/6
 * Description: This is NavigationLeftAdapter
 */
public class NavigationLeftAdapter extends BaseQuickAdapter<NavigationContentInfo, BaseViewHolder> {

    public NavigationLeftAdapter(@Nullable List<NavigationContentInfo> data) {
        super(R.layout.item_navigation_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationContentInfo item) {
        View divideView = helper.getView(R.id.divide_view);
        helper.setText(R.id.name_tv, item.name);

        if (helper.getLayoutPosition() == 0) {
            divideView.setVisibility(View.GONE);
        } else {
            divideView.setVisibility(View.VISIBLE);
        }
    }
}
