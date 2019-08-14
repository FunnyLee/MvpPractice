package com.example.toutiao.ui.video;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.greendao.daoManager.VideoDao;
import com.example.base.greendao.entity.VideoChannel;
import com.example.toutiao.R;
import com.example.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 视频
 */
public class VideoFragment extends BaseFragment {

    TabLayout mTabLayoutVideo;
    ViewPager mViewPagerVideo;
    private List<BaseMvpFragment> mFragmentList;
    private List<String> mTitleList;

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View view) {
        mTabLayoutVideo =  findViewById(R.id.tab_layout_video);
        mViewPagerVideo =  findViewById(R.id.view_pager_video);
    }

    @Override
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        List<VideoChannel> videoChannels = VideoDao.queryAll();
        for (VideoChannel channelBean : videoChannels) {
            mTitleList.add(channelBean.channelName);
            VideoArticleFragment fragment = VideoArticleFragment.newInstance(channelBean.channelId);
            mFragmentList.add(fragment);
        }

        mTabLayoutVideo.setupWithViewPager(mViewPagerVideo);
        mTabLayoutVideo.setTabMode(TabLayout.MODE_SCROLLABLE);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getFragmentManager(), mFragmentList, mTitleList);
        mViewPagerVideo.setAdapter(adapter);
    }
}
