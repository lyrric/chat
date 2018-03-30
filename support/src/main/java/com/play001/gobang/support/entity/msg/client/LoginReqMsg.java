package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.User;

/**
 * 登陆请求
 */
@MsgAnnotation(msgType = ClientMsgType.LOGIN_REQ)
public class LoginReqMsg extends ClientBaseMsg {

    public LoginReqMsg() {
    }

    public LoginReqMsg(Long time) {
        super(ClientMsgType.LOGIN_REQ, time);
    }

    @Override
    public String toString() {
        return "LoginReqMsg{}";
    }
}
