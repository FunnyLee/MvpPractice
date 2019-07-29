package com.example.base.base;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseActivity
 */
public abstract class BaseMvpActivity<P extends IBasePresenter> extends BaseActivity implements IBaseView<P> {

    protected P mPresenter;

    @Override
    protected void initPresenter() {
        onSetPresenter(mPresenter);
    }

}
