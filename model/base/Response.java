package com.wsywddr.sample.model.base;

/**
 * Created by fengxiang on 2016/3/15.
 */
public class Response<T> {

    private String status;
    private String msg;
    private T data;

    public String getCode() {
        return status;
    }

    public void setCode(String code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
