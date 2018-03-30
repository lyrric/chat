package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

//有用户进入房间消息
@MsgAnnotation(msgType = ServerMsgType.USER_ENTER)
public class UserEnterMsg extends ServerBaseMsg{

    private String username;

    public UserEnterMsg() {
    }

    public UserEnterMsg(long time, String username) {
        super(ServerMsgType.USER_ENTER, time);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserEnterMsg{" +
                "username='" + username + '\'' +
                '}';
    }
}
