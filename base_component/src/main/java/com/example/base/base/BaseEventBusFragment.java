package com.example.base.base;

import android.os.Bundle;

import com.example.base.utils.BindEventBus;
import com.trello.navi2.component.support.NaviFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is BaseEventBusFragment
 */
public abstract class BaseEventBusFragment extends NaviFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册EventBus
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
}
