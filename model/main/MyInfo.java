package com.wsywddr.sample.model.main;

/**
 * Created by xthink3 on 16/3/17.
 */
public class MyInfo {
    private String id;
    private String display_created_at;
    private String reason;
    private UserInfo user;
    private String done_at;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return display_created_at;
    }

    public void setTime(String time) {
        this.display_created_at = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDone_at() {
        return done_at;
    }

    public void setDone_at(String done_at) {
        this.done_at = done_at;
    }
}
