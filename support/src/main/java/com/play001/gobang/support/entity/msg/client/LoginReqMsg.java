package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Client;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 登陆请求
 */
@MsgAnnotation(msgType = ClientMsgType.LOGIN_REQ)
public class LoginReqMsg extends ClientBaseMsg {

    private String username;
    public LoginReqMsg() {
    }

    public LoginReqMsg(String username, Long time, Client user) {
        super(ServerMsgType.LOGIN_RES, time, user);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
