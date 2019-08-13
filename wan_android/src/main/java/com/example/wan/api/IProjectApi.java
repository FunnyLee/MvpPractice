package com.example.wan.api;

import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.ProjectCategoryInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is 项目api
 */
public interface IProjectApi {

    /**
     * 获取项目分类列表
     * @return
     */
    @GET("project/tree/json")
    Observable<BaseWanAndroidResponse<List<ProjectCategoryInfo>>> getProjectCategory();

}
