package com.example.base.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.example.base.R;
import com.example.base.utils.BindEventBus;
import com.gyf.immersionbar.ImmersionBar;
import com.trello.navi2.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import org.greenrobot.eventbus.EventBus;


/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseActivity
 */
public abstract class BaseActivity<P extends IBasePresenter> extends NaviAppCompatActivity implements IBaseView<P> {


    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onSetPresenter(mPresenter);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(getLayoutId());

        registEventBus();
        initData();
        initView();
        //设置状态栏颜色
        initStatusBarColor();
        initEvent();
    }

    protected void initStatusBarColor() {
        //设置状态栏的颜色
        ImmersionBar.with(this).statusBarColor(R.color.theme_color).init();
    }

    private void registEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public LifecycleProvider<ActivityEvent> autoRxLifeCycle() {
        //返回Rxlifecycler的provider对象
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

    protected abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView() {
    }

    protected void initEvent() {
    }


}
