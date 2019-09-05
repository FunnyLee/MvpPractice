package com.example.wan.api;

import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.ProjectCategoryInfo;
import com.example.wan.entity.ProjectContentInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is 项目模块api
 */
public interface IProjectApi {

    /**
     * 获取项目分类列表
     *
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseWanAndroidResponse<List<ProjectCategoryInfo>>> getProjectCategory();

    /**
     * 获取项目内容列表
     * @param page
     * @param cid
     * @return
     */
    @GET("project/list/{page}/json")
    Observable<BaseWanAndroidResponse<ProjectContentInfo>> getProjectContent(@Path("page") int page, @Query("cid") String cid);

}
