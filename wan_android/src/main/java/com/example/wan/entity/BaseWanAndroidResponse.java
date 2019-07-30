package com.example.wan.entity;

import java.io.Serializable;

/**
 * Author: Funny
 * Time: 2019/7/30
 * Description: This is BaseWanAndroidInfo
 */
public class BaseWanAndroidResponse<T> implements Serializable {

    public int errorCode;

    public String errorMsg;

    public T data;
}
