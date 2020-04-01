package com.example.toutiao.ui.picture;

import android.view.View;

import com.example.base.adapter.ViewPagerFragmentAdapter;
import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.greendao.daoManager.PictureDao;
import com.example.base.greendao.entity.PictureChannel;
import com.example.toutiao.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 图片
 */
public class PictureFragment extends BaseFragment {

    TabLayout mTabLayoutNews;
    ViewPager mViewPager;
    private List<BaseMvpFragment> mFragmentList;
    private List<String> mTitleList;

    public static PictureFragment newInstance() {
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initView(View view) {
        mTabLayoutNews = (TabLayout) findViewById(R.id.tab_layout_news);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_news);
    }

    @Override
    protected void initData() {
//        String[] pictureNames = mContext.getResources().getStringArray(R.array.picture_name);
//        String[] pictureIds = mContext.getResources().getStringArray(R.array.picture_id);
//
//        mFragmentList = new ArrayList<>();
//        mPicChannelList = new ArrayList();
//        mTitleList = new ArrayList<>();
//
//        for (int i = 0; i < pictureNames.length; i++) {
//            ChannelBean channelBean = new ChannelBean();
//            channelBean.channelName = pictureNames[i];
//            channelBean.channelId = pictureIds[i];
//            mPicChannelList.add(channelBean);
//        }

//        for (ChannelBean channelBean : mPicChannelList) {
//            mTitleList.add(channelBean.channelName);
//            BaseFragment fragment = PictureArticleFragment.newInstance(channelBean.channelId);
//            mFragmentList.add(fragment);
//        }




        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        List<PictureChannel> pictureChannels2 = PictureDao.queryAll();
        for (PictureChannel channelBean : pictureChannels2) {
            mTitleList.add(channelBean.channelName);
            BaseMvpFragment fragment = PictureArticleFragment.newInstance(channelBean.channelId);
            mFragmentList.add(fragment);
        }

        mTabLayoutNews.setupWithViewPager(mViewPager);
        mTabLayoutNews.setTabMode(TabLayout.MODE_FIXED);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(adapter);
    }

}
