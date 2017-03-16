package com.wsywddr.sample.model;

/**
 * Created by xthink3 on 16/3/17.
 */
public class TestEvent {
    private int requestCode;
    private int resultCode;


    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


    public TestEvent(int requestCode, int resultCode) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }
}
