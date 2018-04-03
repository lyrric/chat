package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ReadyChangeResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;

/**
 * 用户准备事件
 */
@MsgAnnotation(msgType = ServerMsgType.READY_CHANGE)
public class ReadyChangeExecutor extends BaseExecutor{

    private Logger logger = Logger.getLogger(ReadyChangeExecutor.class);
    @Override
    public void run() {
        ReadyChangeResMsg resMsg = (ReadyChangeResMsg)baseMsg;
        logger.info("收到消息:"+resMsg.toString());
        UIFactory.getGameFrame().readyChange(resMsg.getUsername(), resMsg.getReady());
    }
}
