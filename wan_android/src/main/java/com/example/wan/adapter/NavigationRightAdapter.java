package com.example.wan.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.widget.FlowLayout;
import com.example.wan.R;
import com.example.wan.entity.NavigationContentInfo;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * Author: Funny
 * Time: 2019/9/6
 * Description: This is NavigationRightAdapter
 */
public class NavigationRightAdapter extends BaseQuickAdapter<NavigationContentInfo, BaseViewHolder> {

    public NavigationRightAdapter(@Nullable List<NavigationContentInfo> data) {
        super(R.layout.item_navigation_right, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, NavigationContentInfo item) {
        View divideView = helper.getView(R.id.divide_view);
        TextView nameTv = helper.getView(R.id.name_tv);
        FlowLayout flowLayout = helper.getView(R.id.flow_layout);

        nameTv.setText(item.name);

        List<NavigationContentInfo.Articlesinfo> articles = item.articles;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 30, 10);
        //添加之前一定要先清空所有子view
        flowLayout.removeAllViews();
        for (NavigationContentInfo.Articlesinfo article : articles) {
            TextView tv = new TextView(mContext);
            tv.setPadding(5, 5, 5, 5);
            tv.setBackgroundResource(R.drawable.text_view_bg_shape);
            tv.setLayoutParams(layoutParams);
            tv.setTextSize(13);
            tv.setText(article.title);
            flowLayout.addView(tv);
        }

        if (helper.getLayoutPosition() == 0) {
            divideView.setVisibility(View.GONE);
        } else {
            divideView.setVisibility(View.VISIBLE);
        }
    }
}
