package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;

/**
 * 移动
 */
@MsgAnnotation(msgType = ServerMsgType.MOVE_CHESS)
public class MoveChessResMsg extends ServerBaseMsg {

    private Integer x;
    private Integer y;
    private String username;
    public MoveChessResMsg() {
    }

    public MoveChessResMsg(long time, String username, Integer x, Integer y) {
        super(ServerMsgType.MOVE_CHESS, time);
        this.x = x;
        this.y = y;
        this.username = username;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
