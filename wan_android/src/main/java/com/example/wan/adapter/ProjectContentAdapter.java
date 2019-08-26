package com.example.wan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wan.entity.ProjectContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/26
 * Description: This is 项目内容Adapter
 */
public class ProjectContentAdapter extends BaseQuickAdapter<ProjectContentInfo.DatasInfo, BaseViewHolder> {

    public ProjectContentAdapter(int layoutResId, @Nullable List<ProjectContentInfo.DatasInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectContentInfo.DatasInfo item) {

    }
}
