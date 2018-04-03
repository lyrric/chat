package com.play001.gobang.support.entity;

import io.netty.channel.socket.SocketChannel;

import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String userKey;
    //登陆时间
    private Long loginTime;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userKey='" + userKey + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
