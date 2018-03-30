package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ReadyChangeResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 游戏开始
 */
@MsgAnnotation(msgType = ServerMsgType.READY_CHANGE)
public class GameStartExecutor extends BaseExecutor{
    @Override
    public void run() {
        ReadyChangeResMsg resMsg = (ReadyChangeResMsg)baseMsg;
        UIFactory.getGameFrame().readyChange(resMsg.getUsername(), resMsg.getReady());
    }
}
