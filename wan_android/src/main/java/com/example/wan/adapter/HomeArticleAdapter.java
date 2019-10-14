package com.example.wan.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.entity.HomeArticleInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/1
 * Description: This is HomeArticleAdapter
 */
public class HomeArticleAdapter extends BaseQuickAdapter<HomeArticleInfo.DatasInfo, BaseViewHolder> {

    public HomeArticleAdapter(int layoutResId, @Nullable List<HomeArticleInfo.DatasInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleInfo.DatasInfo item) {
        helper.setText(R.id.title_tv, item.title)
                .setText(R.id.author_tv, "作者：" + item.author)
                .setText(R.id.category_tv, "分类：" + item.chapterName)
                .setText(R.id.time_tv, item.niceDate);

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
