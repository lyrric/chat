package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 进入房间请求
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_ENTER_REQ)
public class RoomEnterReqMsg extends ClientBaseMsg{

    public RoomEnterReqMsg() {
    }

    public RoomEnterReqMsg(long time) {
        super(ClientMsgType.ROOM_ENTER_REQ, time);
    }



    @Override
    public String toString() {
        return "RoomEnterReqMsg{" +
                '}';
    }
}
