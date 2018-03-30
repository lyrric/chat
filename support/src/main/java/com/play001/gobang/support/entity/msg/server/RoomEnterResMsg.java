package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;

import java.util.List;

/**
 * 进入房间请求
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_ENTER_RES)
public class RoomEnterResMsg extends ServerBaseMsg{

    public RoomEnterResMsg() {
    }

    public RoomEnterResMsg(long time) {
        super(ServerMsgType.ROOM_ENTER_RES, time);
    }

    @Override
    public String toString() {
        return "RoomEnterResMsg{}";
    }
}
