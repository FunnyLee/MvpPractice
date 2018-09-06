package com.example.think.ui.news;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.think.R;
import com.example.think.base.BaseFragment;
import com.example.think.base.BaseViewFragment;
import com.example.think.bean.ChannelBean;
import com.example.think.ui.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is AccountFragment
 */
public class NewsFragment extends BaseViewFragment {
    @BindView(R.id.tab_layout_news)
    TabLayout mTabLayoutNews;
    @BindView(R.id.add_channel_iv)
    ImageView mAddChannelIv;
    @BindView(R.id.header_layout)
    LinearLayout mHeaderLayout;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPager;
    private List<String> mTitleList;
    private List<ChannelBean> mNewsChannelList;
    //这个Map主要是为了缓存已创建的Fragment
    private Map<String, BaseFragment> mFragmentMap = new HashMap<>();
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
        String[] newsNames = mContext.getResources().getStringArray(R.array.mobile_news_name);
        String[] newsIds = mContext.getResources().getStringArray(R.array.mobile_news_id);

        mNewsChannelList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();

        for (int i = 0; i < newsNames.length; i++) {
            ChannelBean bean = new ChannelBean();
            bean.channelId = newsIds[i];
            bean.channelName = newsNames[i];
            mNewsChannelList.add(bean);
        }

        for (ChannelBean newsChannelBean : mNewsChannelList) {
            mTitleList.add(newsChannelBean.channelName);

            String channelId = newsChannelBean.channelId;

            BaseFragment fragment = null;

            if ("question_and_answer".equals(channelId)) {
                if (mFragmentMap.containsKey(channelId)) {
                    fragment = mFragmentMap.get(channelId);
                } else {
                    fragment = WendaArticleFragment.newInstance();
                }

            } else {
                if (mFragmentMap.containsKey(channelId)) {
                    fragment = mFragmentMap.get(channelId);
                } else {
                    fragment = NewsArticleFragment.newInstance(channelId);
                }
            }

            if (fragment != null) {
                mFragmentMap.put(channelId, fragment);
            }
        }
        mFragmentList.addAll(mFragmentMap.values());
    }

    @Override
    protected void initView(View view) {
        mTabLayoutNews.setupWithViewPager(mViewPager);
        mTabLayoutNews.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPagerFragmentAdapter fragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(fragmentAdapter);
    }
}
