package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 用户离开消息
 * 包括离开房间和下线
 */
@MsgAnnotation(msgType = ServerMsgType.USER_LEAVE)
public class UserLeaveResMsg extends ServerBaseMsg {

    //离开的用户名
    private String username;

    public UserLeaveResMsg() {
    }

    public UserLeaveResMsg(long time, String username) {
        super(ServerMsgType.USER_LEAVE, time);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
