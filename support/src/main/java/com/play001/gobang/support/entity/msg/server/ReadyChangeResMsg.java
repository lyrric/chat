package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

@MsgAnnotation(msgType = ServerMsgType.READY_CHANGE)
public class ReadyChangeResMsg extends ServerBaseMsg{

    //用户名
    private String username;
    //是否准备
    private Boolean ready;

    public ReadyChangeResMsg() {
    }

    public ReadyChangeResMsg(long time, String username, Boolean ready) {
        super(ServerMsgType.READY_CHANGE, time);
        this.username = username;
        this.ready = ready;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "ReadyChangeResMsg{" +
                "username='" + username + '\'' +
                ", ready=" + ready +
                '}';
    }
}
