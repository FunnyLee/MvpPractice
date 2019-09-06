package com.example.wan.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.contract.INavigationContract;
import com.example.wan.presenter.NavigationPresenter;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is NavigationContentFragment
 */
@Route(path = RouterManager.NAVIGATION_CONTENT_RAGMENT)
public class NavigationContentFragment extends BaseMvpFragment<INavigationContract.Presenter> implements INavigationContract.View {

    @Override
    public void onSetPresenter(INavigationContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new NavigationPresenter(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_content;
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        mPresenter.doLoadNavigation();
    }

    @Override
    public void onShowContentView() {

    }


}
