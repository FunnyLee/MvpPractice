package com.example.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.base.utils.BindEventBus;
import com.trello.navi2.component.support.NaviFragment;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseFragment
 */
public abstract class BaseFragment<P extends IBasePresenter> extends NaviFragment implements IBaseView<P> {

    protected P mPresenter;

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSetPresenter(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);

        registEventBus();
        initData();
        initView(view);
        initEvent();
        return view;
    }

    private void registEventBus() {
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
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

    protected void initView(View view) {
    }

    protected void initEvent() {
    }

}
