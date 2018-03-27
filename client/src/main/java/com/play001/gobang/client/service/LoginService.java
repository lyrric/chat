package com.play001.gobang.client.service;

import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import io.netty.channel.socket.SocketChannel;

public class LoginService {

    /**
     * 登陆
     */
    public void login(String username){
        UserService userService = ServiceFactory.getUserService();
        SocketChannel channel = userService.getSocketChannel();
        LoginReqMsg msg = new LoginReqMsg();
        msg.setUsername(username);
        msg.setTime(System.currentTimeMillis());
        channel.writeAndFlush(msg);
    }
}
