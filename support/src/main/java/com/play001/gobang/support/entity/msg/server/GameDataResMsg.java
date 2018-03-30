package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.ServerGameData;

/**
 * 返回游戏数据
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_DATA_RES)
public class GameDataResMsg extends ServerBaseMsg{

    private ServerGameData gameData;
    public GameDataResMsg(long time) {
        super(ServerMsgType.GAME_DATA_RES, time);
    }

    public ServerGameData getGameData() {
        return gameData;
    }

    public void setGameData(ServerGameData gameData) {
        this.gameData = gameData;
    }


}
