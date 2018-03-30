package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;

/**
 * 进入房间请求
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_CREATE_REQ)
public class RoomCreateReqMsg extends ClientBaseMsg{

    public RoomCreateReqMsg() {
    }

    public RoomCreateReqMsg(long time) {
        super(ClientMsgType.ROOM_CREATE_REQ, time);
    }

    @Override
    public String toString() {
        return "RoomCreateReqMsg{}";
    }
}
