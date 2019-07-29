package com.example.toutiao.ui.activity;

import android.annotation.SuppressLint;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.base.BaseActivity;
import com.example.base.router.RouterManager;
import com.example.toutiao.R;
import com.gyf.immersionbar.ImmersionBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initStatusBarColor() {
        ImmersionBar.with(this).transparentStatusBar().init();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initEvent() {
        Observable.just("splash")
                .delay(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(autoRxLifeCycle().bindToLifecycle())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ARouter.getInstance().build(RouterManager.MAIN_ACTIVTY_URL).navigation();
                        finish();
                    }
                });
    }
}
