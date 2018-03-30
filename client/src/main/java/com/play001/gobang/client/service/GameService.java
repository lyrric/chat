package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.msg.client.GameDataReqMsg;

//请求数据
public class GameService {

    public void getGameData(){
        GameDataReqMsg reqMsg = new GameDataReqMsg(System.currentTimeMillis());
        ServiceFactory.getNettyService().send(reqMsg);
    }
}
