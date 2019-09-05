package com.example.wan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
        helper.setText(R.id.name_tv, item.name);
    }
}
