package com.wsywddr.sample.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsywddr.sample.R;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;
import com.wsywddr.sample.util.loadinglayout.LoadingLayout;

import butterknife.BindView;
import butterknife.OnClick;

public class LayoutStatusActivity extends BaseActivity {

    @BindView(R.id.loading)
    LoadingLayout loading;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_status;
    }

    @Override
    protected void Initial() {
        loading.setLoadingPage(R.layout.define_loading_page).setOnReloadListener(new LoadingLayout.OnReloadListener() {

            @Override
            public void onReload(View v) {
                Toast.makeText(LayoutStatusActivity.this, "重试", Toast.LENGTH_SHORT).show();
            }
        });
        loading.setStatus(LoadingLayout.Loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Empty);
            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Error);
            }
        },4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.No_Network);
            }
        },6000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Success);
            }
        },8000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loading.setStatus(LoadingLayout.Loading);
            }
        },10000);
    }
}
