package com.wsywddr.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wsywddr.sample.R;
import com.wsywddr.sample.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推送
 * Created by admin on 2016/3/15.
 */
public class HomeFragment extends BaseFragment {
//    @BindView(R.id.webview)
//    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_layout_status, null);
        ButterKnife.bind(this, mView);
//        initView();
        return mView;
    }
}
