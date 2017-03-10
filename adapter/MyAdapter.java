package com.wsywddr.sample.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.wsywddr.sample.R;
import com.wsywddr.sample.model.main.MyInfo;
import com.wsywddr.sample.util.ACache;
import com.wsywddr.sample.util.Tools;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by bappy on 16-7-21.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    public List<MyInfo> mList;
    private LayoutInflater mInflater;
    public ViewHolder holder;
    private ACache mCache;

    public MyAdapter(Context context, List<MyInfo> list) {
        this.context = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(context);
        mCache = ACache.get(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_apply_list,parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MyInfo ai = mList.get(position);

        Tools.loadImageUrlToImageView(context,holder.img_logo,"http://images.csdn.net/20170118/gic19381274.jpg");
        holder.tv_username.setText(ai.getId());
        holder.tv_time.setText(ai.getTime());
        holder.tv_comment.setText(ai.getReason());

        holder.img_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("id", "" + ai.getUser().getId());
//                intent.setClass(context, UserHomeActivity.class);
//                context.startActivity(intent);
            }
        });

        holder.tv_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                new UserServiceImpl().createApplyOrder(ai.getId(), mCache.getAsString("token"), new DefaultResponseListener<Response>() {
//                    @Override
//                    public void onSuccess(Response response) {
//                        if (response.getCode().equals("1111")) {
//                            Gson mGson=new Gson();
//                            Log.d("createApplyOrder",mGson.toJson(response.getData()));
//                            try {
//                                JSONObject jsonobject = new JSONObject(mGson.toJson(response.getData()));
//                                EventBus.getDefault().post(new ApplyListEvent("giveout", jsonobject.getJSONObject("order").getString("id")));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            EventBus.getDefault().post(new SongEvent("giveout", ""));
//                            ToastUtil.show(context, "送出成功");
//                        } else {
//                            ToastUtil.show(context, response.getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onError(VolleyError error) {
//                        Log.d("responseresponse", "1");
//                    }
//                });
            }
        });
        return convertView;
    }


    static final class ViewHolder {
        @BindView(R.id.img_logo)
        ImageView img_logo;
        @BindView(R.id.tv_username)
        TextView tv_username;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_comment)
        TextView tv_comment;
        @BindView(R.id.tv_get)
        TextView tv_get;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

