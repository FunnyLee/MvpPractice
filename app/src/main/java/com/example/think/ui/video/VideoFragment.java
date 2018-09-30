package com.example.think.ui.video;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.think.R;
import com.example.think.base.BaseFragment;
import com.example.think.base.ViewFragment;
import com.example.think.greendao.DaoManager.VideoDao;
import com.example.think.greendao.entity.VideoChannel;
import com.example.think.ui.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 视频
 */
public class VideoFragment extends ViewFragment {

    @BindView(R.id.tab_layout_video)
    TabLayout mTabLayoutVideo;
    @BindView(R.id.view_pager_video)
    ViewPager mViewPagerVideo;
    private List<BaseFragment> mFragmentList;
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
    protected void initData() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        List<VideoChannel> videoChannels = VideoDao.queryAll();
        for (VideoChannel channelBean : videoChannels) {
            mTitleList.add(channelBean.channelName);
            VideoArticleFragment fragment = VideoArticleFragment.newInstance(channelBean.channelId);
            mFragmentList.add(fragment);
        }

    }

    @Override
    protected void initView(View view) {
        mTabLayoutVideo.setupWithViewPager(mViewPagerVideo);
        mTabLayoutVideo.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getFragmentManager(), mFragmentList, mTitleList);
        mViewPagerVideo.setAdapter(adapter);
    }
}
