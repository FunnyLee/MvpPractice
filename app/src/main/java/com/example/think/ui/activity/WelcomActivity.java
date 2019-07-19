package com.example.think.ui.activity;

import android.annotation.SuppressLint;

import com.example.think.R;
import com.example.think.base.ViewActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WelcomActivity extends ViewActivity {

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
                        MainActivity.start(WelcomActivity.this);
                        finish();
                    }
                });
    }
}
