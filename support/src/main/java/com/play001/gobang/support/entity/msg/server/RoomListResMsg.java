package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;

import java.util.List;

/**
 * 返回房间列表
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_LIST_RES)
public class RoomListResMsg extends ServerBaseMsg{



    private List<Room> roomList;

    public RoomListResMsg() {
    }

    public RoomListResMsg(long time) {
        super(ClientMsgType.ROOM_LIST_REQ, time);
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "RoomListResMsg{" +
                "roomList=" + roomList +
                '}';
    }
}
