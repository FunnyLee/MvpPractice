package com.example.think.ui.activity;

import com.example.think.R;
import com.example.think.base.ViewActivity;

public class WelcomActivity extends ViewActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected int setStatusBarColor() {
        return R.color.statusBarColor;
    }
}
