package com.example.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Funny
 * Time: 2018/9/5
 * Description: This is BaseViewFragment，BaseFragment
 */
public abstract class BaseFragment extends BaseEventBusFragment {
    protected Context mContext;
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        initData();
        initView(mView);
        initEvent();
        return mView;
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
