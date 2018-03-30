package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;

/**
 * 返回创建房间结果
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_CREATE_RES)
public class RoomCreateResMsg extends ServerBaseMsg{

    //创建的房间ID
    private Integer roomId;
    public RoomCreateResMsg() {
    }

    public RoomCreateResMsg(Integer roomId, long time) {
        super(ServerMsgType.ROOM_CREATE_RES, time);
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "RoomCreateResMsg{" +
                "roomId=" + roomId +
                '}';
    }
}
