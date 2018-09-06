package com.example.think.ui.video;

import com.example.think.R;
import com.example.think.base.BaseListFragment;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 视频
 */
public class VideoFragment extends BaseListFragment {

    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void onSetAdapter(List list) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
