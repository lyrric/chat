package com.play001.gobang.client.exec;

import com.play001.gobang.client.service.UserService;
import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.client.ui.frame.LoginFrame;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import com.play001.gobang.support.entity.msg.server.RoomEnterResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 登陆返回key
 */
@MsgAnnotation(msgType = ServerMsgType.ROOM_ENTER_RES)
public class RoomEnterExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(RoomEnterExecutor.class);


    @Override
    public void run() {
        RoomFrame roomFrame = UiFactory.getRoomFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("进入房间失败:"+baseMsg.getErrMsg());
            ServiceFactory.getUserService().getUser().setRoomId(null);
            roomFrame.enterFailed(baseMsg.getErrMsg());
            return;
        }
        logger.info("进入房间成功");
        //进入房间成
        roomFrame.enterSuccess();
    }
}
