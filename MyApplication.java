package com.wsywddr.sample;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wsywddr.sample.util.AppManager;
import com.wsywddr.sample.util.baiduservice.LocationService;
import com.wsywddr.sample.util.database.RealmHelper;
import com.zhy.autolayout.config.AutoLayoutConifg;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by bapppy on 17-1-12.
 */

public class MyApplication extends Application {
    public RequestQueue requestQueue;

    //地图定位
    public LocationService locationService;
    private static MyApplication sMyApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
        sMyApplication = this;

        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());

        requestQueue = Volley.newRequestQueue(this);
        AppManager.setRequestQueue(requestQueue);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 获取全局 context
     * @return 全局context
     */
    public static Context getContext() {
        return sMyApplication.getApplicationContext();
    }

    /**
     * 获取应用的版本号
     * @return 应用版本号
     */
    public static int getAppVersion() {
        Context context = getContext();
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
