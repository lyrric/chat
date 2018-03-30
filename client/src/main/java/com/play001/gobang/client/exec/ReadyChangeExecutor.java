package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ReadyChangeResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;

/**
 * 用户准备状态发生变化
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_START)
public class ReadyChangeExecutor extends BaseExecutor{
    @Override
    public void run() {
        UIFactory.getGameFrame().startGame();
    }
}
