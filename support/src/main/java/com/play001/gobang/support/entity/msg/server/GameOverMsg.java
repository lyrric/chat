package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 游戏结束
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_OVER)
public class GameOverMsg extends ServerBaseMsg{
    //赢家用户名
    private String winner;

    public GameOverMsg() {
    }

    public GameOverMsg(long time, String winner) {
        super(ServerMsgType.GAME_OVER, time);
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
