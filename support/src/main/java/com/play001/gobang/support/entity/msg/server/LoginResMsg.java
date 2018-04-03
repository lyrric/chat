package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 返回key给用户
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class LoginResMsg extends ServerBaseMsg {

    private String userKey;

    public LoginResMsg() {
        super(ServerMsgType.LOGIN_RES);
    }

    public LoginResMsg(String userKey, Long time) {
        super(ServerMsgType.LOGIN_RES, time);
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "LoginResMsg{" +
                "userKey='" + userKey + '\'' +
                '}';
    }
}
