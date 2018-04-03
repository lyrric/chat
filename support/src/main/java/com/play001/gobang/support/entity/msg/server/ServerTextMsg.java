package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

@MsgAnnotation(msgType = ServerMsgType.TEXT)
public class ServerTextMsg extends ServerBaseMsg {

    private String fromUsername;
    private String text;

    public ServerTextMsg() {
    }

    public ServerTextMsg(long time, String fromUsername, String text) {
        super(ServerMsgType.TEXT, time);
        this.fromUsername = fromUsername;
        this.text = text;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ServerTextMsg{" +
                "fromUsername='" + fromUsername + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
