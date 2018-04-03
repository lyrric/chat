package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.msg.client.*;
import org.apache.log4j.Logger;

//游戏数据操作
public class GameService {
    private Logger logger = Logger.getLogger(GameService.class);
    //请求游戏数据
    public void getCompetitorData(){
        ClientBaseMsg reqMsg = new ClientBaseMsg(ClientMsgType.COMPETITOR_DATA_REQ, System.currentTimeMillis());
        ServiceFactory.getNettyService().send(reqMsg);
    }
    //准备
    public void readyChange(boolean ready){
        ReadyChangeReqMsg reqMsg = new ReadyChangeReqMsg(System.currentTimeMillis(), ready);
        ServiceFactory.getNettyService().send(reqMsg);
    }
    //落子
    public void moveChess(int x, int y){
        MoveChessReqMsg reqMsg = new MoveChessReqMsg(System.currentTimeMillis(), x, y);
        ServiceFactory.getNettyService().send(reqMsg);
    }
    //退出游戏
    public void ExitRoom(){
        ClientBaseMsg req = new ClientBaseMsg(ClientMsgType.EXIT_ROOM, System.currentTimeMillis());
        ServiceFactory.getNettyService().send(req);
    }
}
