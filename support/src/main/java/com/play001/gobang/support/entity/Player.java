package com.play001.gobang.support.entity;

import java.io.Serializable;

public class Player implements Serializable{

    //对手名字
    private String username;
    //是否先手
    private Boolean isFirst;
    //是否准备
    private Boolean isReady;

    public Player() {
    }

    public Player(String username, Boolean isFirst, Boolean isReady) {
        this.username = username;
        this.isFirst = isFirst;
        this.isReady = isReady;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
