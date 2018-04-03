package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;

@MsgAnnotation(msgType = ClientMsgType.ROOM_ENTER_REQ)
public class EnterRoomReqMsg extends ClientBaseMsg {
    private Integer roomId;

    public EnterRoomReqMsg() {
    }

    public EnterRoomReqMsg(long time, Integer roomId) {
        super(ClientMsgType.ROOM_ENTER_REQ, time);
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
