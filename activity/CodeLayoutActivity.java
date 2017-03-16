package com.wsywddr.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wsywddr.sample.R;
import com.wsywddr.sample.model.TestEvent;
import com.wsywddr.sample.util.Tools;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CodeLayoutActivity extends AppCompatActivity {
    @BindView(R.id.Lin_custom)
    LinearLayout Lin_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_layout);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);

        Initial();
    }

    private void Initial() {

        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(CodeLayoutActivity.this);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
//            img.setImageResource(R.mipmap.ic_launcher);
//        tv.setPadding(Tools.dp2px(CodeLayoutActivity.this, 10), Tools.dp2px(CodeLayoutActivity.this, 5),
//              Tools.dp2px(CodeLayoutActivity.this, 10), Tools.dp2px(CodeLayoutActivity.this, 5));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            Tools.loadImageUrlToImageView(CodeLayoutActivity.this, img, "http://a3.topitme.com/4/1f/40/1122058076c78401f4l.jpg");

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    Tools.dp2px(CodeLayoutActivity.this, 30));
            params2.setMargins(10, 10, 10, 10);
            params2.weight = 1;
            img.setLayoutParams(params2);
            Lin_custom.addView(img);
        }

        Lin_custom.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                EventBus.getDefault().post(new TestEvent(1, 0));
                return true;
            }
        });
    }


    @Subscribe
    public void onMessageEvent(TestEvent event) {
        traversalView(Lin_custom);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void traversalView(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                traversalView((ViewGroup) view);
            } else {
                doView(view);
            }
        }
    }

    private void doView(View view) {
        ViewGroup.LayoutParams para = view.getLayoutParams();
        para.height = view.getMeasuredWidth();
        para.width = view.getMeasuredWidth();
        view.setLayoutParams(para);
    }
}
