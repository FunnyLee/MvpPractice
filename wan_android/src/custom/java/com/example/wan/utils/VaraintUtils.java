package com.example.wan.utils;

import android.widget.Toast;

import com.example.base.AppManager;

/**
 * Author: Funny
 * Time: 2019/10/16
 * Description: This is VaraintUtils
 */
public class VaraintUtils {

    public static void showMsg() {
        Toast.makeText(AppManager.getAppManagerContext(), "custom", Toast.LENGTH_SHORT).show();
    }

}
