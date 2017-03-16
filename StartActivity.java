package com.wsywddr.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.wsywddr.sample.activity.LayoutStatusActivity;
import com.wsywddr.sample.activity.ListviewActivity;
import com.wsywddr.sample.activity.LocationActivity;
import com.wsywddr.sample.activity.MainActivity;
import com.wsywddr.sample.activity.RealmDataBaseActivity;
import com.wsywddr.sample.activity.TestListViewActivity;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;
import com.wsywddr.sample.data.goods.GoodsServiceImpl;
import com.wsywddr.sample.model.MyObserver;
import com.wsywddr.sample.model.MyPerson;
import com.wsywddr.sample.model.PersonBd;
import com.wsywddr.sample.model.base.Response;
import com.wsywddr.sample.model.factory.AudiCarFactory;
import com.wsywddr.sample.model.factory.AudiFactory;
import com.wsywddr.sample.model.factory.CarA;
import com.wsywddr.sample.model.factory.CarB;
import com.wsywddr.sample.util.QiniuUploadUitls;
import com.wsywddr.sample.util.net.DefaultResponseListener;
import com.wsywddr.sample.util.network.api.ApiManager;
import com.wsywddr.sample.util.network.biz.OnEventLister;
import com.wsywddr.sample.util.network.biz.ZhihuDailyBiz;
import com.wsywddr.sample.util.network.databeans.ZhiHuDaily;
import com.wsywddr.sample.util.network.databeans.ZhihuStory;
import com.wsywddr.sample.util.network.disklrucache.Constants;
import com.wsywddr.sample.util.network.disklrucache.DiskCacheManager;
import com.wsywddr.sample.util.view.ExpandIconView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class StartActivity extends BaseActivity {

    @BindView(R.id.tv_base)
    TextView tv_base;

    @BindView(R.id.expand_icon)
    ExpandIconView expandIconView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    MyPerson observable;
    MyObserver myObserver;

    @Override
    protected void Initial() {
        mCompositeSubscription = new CompositeSubscription();
//        showToast("Initial");
//        tv_base.setText("'s");
        tv_base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("tv_base");
            }
        });

