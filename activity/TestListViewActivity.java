package com.wsywddr.sample.activity;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wsywddr.sample.R;
import com.wsywddr.sample.adapter.MyAdapter;
import com.wsywddr.sample.adapter.SimpleAdapter;
import com.wsywddr.sample.base.BaseActivity;
import com.wsywddr.sample.model.main.MyInfo;
import com.wsywddr.sample.presenter.TestListViewActivityPresenter;
import com.wsywddr.sample.ui.ITestListViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TestListViewActivity extends BaseActivity implements ITestListViewActivity {

    private MyAdapter adapter;
    private List<MyInfo> mLists=new ArrayList<MyInfo>();

    @BindView(R.id.listview)
    ListView listview;
    private TestListViewActivityPresenter mPresenter = new TestListViewActivityPresenter(this);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_listview;
    }

    @Override
    protected void Initial() {
        adapter = new MyAdapter(this,mLists);
        listview.setAdapter(adapter);
    }

    @OnClick(R.id.tv_add_to_header)
    void tv_add_to_headersubmit() {
        addDataToHead();
    }

    @Override
    public void initData() {
        mPresenter.getData(mContext);
    }

    @Override
    public void addDataToHead() {
        List<MyInfo> nLists=new ArrayList<MyInfo>();
        for(int i=0;i<10;i++)
        {
            MyInfo mMyInfo=new MyInfo();
            mMyInfo.setId("head"+i);
            mMyInfo.setReason("headReason"+i);
            mMyInfo.setTime("headTime"+i);
            mMyInfo.setDone_at("headDone_at"+i);
            nLists.add(mMyInfo);
        }
        adapter.addToHead(nLists);
    }

    @Override
    public void showResult(List<MyInfo> mlist) {
        mLists.addAll(mlist);
        adapter.notifyDataSetChanged();
    }

}
