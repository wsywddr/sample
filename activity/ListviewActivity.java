package com.wsywddr.sample.activity;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.wsywddr.sample.R;
import com.wsywddr.sample.adapter.MyAdapter;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;
import com.wsywddr.sample.model.main.MyInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class ListviewActivity extends BaseActivity implements BGARefreshLayout.BGARefreshLayoutDelegate  {

    private MyAdapter adapter;
    private View heardView;
    private List<MyInfo> listData;
    private int pageIndex = 1;

    @BindView(R.id.mylistview)
    ListView mylistview;

    @BindView(R.id.swipe_container)
    BGARefreshLayout swipeLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_listview;
    }

    @Override
    protected void Initial() {
        listData=new ArrayList<MyInfo>();
        for(int i=0;i<10;i++)
        {
            MyInfo mMyInfo=new MyInfo();
            mMyInfo.setId(i+"");
            mMyInfo.setTime(i+"time");
            mMyInfo.setReason(i+"reason");
            listData.add(mMyInfo);
        }

        //一定要加一个header
        heardView = LayoutInflater.from(mContext).inflate(R.layout.refresh_header, null);
        mylistview.addHeaderView(heardView);
        adapter = new MyAdapter(ListviewActivity.this,listData);
        mylistview.setAdapter(adapter);


        swipeLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(ListviewActivity.this, true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.main_black);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.bga_refrash);
//        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.1f);
//        moocStyleRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.custom_imoocstyle);
        swipeLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        swipeLayout.beginRefreshing();
    }

    Handler mHandler=new Handler();
    private void loadData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       if (pageIndex == 1) {
                           for(int i=0;i<10;i++)
                           {
                               MyInfo mMyInfo=new MyInfo();
                               mMyInfo.setId(i+"");
                               mMyInfo.setTime(i+"time");
                               mMyInfo.setReason(i+"reason");
                               listData.add(mMyInfo);
                           }

                           swipeLayout.endRefreshing();
                       } else {
                           for(int i=0;i<10;i++)
                           {
                               MyInfo mMyInfo=new MyInfo();
                               mMyInfo.setId(i+"");
                               mMyInfo.setTime(i+"time");
                               mMyInfo.setReason(i+"reason");
                               listData.add(mMyInfo);
                           }

                           swipeLayout.endLoadingMore();
                       }
                       adapter.notifyDataSetChanged();
                   }
               });
            }
        },1500);

//        new GoodsServiceImpl().showApplyList(getIntentExtra("id"), token, new DefaultResponseListener<Response>() {
//            @Override
//            public void onSuccess(Response response) {
//                if (response.getCode().equals("1111")) {
//                    try {
//                        JSONObject jsonobject = new JSONObject(mGson.toJson(response.getData()));
//                        List<ApplyInfo> jsonfreeObject = mGson.fromJson(jsonobject.getJSONArray("apply_logs").toString(), new TypeToken<List<ApplyInfo>>() {
//                        }.getType());//把JSON格式的字符串转为List
//                        myFinds.addAll(jsonfreeObject);
//
//                        if (pageIndex == 1) {
//                            swipeLayout.endRefreshing();
//                        } else {
//                            swipeLayout.endLoadingMore();
//                        }
//                        adapter.notifyDataSetChanged();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    showToast("" + response.getMsg());
//                }
//            }
//
//            @Override
//            public void onError(VolleyError error) {
//                if (pageIndex == 1) {
//                    swipeLayout.endRefreshing();
//                } else {
//                    swipeLayout.endLoadingMore();
//                }
//            }
//        });
    }

    private void onRefresh() {
        listData.clear();
        pageIndex = 1;
        loadData();
    }

    private void onLoad() {
        pageIndex++;
        loadData();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        onRefresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        onLoad();
        return true;
    }
}
