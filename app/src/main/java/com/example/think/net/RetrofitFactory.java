package com.example.think.net;

import com.example.think.AppManager;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is RetrofitFactory
 */
public class RetrofitFactory {

    private static final String HOST = "http://toutiao.com/";

    private static volatile Retrofit sInstance;

    private RetrofitFactory() {
    }

    public static Retrofit getInstance() {
        if (sInstance == null) {
            synchronized (RetrofitFactory.class) {
                if (sInstance == null) {

                    //添加持久化cookie
                    ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppManager.getAppContext()));

                    OkHttpClient mClient = new OkHttpClient().newBuilder()
                            .cookieJar(cookieJar)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();

                    sInstance = new Retrofit.Builder()
                            .baseUrl(HOST)
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
