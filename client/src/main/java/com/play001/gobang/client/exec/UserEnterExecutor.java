package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.UserEnterMsg;
import org.apache.log4j.Logger;

/**
 * 玩家进入消息
 */
@MsgAnnotation(msgType = ServerMsgType.USER_ENTER)
public class UserEnterExecutor extends BaseExecutor{

    private Logger logger = Logger.getLogger(UserEnterExecutor.class);
    @Override
    public void run() {
        UserEnterMsg userEnterMsg = (UserEnterMsg)baseMsg;
        logger.info("收到消息, 玩家进入房间:"+userEnterMsg.toString());
        UIFactory.getGameFrame().userEnter(userEnterMsg.getUsername());
    }
}
