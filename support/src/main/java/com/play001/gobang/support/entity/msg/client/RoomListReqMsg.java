package com.play001.gobang.support.entity.msg.client;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;

import java.util.List;

/**
 * 请求房间列表
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_LIST_REQ)
public class RoomListReqMsg extends ClientBaseMsg{

    public RoomListReqMsg() {
    }

    public RoomListReqMsg(long time) {
        super(ClientMsgType.ROOM_LIST_REQ, time);
    }

    @Override
    public String toString() {
        return "RoomListReqMsg{}";
    }
}
