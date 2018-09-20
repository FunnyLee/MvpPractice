package com.example.think.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.example.think.R;
import com.example.think.utils.BindEventBus;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseActivity
 */
public abstract class BaseActivity<P extends IBasePresenter> extends RxAppCompatActivity implements IBaseView<P> {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(mPresenter);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(getLayoutId());

        //设置状态栏颜色
        initStatusBarColor(setStatusBarColor());

        ButterKnife.bind(this);
        registEventBus();
        initData();
        initView();
        initEvent();
    }

    protected int setStatusBarColor() {
        return R.color.colorPrimary;
    }

    private void initStatusBarColor(int color) {
        //设置状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }
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

    protected abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView() {
    }

    protected void initEvent() {
    }


}
