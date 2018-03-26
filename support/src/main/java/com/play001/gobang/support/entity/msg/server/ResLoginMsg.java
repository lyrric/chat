package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.BaseMsg;

/**
 * 返回key给用户
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class ResLoginMsg extends BaseMsg {

    private String userKey;
    public ResLoginMsg() {
    }

    public ResLoginMsg(String userKey, Long time) {
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
