package com.example.toutiao.api;


import com.example.toutiao.entity.news.MultiNewsArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Author: Funny
 * Time: 2018/8/27
 * Description: This is IMobileNewsApi
 */
public interface IMobileNewsApi {

//
//    获取个性化新闻
//    深圳 http://is.snssdk.com/api/news/feed/v58/?iid=5034850950&device_id=6096495334&category=news_society
//    深圳 http://lf.snssdk.com/api/news/feed/v58/?iid=12507202490&device_id=37487219424&category=news_society
//    天津 http://ib.snssdk.com/api/news/feed/v58/?
//    北京 http://iu.snssdk.com/api/news/feed/v58/?

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticleOne(@Query("category") String category,
                                                       @Query("max_behot_time") String maxBehotTime);

    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getNewsArticleTwo(@Query("category") String category,
                                                       @Query("max_behot_time") String maxBehotTime);
}
