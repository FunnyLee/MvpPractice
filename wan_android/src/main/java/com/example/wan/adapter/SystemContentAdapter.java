package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.widget.FlowLayout;
import com.example.wan.R;
import com.example.wan.entity.SystemContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/5
 * Description: This is SystemAdapter
 */
public class SystemContentAdapter extends BaseQuickAdapter<SystemContentInfo, BaseViewHolder> {

    public SystemContentAdapter(@Nullable List<SystemContentInfo> data) {
        super(R.layout.item_system_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemContentInfo item) {

        FlowLayout flowLayout = helper.getView(R.id.flow_layout);

        List<SystemContentInfo.Childreninfo> childrens = item.children;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 30, 10);
        //添加之前一定要先清空所有子view
        flowLayout.removeAllViews();
        for (SystemContentInfo.Childreninfo children : childrens) {
            TextView tv = new TextView(mContext);
            tv.setPadding(5, 5, 5, 5);
            tv.setBackgroundResource(R.drawable.text_view_bg_shape);
            tv.setLayoutParams(layoutParams);
            tv.setTextSize(13);
            tv.setText(children.name);
            flowLayout.addView(tv);
        }

        helper.setText(R.id.name_tv, item.name);
    }
}
