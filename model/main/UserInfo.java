package com.wsywddr.sample.model.main;

import java.io.Serializable;

/**
 * Created by xthink3 on 16/3/17.
 */
public class UserInfo implements Serializable {
    private String id;
    private String mobile;
    private String token;
    private String nickname;
    private String avatar_key;
    private String display_avatar_key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar_key() {
        return avatar_key;
    }

    public void setAvatar_key(String avatar_key) {
        this.avatar_key = avatar_key;
    }

    public String getDisplay_avatar_key() {
        return display_avatar_key;
    }

    public void setDisplay_avatar_key(String display_avatar_key) {
        this.display_avatar_key = display_avatar_key;
    }
}
