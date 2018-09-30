package com.example.think.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.example.think.R;

/**
 * Author: Funny
 * Time: 2018/9/29
 * Description: This is StatusBarUtil
 */
public class StatusBarUtil {

    public static void setStatusBarColor(Activity context, int Color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(context.getResources().getColor(R.color.transparent));
        }
    }

}
