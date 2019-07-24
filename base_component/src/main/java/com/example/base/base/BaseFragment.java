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

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseFragment
 */
public abstract class BaseFragment<P extends IBasePresenter> extends NaviFragment implements IBaseView<P> {

    protected P mPresenter;

    protected Context mContext;
    private View mView;

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
        mView = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);

        registEventBus();
        initData();
        initView(mView);
        initEvent();
        return mView;
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

    /**
     * 查找控件
     *
     * @param viewId
     */
    public final <T extends View> T findViewById(int viewId) {
        if (null != mView) {
            return mView.findViewById(viewId);
        }
        return null;
    }
}
