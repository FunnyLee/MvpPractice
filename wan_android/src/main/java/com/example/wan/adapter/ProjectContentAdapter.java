package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.router.RouterManager;
import com.example.base.utils.ImageHelper;
import com.example.wan.R;
import com.example.wan.entity.ProjectContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/26
 * Description: This is 项目内容Adapter
 */
public class ProjectContentAdapter extends BaseQuickAdapter<ProjectContentInfo.DatasInfo, BaseViewHolder> {

    public ProjectContentAdapter(@Nullable List<ProjectContentInfo.DatasInfo> data) {
        super(R.layout.item_project_content, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectContentInfo.DatasInfo item) {
        ImageView picIv = helper.getView(R.id.pic_iv);
        ImageHelper.loadBgImage(mContext, item.envelopePic, picIv);
        helper.setText(R.id.author_tv, item.author)
                .setText(R.id.title_tv, item.title)
                .setText(R.id.date_tv, item.niceDate)
                .setText(R.id.desc_tv, item.desc)
                .setText(R.id.type_tv, item.superChapterName + "/" + item.chapterName);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build(RouterManager.ARTICLE_WEBVIEW_ACTIVITY)
                        .withString("link", item.link)
                        .withString("title", item.title)
                        .navigation();
            }
        });
    }
}
