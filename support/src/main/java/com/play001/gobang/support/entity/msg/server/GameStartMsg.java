package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

import java.util.Base64;

/**
 * 游戏对手信息
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_START)
public class GameStartMsg extends ServerBaseMsg {

    //先手用户名
    private String theInitiativeName;

    public GameStartMsg() {
    }

    public GameStartMsg(long time, String theInitiativeName) {
        super(ServerMsgType.GAME_START, time);
        this.theInitiativeName = theInitiativeName;
    }

    public String getBlacknessUsername() {
        return theInitiativeName;
    }


}
