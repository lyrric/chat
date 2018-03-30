package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.entity.User;

import java.io.Serializable;

/**
 * 消息基类,状态,创建时间,消息类型
 * 必须实现Serializable接口,不然无法传递消息
 */
public class ClientBaseMsg implements Serializable{

    private User user;
    private short type;
    private long time;
    public ClientBaseMsg() {
    }

    public ClientBaseMsg(short type, long time) {
        this.type = type;
        this.time = time;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ClientBaseMsg{" +
                "user=" + user +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
