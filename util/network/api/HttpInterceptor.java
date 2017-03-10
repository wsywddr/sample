package com.wsywddr.sample.util.network.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by PandaQ on 2016/11/2.
 * email : 767807368@qq.com
 */

public class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();

        Request.Builder builder = chain.request().newBuilder();
        Request request = builder
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie", "add cookies here")
                .build();

        //打印请求链接
        String TAG_REQUEST = "request";
        Log.e(TAG_REQUEST, request.url().toString());
        Response response = chain.proceed(request);
        //打印返回的message
        return response;
    }
}
