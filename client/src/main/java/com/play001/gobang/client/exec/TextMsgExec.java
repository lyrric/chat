package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.ServerTextMsg;
import org.apache.log4j.Logger;

@MsgAnnotation(msgType = ServerMsgType.TEXT)
public class TextMsgExec extends BaseExecutor {

    private Logger logger = Logger.getLogger(TextMsgExec.class);
    @Override
    public void run() {
        ServerTextMsg textMsg = (ServerTextMsg)baseMsg;
        logger.info("收到文本消息:"+textMsg.toString());
        UIFactory.getGameFrame().textMsg(textMsg.getFromUsername(), textMsg.getText());
    }
}
