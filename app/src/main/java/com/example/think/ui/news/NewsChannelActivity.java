package com.example.think.ui.news;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.think.R;
import com.example.think.base.ViewActivity;

import butterknife.BindView;

public class NewsChannelActivity extends ViewActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout mAppBarLayout01;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, NewsChannelActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_channel;
    }

    @Override
    protected void initView() {
        //设置ToolBar
        setSupportActionBar(mToolbar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
