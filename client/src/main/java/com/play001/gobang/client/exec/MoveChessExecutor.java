package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.server.MoveChessResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;

//落子消息
@MsgAnnotation(msgType = ServerMsgType.MOVE_CHESS)
public class MoveChessExecutor extends BaseExecutor{

    private Logger logger = Logger.getLogger(MoveChessExecutor.class);
    @Override
    public void run() {
        MoveChessResMsg resMsg = (MoveChessResMsg)baseMsg;
        logger.info("对手落子:x="+resMsg.getX()+". y="+resMsg.getY());
        UIFactory.getGameFrame().competitorMove(resMsg.getX(), resMsg.getY());
    }
}
