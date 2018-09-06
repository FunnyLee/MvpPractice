package com.example.think.ui.channel;

import com.example.think.R;
import com.example.think.base.BaseListFragment;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is 头条号
 */
public class ChannelFragment extends BaseListFragment {

    public static ChannelFragment newInstance() {
        ChannelFragment fragment = new ChannelFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_channel;
    }

    @Override
    public void onSetAdapter(List list) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
