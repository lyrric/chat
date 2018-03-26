package com.play001.gobang.support.entity.msg.user;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.BaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 返回key给用户
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class LoginMsg extends BaseMsg {

    private String userKey;

    public LoginMsg() {
    }

    public LoginMsg(String userKey, Long time) {
        super(ServerMsgType.LOGIN_RES, time);
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
