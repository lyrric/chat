package com.play001.gobang.support.entity.msg.server;

public class ServerTextMsg extends ServerBaseMsg {

    private String fromUsername;
    private String text;

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
}
