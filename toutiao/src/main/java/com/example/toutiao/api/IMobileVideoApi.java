package com.example.toutiao.api;

import com.example.toutiao.bean.news.MultiNewsArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is IMobileVideoApi
 */
public interface IMobileVideoApi {

    /**
     * 获取视频标题等信息
     * http://is.snssdk.com/api/news/feed/v53/?category=subv_cute&refer=1&count=20&max_behot_time=1499321562&iid=11776029171&device_id=36394312781
     */
    @GET("http://is.snssdk.com/api/news/feed/v62/?iid=5034850950&device_id=6096495334&refer=1&count=20&aid=13")
    Observable<MultiNewsArticleBean> getVideoArticle(
            @Query("category") String category,
            @Query("max_behot_time") String maxBehotTime);

}
