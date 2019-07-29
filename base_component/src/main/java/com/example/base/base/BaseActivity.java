package com.example.base.base;

import android.os.Bundle;

import com.example.base.R;
import com.gyf.immersionbar.ImmersionBar;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is BaseTestActivity
 */
public abstract class BaseActivity extends BaseEventBusActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        initPresenter();
        //因为这三个方法中，可能会用到presenter对象
        //所以需要控制一定要在initPresenter方法后执行

        initView();
        //设置状态栏颜色
        initStatusBarColor();
        initData();
        initEvent();
    }

    protected abstract int getLayoutId();

    protected void initPresenter() {
    }

    protected void initView() {
    }


    protected void initData() {
    }

    protected void initStatusBarColor() {
        //设置状态栏的颜色
        ImmersionBar.with(this).statusBarColor(R.color.theme_color).init();
    }

    protected void initEvent() {
    }

    public LifecycleProvider<ActivityEvent> autoRxLifeCycle() {
        //返回Rxlifecycler的provider对象
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }
}
