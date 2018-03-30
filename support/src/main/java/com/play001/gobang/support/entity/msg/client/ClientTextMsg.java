package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;

/**
 * 用户所发文本信息
 */
@MsgAnnotation(msgType = ClientMsgType.TEXT)
public class ClientTextMsg extends ServerBaseMsg {

    private User formUser;
    private String text;

    public ClientTextMsg() {
    }

    public ClientTextMsg(long time, User formUser, String text) {
        super(ClientMsgType.TEXT, time);
        this.formUser = formUser;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFormUser() {
        return formUser;
    }

    public void setFormUser(User formUser) {
        this.formUser = formUser;
    }

    @Override
    public String toString() {
        return "ClientTextMsg{" +
                "formUser=" + formUser +
                ", text='" + text + '\'' +
                '}';
    }
}
