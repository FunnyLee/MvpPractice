package com.example.toutiao.api;

import com.example.toutiao.entity.news.NewsContentBean;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Author: Funny
 * Time: 2018/9/28
 * Description: This is INewsApi
 */
public interface INewsApi {

    @GET
    Observable<NewsContentBean> getNewsContent(@Url @NonNull String url);

}
