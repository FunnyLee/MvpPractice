package com.example.base.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Meiji on 2017/5/31.
 */
@GlideModule
public class ImageHelper extends AppGlideModule {

    public static void loadCenterCrop(Context context, String url, ImageView view) {
//        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
//            view.setImageResource(defaultResId);
//        } else {
//            GlideApp.with(context)
//                    .load(url)
//                    .transition(withCrossFade())
//                    .apply(new RequestOptions().centerCrop())
//                    .into(view);
//        }
        Glide.with(context)
                .load(url)
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop())
                .into(view);
    }

    /**
     * 带加载异常图片
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, int defaultResId, int errorResId) {
//        if (SettingUtil.getInstance().getIsNoPhotoMode() && NetWorkUtil.isMobileConnected(context)) {
//            view.setImageResource(defaultResId);
//        } else {
//            GlideApp.with(context)
//                    .load(url)
//                    .transition(withCrossFade())
//                    .apply(new RequestOptions().centerCrop().error(errorResId))
//                    .into(view);
//        }

        Glide.with(context)
                .load(url)
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop().error(errorResId))
                .into(view);
    }

    /**
     * 带监听处理
     */
    public static void loadCenterCrop(Context context, String url, ImageView view, RequestListener listener) {
        Glide.with(context)
                .load(url)
                .transition(withCrossFade())
                .apply(new RequestOptions().centerCrop())
                .listener(listener)
                .into(view);
    }

    public static void loadNormal(Context context, String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }

    /**
     * 给LinearLayout,RelativeLayout等加载背景图片
     *
     * @param context
     * @param url
     * @param view
     */
    public static void loadBgImage(Context context, String url, View view) {
        Glide.with(context)
                .load(url)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        view.setBackground(resource);
                    }
                });
    }
}
