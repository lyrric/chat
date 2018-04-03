package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 登陆返回key
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_ENTER_RES)
public class EnterRoomExec extends BaseExecutor {

    private final Logger logger = Logger.getLogger(EnterRoomExec.class);


    @Override
    public void run() {
        RoomFrame roomFrame = UIFactory.getRoomFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("进入房间失败:"+baseMsg.getErrMsg());
            roomFrame.enterFailed(baseMsg.getErrMsg());
            return;
        }
        logger.info("进入房间成功");
        //进入房间成
        roomFrame.enterSuccess();
    }
}
