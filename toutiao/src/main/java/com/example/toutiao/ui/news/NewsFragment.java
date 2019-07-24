package com.example.toutiao.ui.news;

import android.annotation.SuppressLint;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.base.Constants;
import com.example.base.base.BaseFragment;
import com.example.base.base.ViewFragment;
import com.example.base.greendao.daoManager.NewsDao;
import com.example.base.greendao.entity.NewsChannel;
import com.example.toutiao.R;
import com.example.toutiao.ui.adapter.ViewPagerFragmentAdapter;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is AccountFragment
 */
public class NewsFragment extends ViewFragment {
    TabLayout mTabLayoutNews;
    ImageView mAddChannelIv;
    LinearLayout mHeaderLayout;
    ViewPager mViewPager;

    private List<String> mTitleList;
    private List<BaseFragment> mFragmentList;


    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        List<NewsChannel> channelEntities = NewsDao.queryIsShowChannel(true);

        for (NewsChannel channelEntity : channelEntities) {
            mTitleList.add(channelEntity.channelName);

            String channelId = channelEntity.channelId;

            BaseFragment fragment = null;

            if ("question_and_answer".equals(channelId)) {
                fragment = WendaArticleFragment.newInstance();
            } else {
                fragment = NewsArticleFragment.newInstance(channelId);
            }
            mFragmentList.add(fragment);
        }
    }

    @Override
    protected void initView(View view) {
        mTabLayoutNews = (TabLayout) findViewById(R.id.tab_layout_news);
        mAddChannelIv = (ImageView) findViewById(R.id.add_channel_iv);
        mHeaderLayout = (LinearLayout) findViewById(R.id.header_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_news);


        mTabLayoutNews.setupWithViewPager(mViewPager);
        mTabLayoutNews.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPagerFragmentAdapter fragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(fragmentAdapter);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        RxView.clicks(mAddChannelIv)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    NewsChannelActivity.start(mContext);
                });
    }
}