package com.wsywddr.sample.presenter;

import android.content.Context;
import com.wsywddr.sample.model.main.MyInfo;
import com.wsywddr.sample.ui.ITestListViewActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PandaQ on 2016/11/20.
 * email : 767807368@qq.com
 */

public class TestListViewActivityPresenter extends BasePresenter {

    private ITestListViewActivity mActivity;

    public TestListViewActivityPresenter(ITestListViewActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    public void getData(Context context) {
//        Subscription subscription = new ApiManager() //此处直接new ApiManager对象避免缓存证书
//                .get12306Service(context, type)
//                .get12306Test()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<ResponseBody>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mActivity.showResult(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody response) {
//                        try {
//                            mActivity.showResult(response.string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        addSubscription(subscription);

        List<MyInfo> nLists=new ArrayList<MyInfo>();
        for(int i=0;i<10;i++)
        {
            MyInfo mMyInfo=new MyInfo();
            mMyInfo.setId(""+i);
            mMyInfo.setReason("Reason"+i);
            mMyInfo.setTime("Time"+i);
            mMyInfo.setDone_at("Done_at"+i);
            nLists.add(mMyInfo);
        }
        mActivity.showResult(nLists);
    }

}
