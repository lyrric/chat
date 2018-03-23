package com.play001.chat.support.entity;

/**
 * 消息基类,包括消息的创建时间,来自用户username
 */
public abstract class AbstractMsg {

    private long time;
    private String username;



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
