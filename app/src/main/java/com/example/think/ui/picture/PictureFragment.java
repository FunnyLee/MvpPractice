package com.example.think.ui.picture;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.think.R;
import com.example.think.base.BaseFragment;
import com.example.think.base.BaseViewFragment;
import com.example.think.bean.ChannelBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 图片
 */
public class PictureFragment extends BaseViewFragment {

    @BindView(R.id.tab_layout_news)
    TabLayout mTabLayoutNews;
    @BindView(R.id.view_pager_news)
    ViewPager mViewPagerNews;
    private List<BaseFragment> mFragmentList;
    private List<ChannelBean> mPicChannelList;
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
    protected void initData() {
        String[] pictureNames = mContext.getResources().getStringArray(R.array.picture_name);
        String[] pictureIds = mContext.getResources().getStringArray(R.array.picture_id);

        mFragmentList = new ArrayList<>();
        mPicChannelList = new ArrayList();
        mTitleList = new ArrayList<>();

        for (int i = 0; i < pictureNames.length; i++) {
            ChannelBean channelBean = new ChannelBean();
            channelBean.channelName = pictureNames[i];
            channelBean.channelId = pictureIds[i];
            mPicChannelList.add(channelBean);
        }

        for (ChannelBean channelBean : mPicChannelList) {
            mTitleList.add(channelBean.channelName);
            BaseFragment fragment = PictureArticleFragment.newInstance();
            mFragmentList.add(fragment);
        }
    }

    @Override
    protected void initView(View view) {

    }
}
