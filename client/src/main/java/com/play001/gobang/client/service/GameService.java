package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.ReadyChangeReqMsg;

//游戏数据操作
public class GameService {

    //请求游戏数据
    public void getGameData(){
        ClientBaseMsg reqMsg = new ClientBaseMsg(ClientMsgType.GAME_DATA_REQ, System.currentTimeMillis());
        ServiceFactory.getNettyService().send(reqMsg);
    }
    //准备
    public void readyChange(boolean ready){
        ReadyChangeReqMsg reqMsg = new ReadyChangeReqMsg(System.currentTimeMillis(), ready);
        ServiceFactory.getNettyService().send(reqMsg);
    }
}
