package com.play001.gobang.client.exec;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.RoomCreateResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 创建房间
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_CREATE_RES)
public class RoomCreateResExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(RoomCreateResExecutor.class);


    @Override
    public void run() {
        RoomFrame roomFrame = UIFactory.getRoomFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("创建房间失败:"+baseMsg.getErrMsg());
            roomFrame.createFailed(baseMsg.getErrMsg());
            return;
        }
        RoomCreateResMsg resMsg = (RoomCreateResMsg)baseMsg;
        logger.info("创建房间成功");
        //保存房间ID
        ServiceFactory.getUserService().getUser().setRoomId(resMsg.getRoomId());
        //进入房间成功后
        roomFrame.createSuccess(resMsg.getRoomId());
    }
}
