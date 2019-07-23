package com.example.base.net;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/7
 * Description: This is 网络请求的回调接口
 */
public interface NetCallBack<T> {

    void success(List<T> datas);

    void fail(Throwable throwable);

}
