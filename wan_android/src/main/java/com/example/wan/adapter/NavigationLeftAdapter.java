package com.example.wan.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wan.R;
import com.example.wan.entity.NavigationContentInfo;

import java.util.List;

import androidx.annotation.Nullable;

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
        TextView nameTv = helper.getView(R.id.name_tv);

        nameTv.setText(item.name);
        nameTv.setSelected(item.isSelected);

        if (helper.getLayoutPosition() == 0) {
            divideView.setVisibility(View.GONE);
        } else {
            divideView.setVisibility(View.VISIBLE);
        }
    }
}
