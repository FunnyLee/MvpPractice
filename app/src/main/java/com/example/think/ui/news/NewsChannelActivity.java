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

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        List<NewsChannel> newsChannels = NewsDao.queryAll();
        Observable.fromIterable(newsChannels)
                .map(new Function<NewsChannel, List<ChannelUIBean>>() {
                    @Override
                    public List<ChannelUIBean> apply(NewsChannel newsChannel) throws Exception {
                        List<ChannelUIBean> list = new ArrayList<>();
                        list.add(new ChannelUIBean().buildMyChannel(newsChannel));
                        return list;
                    }
                })
                .subscribe(new Consumer<List<ChannelUIBean>>() {
                    @Override
                    public void accept(List<ChannelUIBean> channelUIBeans) throws Exception {
                        mDatas = new ArrayList<>();
                        //channelUIBeans.add((int) channelUIBeans.size() % 2 - 1, new ChannelUIBean().buildHideChannerlHeader());
                        mDatas.addAll(channelUIBeans);
                        setRecyclerView();
                    }
                });

    }

    private void setRecyclerView() {
        mDatas.add(1, new ChannelUIBean().buildMyChannelHeader());
        NewsChannelAdapter adapter = new NewsChannelAdapter(mDatas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mDatas.get(position).spanSize;
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
