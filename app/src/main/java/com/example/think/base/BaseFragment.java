package com.example.think.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.utils.BindEventBus;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseFragment
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView<P> {

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
        setPresenter(mPresenter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    protected abstract int getLayoutId();

    protected void initData() {
    }

    protected void initView(View view) {
    }

    protected void initEvent() {
    }

}
