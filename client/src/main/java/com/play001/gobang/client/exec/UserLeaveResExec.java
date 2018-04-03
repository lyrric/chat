package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 对手离开房间
 */
@MsgAnnotation(msgType = ServerMsgType.USER_LEAVE)
public class UserLeaveResExec extends BaseExecutor {

    @Override
    public void run() {
        UIFactory.getGameFrame().CompetitorLeave();
    }
}
