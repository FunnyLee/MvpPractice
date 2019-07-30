package com.example.toutiao.ui.channel;

import com.example.base.base.BaseMvpFragment;
import com.example.toutiao.R;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is 头条号
 */
public class ChannelFragment extends BaseMvpFragment {

    public static ChannelFragment newInstance() {
        ChannelFragment fragment = new ChannelFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_channel;
    }


    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onSetPresenter(Object presenter) {

    }
}
