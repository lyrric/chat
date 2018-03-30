package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

@MsgAnnotation(msgType = ClientMsgType.READY_CHANGE)
public class ReadyChangeReqMsg extends ClientBaseMsg {

    private Boolean ready;

    public ReadyChangeReqMsg() {
    }

    public ReadyChangeReqMsg(long time, boolean ready) {
        super(ClientMsgType.READY_CHANGE, time);
        this.ready = ready;
    }


    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    @Override
    public String toString() {
        return "UserReadyChangeReqMsg{" +
                "ready=" + ready +
                '}';
    }
}
