package com.play001.gobang.server.entity;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

public class ClientData {
    private String username;
    private String userKey;
    private Channel channel;
    //用户所在房间Id
    private Integer roomId;
    //登陆时间
    private Long loginTime;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
