package com.wsywddr.sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsywddr.sample.R;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView title;
    private SimpleAdapter adapter;

    @BindView(R.id.list_of_things)
    ListView listOfThings;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void Initial() {
        title.setText("Butter Knife");
        adapter = new SimpleAdapter(this);
        listOfThings.setAdapter(adapter);
    }

    @OnClick(R.id.tv_title)
    void submit() {
        Toast.makeText(getApplicationContext(),"tv_title OnClick",Toast.LENGTH_SHORT).show();
    }

//    @OnLongClick(R2.id.hello) boolean sayGetOffMe() {
//        Toast.makeText(this, "Let go of me!", LENGTH_SHORT).show();
//        return true;
//    }

//    @OnItemClick(R2.id.list_of_things) void onItemClick(int position) {
//        Toast.makeText(this, "You clicked: " + adapter.getItem(position), LENGTH_SHORT).show();
//    }

}
