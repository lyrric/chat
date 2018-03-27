package com.play001.gobang.support.entity;

import io.netty.channel.unix.Socket;

import java.nio.channels.SocketChannel;

public class Client {
    private String username;
    private String userKey;
    private SocketChannel socketChannel;
    //连接时间
    private Long time;
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

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}
