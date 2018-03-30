package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.RoomEnterReqMsg;
import com.play001.gobang.support.entity.msg.server.RoomEnterResMsg;
import com.play001.gobang.support.entity.msg.server.RoomListResMsg;
import org.apache.log4j.Logger;


/**
 * 处理查询房间列表请求
 * 返回房间列表
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_LIST_REQ)
public class RoomListReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(RoomListReqExecutor.class);

    @Override
    public void run() {
        RoomListResMsg resMsg = new RoomListResMsg(System.currentTimeMillis());;
        try {
            resMsg.setRoomList(RoomService.getRoomList());
        }catch (Exception e){
            e.printStackTrace();
            resMsg.setErrMsg("获取房间失败!");
        }
        //返回结果给客户端
        channel.writeAndFlush(resMsg);

    }
}
