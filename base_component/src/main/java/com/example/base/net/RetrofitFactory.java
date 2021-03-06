package com.example.base.net;

import android.util.Log;

import com.example.base.AppManager;
import com.example.base.BuildConfig;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.Logger;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is RetrofitFactory
 */
public class RetrofitFactory {

    // TODO: 2019/7/30 base_url待修改
    private static final String BASE_URL = "https://www.wanandroid.com/";

    private static volatile Retrofit sInstance;

    private RetrofitFactory() {
    }

    public static Retrofit getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (sInstance == null) {

                    //添加持久化cookie
                    ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppManager.getAppManagerContext()));

                    //日志拦截器
                    LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                            .loggable(BuildConfig.DEBUG)
                            .setLevel(Level.BASIC)
                            .log(Platform.INFO)
                            .request("Request")
                            .response("Response")
                            .addHeader("version", BuildConfig.VERSION_NAME)
                            .addQueryParam("query", "0")
                            .enableAndroidStudio_v3_LogsHack(true) /* enable fix for logCat logging issues with pretty format */
                            .logger(new Logger() {
                                @Override
                                public void log(int level, String tag, String msg) {
                                    Log.w(tag, msg);
                                }
                            })
                            .executor(Executors.newSingleThreadExecutor())
                            .build();

                    OkHttpClient mClient = new OkHttpClient().newBuilder()
                            .addInterceptor(loggingInterceptor)
                            .cookieJar(cookieJar)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();

                    sInstance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(mClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }
        return sInstance;
    }
}
