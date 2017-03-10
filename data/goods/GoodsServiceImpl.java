package com.wsywddr.sample.data.goods;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;
import com.wsywddr.sample.model.base.Response;
import com.wsywddr.sample.util.AppManager;
import com.wsywddr.sample.util.RequestURL;
import com.wsywddr.sample.util.net.DefaultResponseListener;
import com.wsywddr.sample.util.net.GsonRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by fengxiang on 2016/3/15.
 */
public class GoodsServiceImpl implements GoodsService {
    /**
     * api/v1/goods_items#index
     * @param id
     * @param category_id
     */
    @Override
    public void updateCategory(String token, String id, String category_id, DefaultResponseListener<Response> listener) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("token", token);

        Map<String, String> params = new HashMap<String, String>();
        params.put("category_id", category_id);
//        params.put("_method", "patch");

        GsonRequest request = new GsonRequest(Request.Method.GET, "http://121.43.234.2/postjson/JsonFromDB.aspx?TP=ZYSJ&URSN=UR16000026", params, headers, new TypeToken<Response>() {
        }, listener, listener);
        AppManager.getRequestQueue().add(request);
    }
}
