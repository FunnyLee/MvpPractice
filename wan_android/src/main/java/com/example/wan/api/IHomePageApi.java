package com.example.wan.api;

import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.HomeArticleInfo;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author: Funny
 * Time: 2019/7/30
 * Description: This is 首页api
 */
public interface IHomePageApi {

    /**
     * 获取首页Banner图
     */
    @GET("banner/json")
    Observable<BaseWanAndroidResponse<List<HomeBannerInfo>>> getHomeBanner();

    /**
     * 获取首页文章列表
     *
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseWanAndroidResponse<HomeArticleInfo>> getHomeArticle(@Path("page") int page);

}