//        loadDataByRetrofit();
        loadDataByRxandroidRetrofit();

        observable= new MyPerson();
        myObserver = new MyObserver(999999);
        observable.addObserver(myObserver);

        observable.setName("a" + 99);
        observable.setAge(10 + 99);
        observable.setSex("男" + 99);

        PersonBd.Builder builder=new PersonBd.Builder();
        PersonBd mPersonBd=builder.name("chen").age("10").build();

        String str = "{\"code\":\"1111\",\"msg\":\"success\",\"data\":{\"sms_validation_code_id\":\"1462781375345065984\",\"sms_validation_code_value\":\"356191\"}}";
        String str2="{\"code\":\"1111\",\"msg\":\"success\",\"result\":null,\"data\":{\"sms_validation_code_id\":\"1462781375345065984\",\"sms_validation_code_value\":null}}";
        showToast(jxJson("result",str2));

        testRxjava();
        testCollection();
        testFactory();
    }

    private void makeCache(ArrayList<ZhihuStory> stories) {
        DiskCacheManager manager = new DiskCacheManager(MyApplication.getContext(), Constants.ZHIHUCACHE);
        manager.put(Constants.ZHIHUSTORY_KEY, stories);
    }

    @OnClick(R.id.tv_base)
    void submit() {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        Toast.makeText(getApplicationContext(), "tv_base OnClick", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_testListView)
    void tv_testListViewClick() {
        startActivity(new Intent(StartActivity.this, TestListViewActivity.class));
    }

    @OnClick(R.id.click)
    void clicksubmit() {
        expandIconView.switchState();
    }

    @OnClick(R.id.tv_load_layout)
    void tv_load_layoutsubmit() {
        startActivity(new Intent(StartActivity.this, LayoutStatusActivity.class));
    }

    @OnClick(R.id.tv_qiniu)
    void tv_qiniusubmit() {
//        服务器getQiNiuToken
//        String filepath=file.getAbsolutePath()
//        QiniuUploadUitls.getInstance().uploadImage(filepath, qnToken,
//                new QiniuUploadUitls.QiniuUploadUitlsListener() {
//                    @Override
//                    public void onSucess(String fileUrl) {
//                        Log.d("fileUrl", "" + fileUrl);
//                        image_key=fileUrl;
//                    }
//
//                    @Override
//                    public void onProgress(int progress) {
//                    }
//
//                    @Override
//                    public void onError(int errorCode, String msg) {
//                    }
//                });
    }

    @OnClick(R.id.tv_luban)
    void tv_lubansubmit() {
//        File file=new File("");
//        Luban.get(this)
//                .load(file)
//                .putGear(Luban.THIRD_GEAR)
//                .setFilename(System.currentTimeMillis() + "")
//                .setCompressListener(new OnCompressListener() {
//                    @Override
//                    public void onStart() {
////                        Toast.makeText(PublishActivity.this, "I'm start", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onSuccess(File file) {
//                        Log.i("path", file.getAbsolutePath());
////                        BeginUploadPicToQiNiu(qiniu_token,file.getAbsolutePath());
//
//                        Log.d("onSuccess",file.length() / 1024 + "k");
//                        Log.d("onSuccess",Luban.get(getApplicationContext()).getImageSize(file.getPath())[0] + " * " + Luban.get(getApplicationContext()).getImageSize(file.getPath())[1]);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//                }).launch();
    }

    @OnClick(R.id.tv_dialogplus)
    void tv_dialogplussubmit() {
        Holder holder = new ViewHolder(R.layout.dialog_content);
        DialogPlus mDialog = DialogPlus.newDialog(StartActivity.this)
                .setContentHolder(holder)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        Button like_it_button = (Button) mDialog.findViewById(R.id.like_it_button);
        like_it_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("like_it_button");
            }
        });

        Button love_it_button = (Button) mDialog.findViewById(R.id.love_it_button);
        love_it_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("love_it_button");
            }
        });
        mDialog.show();
    }

    @OnClick(R.id.tv_volley)
    void tv_volleyubmit() {
        new GoodsServiceImpl().updateCategory("", "", "", new DefaultResponseListener<Response>() {
            @Override
            public void onSuccess(Response response) {
                showToast(response.getCode() + "" + response.getMsg());
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
    }

    @OnClick(R.id.tv_listview)
    void tv_listviewubmit() {
        startActivity(new Intent(StartActivity.this, ListviewActivity.class));
    }

    @OnClick(R.id.tv_location)
    void tv_locationubmit() {
        startActivity(new Intent(StartActivity.this, LocationActivity.class));
    }

    @OnClick(R.id.tv_database)
    void tv_databaseubmit() {
        startActivity(new Intent(StartActivity.this, RealmDataBaseActivity.class));
    }

    private void testFactory()
    {
        AudiFactory factory = new AudiCarFactory();
        CarA a = factory.createAudiCar(CarA.class);

        CarB b = factory.createAudiCar(CarB.class);
    }

    private void testCollection()
    {
        int[] scores={1,43,23,10,6,57,25,29};
        for(int i=0;i<scores.length-1;i++)
        {
            for(int j=0;j<scores.length-1-i;j++)
            {
                if(scores[j]>scores[j+1])
                {
                    int temp=scores[j];
                    scores[j]=scores[j+1];
                    scores[j+1]=temp;
                }
                LogUtils.d(scores);
            }
        }
        LogUtils.tag("scorestotal").d(scores);
    }

    private void testRxjava()
    {
        CompositeSubscription mCompositeSubscription=new CompositeSubscription();

        Subscription subscription= Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCompositeSubscription.unsubscribe();
    }

    private String jxJson(String mkey, String strJson) {
        String value="";
        try {
            JSONObject json = new JSONObject(strJson);
            Iterator iterator = json.keys();
            while (iterator.hasNext()) {
                String key = iterator.next() + "";

                if (json.getString(key).startsWith("{")) {
                    jxJson(mkey,json.getString(key));
                } else {
                    if(key.equals(mkey))
                    {
                        Log.d("hasNext", "key-value:" + key + "-" + json.getString(key));
                        value=json.getString(key);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(value.equals("null"))
        {
            value="";
        }
        return value;
    }

    //单独只用Retrofit进行网络请求
    public void loadDataByRetrofit() {
        ZhihuDailyBiz mDailyBiz = new ZhihuDailyBiz();
        mDailyBiz.getStoryDataByRetrofit(new OnEventLister<ArrayList<ZhihuStory>>() {
            @Override
            public void onSuccess(ArrayList<ZhihuStory> response) {
                Log.d("getStoryDataByRetrofit", "onSuccess");
                showToast("getStoryDataByRetrofitonSuccess");
            }

            @Override
            public void onFail(String errCode, String errMsg) {

            }
        });
    }

    //使用rxandroid+retrofit进行请求
    public void loadDataByRxandroidRetrofit() {
        Subscription subscription = ApiManager.getInstence().getDataService()
                .getZhihuDaily()
                .map(new Func1<ZhiHuDaily, ArrayList<ZhihuStory>>() {
                    @Override
                    public ArrayList<ZhihuStory> call(ZhiHuDaily zhiHuDaily) {
                        ArrayList<ZhihuStory> stories = zhiHuDaily.getStories();
                        if (stories != null) {
                            //加载成功后将数据缓存倒本地(demo 中只有一页，实际使用时根据需求选择是否进行缓存)
                            makeCache(zhiHuDaily.getStories());
                        }
                        return stories;
                    }
                })
                //设置事件触发在非主线程
                .subscribeOn(Schedulers.io())
                //设置事件接受在UI线程以达到UI显示的目的
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<ZhihuStory>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("getStoryDataByRetrofit", "onCompleted");
                        showToast("getStoryDataByRetrofitonCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mINewsListActivity.getDataFail("", e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<ZhihuStory> stories) {
                        showToast("getStoryDataByRetrofitonNext");
                        Log.d("getStoryDataByRetrofit", "onNext");
                    }
                });
        //绑定观察对象，注意在界面的ondestory或者onpouse方法中调用presenter.unsubcription();
        mCompositeSubscription.add(subscription);
    }

    private CompositeSubscription mCompositeSubscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
