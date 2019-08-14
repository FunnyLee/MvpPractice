package com.example.wan.ui;

import android.os.Bundle;

import com.example.base.base.BaseMvpFragment;
import com.example.wan.R;
import com.gyf.immersionbar.ImmersionBar;

/**
 * Author: Funny
 * Time: 2019/8/14
 * Description: This is ProjectContentFragment
 */
public class ProjectContentFragment extends BaseMvpFragment {

    public static ProjectContentFragment newInstance() {
        Bundle args = new Bundle();
        ProjectContentFragment fragment = new ProjectContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_content;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).statusBarColor(R.color.Red).init();
    }

    @Override
    public void onSetPresenter(Object presenter) {

    }
}
