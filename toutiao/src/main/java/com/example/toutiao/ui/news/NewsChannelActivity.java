package com.example.toutiao.ui.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.base.ViewActivity;
import com.example.base.greendao.daoManager.NewsDao;
import com.example.base.greendao.entity.NewsChannel;
import com.example.base.router.RouterManager;
import com.example.toutiao.R;
import com.example.toutiao.bean.ChannelUIBean;
import com.example.toutiao.ui.adapter.NewsChannelAdapter;
import com.example.toutiao.widget.ItemTouchCallback;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 选择频道页面
 */

@Route(path = RouterManager.NEWS_CHANNEL_ACTIVITY)
public class NewsChannelActivity extends ViewActivity {

    Toolbar mToolbar;
    AppBarLayout mAppBarLayout01;
    RecyclerView mRecyclerView;

    private List<ChannelUIBean> mDatas;
    private NewsChannelAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, NewsChannelActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_channel;
    }

    @Override
    protected void initStatusBarColor() {
        ImmersionBar.with(this).statusBarColor(R.color.theme_color).init();
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mAppBarLayout01 = findViewById(R.id.AppBarLayout01);
        mRecyclerView = findViewById(R.id.recycler_view);

        //设置ToolBar
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(new ChannelUIBean().buildMyChannelHeader());
        Observable.fromIterable(NewsDao.queryIsShowChannel(true))
                .map(new Function<NewsChannel, ChannelUIBean>() {
                    @Override
                    public ChannelUIBean apply(NewsChannel newsChannel) throws Exception {
                        return new ChannelUIBean().buildMyChannel(newsChannel);
                    }
                })
                .toList()
                .subscribe(new Consumer<List<ChannelUIBean>>() {
                    @Override
                    public void accept(List<ChannelUIBean> channelUIBeans) throws Exception {
                        mDatas.addAll(channelUIBeans);
                    }
                });

        mDatas.add(new ChannelUIBean().buildHideChannerlHeader());
        Observable.fromIterable(NewsDao.queryIsShowChannel(false))
                .map(new Function<NewsChannel, ChannelUIBean>() {
                    @Override
                    public ChannelUIBean apply(NewsChannel newsChannel) throws Exception {
                        return new ChannelUIBean().buildHideChannel(newsChannel);
                    }
                })
                .toList()
                .subscribe(new Consumer<List<ChannelUIBean>>() {
                    @Override
                    public void accept(List<ChannelUIBean> channelUIBeans) throws Exception {
                        mDatas.addAll(channelUIBeans);
                    }
                });

        setRecyclerView();
    }

    private void setRecyclerView() {
        mAdapter = new NewsChannelAdapter(this, mDatas, null);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDatas.get(position).spanSize;
            }
        });

        //RecyclerView拖拽滑动
        ItemTouchCallback<ChannelUIBean> callback = new ItemTouchCallback(mDatas, mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
