package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 请求游戏数据
 */
@MsgAnnotation(msgType = ClientMsgType.GAME_DATA_REQ)
public class GameDataReqMsg extends ClientBaseMsg{


    public GameDataReqMsg(long time) {
        super(ClientMsgType.GAME_DATA_REQ, time);
    }


    @Override
    public String toString() {
        return "GameDataReqMsg{" +
                '}';
    }
}
