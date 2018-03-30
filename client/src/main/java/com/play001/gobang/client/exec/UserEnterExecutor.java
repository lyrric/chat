package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.UserEnterMsg;

/**
 * 玩家进入消息
 */
@MsgAnnotation(msgType = ServerMsgType.USER_ENTER)
public class UserEnterExecutor extends BaseExecutor{
    @Override
    public void run() {
        UserEnterMsg userEnterMsg = (UserEnterMsg)baseMsg;
        UiFactory.getGameFrame().userEnter(userEnterMsg.getUsername());
    }
}
