package com.wsywddr.sample.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsywddr.sample.R;
import com.wsywddr.sample.adapter.DogAdapter;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;
import com.wsywddr.sample.model.main.Dog;
import com.wsywddr.sample.util.database.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RealmDataBaseActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_age)
    EditText et_age;
    @BindView(R.id.et_id)
    EditText et_id;

    private DogAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private RealmHelper mRealmHelper;
    private List<Dog> mDogs = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_realmdatabase;
    }

    @Override
    protected void Initial() {
        mRealmHelper = new RealmHelper(mContext);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new DogAdapter(this, mDogs, R.layout.item_dog);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.tv_save)
    void tv_savesubmit() {
        String age=et_age.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        if(isEmpty(age)&&isEmpty(name))
        {
            showToast("age or name isEmpty");
            return;
        }
        Dog dog = new Dog();
        dog.setAge(Integer.parseInt(age));
        dog.setName(name);
        mRealmHelper.addDog(dog);
        mDogs.add(dog);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_choose)
    void tv_choosesubmit() {
        String id=et_id.getText().toString().trim();
        if(isEmpty(id))
        {
            showToast("id isEmpty");
            return;
        }
        Dog dog =mRealmHelper.queryDogById(id);
        if(dog!=null)
        {
            et_age.setText(dog.getAge());
            et_name.setText(dog.getName());
        }
    }

    @OnClick(R.id.tv_change)
    void tv_changesubmit() {
        String id=et_id.getText().toString().trim();
        String age=et_age.getText().toString().trim();
        String name=et_name.getText().toString().trim();
        if(isEmpty(age)||isEmpty(name)||isEmpty(id))
        {
            showToast("id or age or name isEmpty");
            return;
        }
        mRealmHelper.updateDog(id,name,age);
        mAdapter.notifyDataSetChanged();
    }
}
