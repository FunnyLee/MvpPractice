package com.example.think.ui.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.think.R;
import com.example.think.base.ViewActivity;
import com.example.think.bean.ChannelUIBean;
import com.example.think.greendao.DaoManager.NewsDao;
import com.example.think.greendao.entity.NewsChannel;
import com.example.think.ui.adapter.NewsChannelAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class NewsChannelActivity extends ViewActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.AppBarLayout01)
    AppBarLayout mAppBarLayout01;
    @BindView(R.id.recycler_view)
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
    protected void initView() {
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
        mAdapter = new NewsChannelAdapter(this, mDatas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDatas.get(position).spanSize;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void change() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
