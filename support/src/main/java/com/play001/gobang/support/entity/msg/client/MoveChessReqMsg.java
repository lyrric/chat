package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;

import java.util.Base64;

/**
 * 移动
 */
@MsgAnnotation(msgType = ClientMsgType.MOVE_CHESS)
public class MoveChessReqMsg extends ClientBaseMsg {

    private Integer x;
    private Integer y;

    public MoveChessReqMsg() {
        super(ClientMsgType.MOVE_CHESS);
    }
    public MoveChessReqMsg(long time, Integer x, Integer y) {
        super(ClientMsgType.MOVE_CHESS, time);
        this.x = x;
        this.y = y;
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

    @Override
    public String toString() {
        return "MoveChessReqMsg{" +
                "x=" + x +
                ", y=" + y +
                '}' + super.toString();
    }
}
