package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.entity.Client;

import java.io.Serializable;

/**
 * 消息基类,状态,创建时间,消息类型
 * 必须实现Serializable接口,不然无法传递消息
 */
public class ClientBaseMsg implements Serializable{

    private Client user;
    private short type;
    private long time;
    public ClientBaseMsg() {
    }

    public ClientBaseMsg(short type, long time, Client user) {
        this.type = type;
        this.time = time;
        this.user = user;
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

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }
}
