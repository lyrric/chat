package com.play001.gobang.support.entity.msg.user;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.BaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 登陆请求
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class ReqLoginMsg extends BaseMsg {

    private String username;
    public ReqLoginMsg() {
    }

    public ReqLoginMsg(String username, Long time) {
        super(ServerMsgType.LOGIN_RES, time);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
