package com.example.wan;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseActivity;
import com.example.base.router.RouterManager;

@Route(path = RouterManager.WAN_ANDROID_ACTIVITY)
public class WanAndroidActivity extends BaseActivity<IWanAndroidContract.Presenter> implements IWanAndroidContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_android;
    }

    @Override
    public void onSetPresenter(IWanAndroidContract.Presenter presenter) {
        if(mPresenter == null){
            mPresenter = new WanAndroidPresenter();
        }
    }

    @Override
    public void setBanner() {

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


}
