package com.example.wan.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is NavigationContentFragment
 */
@Route(path = RouterManager.NAVIGATION_CONTENT_RAGMENT)
public class NavigationContentFragment extends BaseMvpFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_content;
    }

    @Override
    public void onSetPresenter(Object presenter) {

    }
}
