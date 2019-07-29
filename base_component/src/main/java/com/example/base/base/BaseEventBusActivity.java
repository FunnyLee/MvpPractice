package com.example.base.base;

import android.os.Bundle;

import com.example.base.utils.BindEventBus;
import com.trello.navi2.component.support.NaviAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is 封装了EventBus
 */
public abstract class BaseEventBusActivity extends NaviAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注册EventBus
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
}
