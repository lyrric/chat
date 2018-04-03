package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.msg.client.ClientTextMsg;

public class MessageService {

    //发送聊天消息
    public void sendTextMsg(String msg){
        ClientTextMsg textMsg = new ClientTextMsg(System.currentTimeMillis(), msg);
        ServiceFactory.getNettyService().send(textMsg);
    }
}
