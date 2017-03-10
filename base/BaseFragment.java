package com.wsywddr.sample.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.wsywddr.sample.util.ACache;
import com.wsywddr.sample.util.Tools;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected Gson mGson;
    protected ACache mCache;
    protected KProgressHUD loading_hud;

    private Unbinder unbinder;
    protected Activity mActivity;  //代替getavtivity()
    /**
     *  如果你用了support 23的库，上面的方法会提示过时，有强迫症的小伙伴，可以用下面的方法代替
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCache = ACache.get(getActivity());
        mGson = new Gson();
        loading_hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
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
            SuperActivityToast.create(getActivity(), new Style(), Style.TYPE_STANDARD)
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
