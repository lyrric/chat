package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.RoomListResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 返回房间列表
 */
@MsgAnnotation(msgType = ServerMsgType.ROMM_LIST_RES)
public class RoomListResExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(RoomListResExecutor.class);

    @Override
    public void run() {
        RoomFrame roomFrame = UiFactory.getRoomFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("获取房间列表失败:"+baseMsg.getErrMsg());
            roomFrame.getListFailed(baseMsg.getErrMsg());
            return;
        }
        RoomListResMsg resMsg = (RoomListResMsg)baseMsg;
        logger.info("获取房间列表成功, 房间数:"+resMsg.getRoomList().size());
        //进入房间成功后,先向
        roomFrame.updateList(resMsg.getRoomList());
    }
}
