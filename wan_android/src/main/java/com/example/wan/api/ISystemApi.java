package com.example.wan.api;

import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.SystemContentInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author: Funny
 * Time: 2019/9/5
 * Description: This is 系统模块api
 */
public interface ISystemApi {

    /**
     * 获取体系数据
     * @return
     */
    @GET("tree/json")
    Observable<BaseWanAndroidResponse<List<SystemContentInfo>>> getSystemContent();

}
