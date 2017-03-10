package com.wsywddr.sample.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.wsywddr.sample.model.BaseEvent;
import com.wsywddr.sample.util.ACache;
import com.wsywddr.sample.util.AppConfig;
import com.wsywddr.sample.util.Tools;
import com.wsywddr.sample.util.glide.CircleTransform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity {
    protected ACache mCache;
    protected BaseActivity mContext;
    protected Gson mGson;
    protected KProgressHUD loading_hud;

    // 右滑返回
    protected SwipeBackLayout mSwipeBackLayout;

    //布局文件ID
    protected abstract int getLayoutId();
    //Initial
    protected abstract void Initial();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        mCache = ACache.get(this);
        mContext = this;
        mGson = new Gson();
        loading_hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);

        mSwipeBackLayout = getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        Initial();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onMessageEvent(BaseEvent event) {

    }

    public void showLoading(String... text) {
        if(text.length!=0)
        {
            loading_hud.setLabel(text[0]);
        }
        loading_hud.show();
    }

    public void dismissLoading() {
        loading_hud.dismiss();
    }

    public void showToast(String text) {
        if (text != null && !text.trim().equals("")) {
            SuperActivityToast.create(mContext, new Style(), Style.TYPE_STANDARD)
                    .setText(text)
                    .setDuration(Style.DURATION_SHORT)
                    .setFrame(Style.FRAME_KITKAT)
                    .setColor(PaletteUtils.getSolidColor(PaletteUtils.GREY))
                    .setAnimations(Style.ANIMATIONS_POP).show();
//            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isEmpty(String text) {
        return Tools.isEmpty(text);
    }

    public void loadImageUrlToImageView(Context context, ImageView mImageView, String img_url) {
        Tools.loadImageUrlToImageView(context, mImageView, img_url);
    }

    public void loadImageUrlToCircleImageView(Context context, ImageView mImageView, String img_url) {
        Tools.loadImageUrlToCircleImageView(context, mImageView, img_url);
    }

    public String getImageUrl(String key, String width, String height) {
        return Tools.getImageUrl(key, width, height);
    }

    public String getIntentExtra(String key) {
        String value = "";
        if (!isEmpty(getIntent().getStringExtra(key))) {
            value = getIntent().getStringExtra(key);
        }
        return value;
    }

    public void log(String key, String value) {
        if (!getACache("showLog").equals("false")) {
            Log.d(key, value);
        }
    }

    public String getACache(String key) {
        String value = "";
        if (!isEmpty(mCache.getAsString(key))) {
            value = mCache.getAsString(key);
        }
        return value;
    }

    public String getACache(String key, String normal) {
        String value = "";
        if (!isEmpty(mCache.getAsString(key))) {
            value = mCache.getAsString(key);
        } else {
            value = normal;
        }
        return value;
    }

    public void putACache(String key, String value) {
        if (!isEmpty(key)) {
            mCache.put(key, value);
        }
    }
}
