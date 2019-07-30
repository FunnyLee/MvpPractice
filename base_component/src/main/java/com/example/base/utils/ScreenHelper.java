package com.example.base.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.base.AppManager;

import java.lang.reflect.Field;

/**
 * 屏幕信息获取工具类
 * Created by lewis.zhou on 2017/6/8.
 */

public class ScreenHelper {
    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变 n
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取设备屏幕密度
     */
    public static float getDisplayDensity(Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    public static float getScaledDensity(Context context) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return fontScale;
    }

    public static float getDensityDpi(Context context) {
        float densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return densityDpi;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        return screenHeight;
    }

//    public static int getStatusBarHeight(Activity activity) {
//        Rect outRect = new Rect();
//        activity.getWindow().getDecorView()
//                .getWindowVisibleDisplayFrame(outRect);
//        return outRect.top;
//    }

    public static void printSreenInfo(Context ctx) {
        Log.w("ScreenHelper", "screenWidtdh:" + getScreenWidth(ctx) + ", screenHeight:"
                + getScreenHeight(ctx) + ", statusBarHeight:"
                + getStatusBarHeight(ctx) + ", densityDpi:"
                + getDensityDpi(ctx) + ", density:" + getDisplayDensity(ctx)
                + ", scaledDensity:" + getScaledDensity(ctx));
    }

    public static int getAppAreaHeight(Context context) {
        return getScreenHeight(context) - getStatusBarHeight(context);
    }

    public static int getAppAreaWidth(Context context) {
        return getScreenWidth(context);
    }

    public static void setMarginLeft(View view, int x) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(x, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginRight(View view, int x) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(0, 0, x, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginTop(View view, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(0, y, 0, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setMarginBottom(View view, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(0, 0, 0, y);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setLayoutByXY(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(
                view.getLayoutParams());
        margin.setMargins(x, y, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                margin);
        view.setLayoutParams(layoutParams);
    }

    public static void setViewParentAlign(Context context, View view, int verb) {
        RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lpp.addRule(verb, RelativeLayout.TRUE);
        lpp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpp.setMargins(0, ScreenHelper.dp2px(context, 20), 0, 0);
        view.setLayoutParams(lpp);
    }

    public static int getHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }

    public static int getWidht(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

    public static void setComponentWH(View view, float w, float h,
                                      int screenWidth, int screenHight) {
        view.setLayoutParams(new LinearLayout.LayoutParams((int) (screenWidth
                * w + 0.5f), (int) (screenHight * h + 0.5f)));
    }

    /**
     * 设置通知栏透明
     *
     * @param activity
     */
    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); 虚拟返回键是否透明
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 已知系统类型时，设置状态栏黑色文字、图标
     * 6.0以上版本其他Android
     *
     * @param activity
     */
    public static void setStatusBarLightMode(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    /**
     * 清除状态栏黑色文字、图标
     */
    public static void setStatusBarDarkMode(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
    }

    public static void setNavigationBarColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(color);
        }
    }

    /**
     * 获取通知栏的高度  如果系统低于4.4则返回0
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return 0;
        }
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    public static int getDimensionPixelOffset(@DimenRes int resId) {
        return AppManager.getAppManagerContext().getResources().getDimensionPixelOffset(resId);
    }

    public static int getColor(@ColorRes int resId) {
        return AppManager.getAppManagerContext().getResources().getColor(resId);
    }
}
