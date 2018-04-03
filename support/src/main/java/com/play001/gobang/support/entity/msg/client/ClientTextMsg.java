package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;

/**
 * 用户所发文本信息
 */
@MsgAnnotation(msgType = ClientMsgType.TEXT)
public class ClientTextMsg extends ClientBaseMsg{

    private String text;

    public ClientTextMsg() {
    }

    public ClientTextMsg(long time, String text) {
        super(ClientMsgType.TEXT, time);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ClientTextMsg{" +
                "text='" + text + '\'' +
                '}';
    }
}
