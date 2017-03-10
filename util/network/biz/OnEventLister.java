package com.wsywddr.sample.util.network.biz;

/**
 * Created by PandaQ on 2016/10/19.
 * email : 767807368@qq.com
 */

public interface OnEventLister<T> {
    void onSuccess(T response);

    void onFail(String errCode, String errMsg);
}
