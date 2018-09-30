package com.example.think.ui.news;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.think.R;
import com.example.think.base.ViewActivity;
import com.example.think.database.ChannelEntity;
import com.example.think.greendao.GreenDaoManager;

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
        String[] channelNames = getResources().getStringArray(R.array.mobile_news_name);
        String[] channelIds = getResources().getStringArray(R.array.mobile_news_id);

        for (int i = 0; i < channelNames.length; i++) {
            ChannelEntity entity = new ChannelEntity(null, channelIds[i], channelNames[i]);
            //存入数据库
            GreenDaoManager.getInstance().insert(entity);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
