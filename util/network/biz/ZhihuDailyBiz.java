package com.wsywddr.sample.util.network.biz;


import com.wsywddr.sample.util.network.api.ApiManager;
import com.wsywddr.sample.util.network.databeans.ZhiHuDaily;
import com.wsywddr.sample.util.network.databeans.ZhihuStory;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PandaQ on 2016/10/19.
 * email : 767807368@qq.com
 */

public class ZhihuDailyBiz {

    public void getStoryDataByRetrofit(final OnEventLister<ArrayList<ZhihuStory>> eventLister) {
        ApiManager apiManager = ApiManager.getInstence();
        Call<ZhiHuDaily> call = apiManager.getDataService().getZhihuDailyRetrofitOnly();
        //发送异步请求
        call.enqueue(new Callback<ZhiHuDaily>() {
            @Override
            public void onResponse(Call<ZhiHuDaily> call, Response<ZhiHuDaily> response) {
                eventLister.onSuccess(response.body().getStories());
            }

            @Override
            public void onFailure(Call<ZhiHuDaily> call, Throwable t) {
                eventLister.onFail(t.getMessage(), "");
            }
        });
    }
}
