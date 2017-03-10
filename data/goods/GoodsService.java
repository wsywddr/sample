package com.wsywddr.sample.data.goods;


import com.wsywddr.sample.model.base.Response;
import com.wsywddr.sample.util.net.DefaultResponseListener;

/**
 * Created by admin on 2016/3/15.
 */
public interface GoodsService {
    public void updateCategory(String token, String id, String category_id, DefaultResponseListener<Response> listener);
}
