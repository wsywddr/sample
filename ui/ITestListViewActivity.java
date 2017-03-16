package com.wsywddr.sample.ui;

import com.wsywddr.sample.model.main.MyInfo;

import java.util.List;

/**
 * Created by PandaQ on 2016/11/20.
 * email : 767807368@qq.com
 */

public interface ITestListViewActivity {
    void initData();
    void showResult(List<MyInfo> mlist);
    void addDataToHead();
}
