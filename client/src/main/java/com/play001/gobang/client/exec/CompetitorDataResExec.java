package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.client.ui.frame.GameFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.CompetitorDataResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 对手信息
 */
@MsgAnnotation(msgType = ServerMsgType.COMPETITOR_DATA_RES)
public class CompetitorDataResExec extends BaseExecutor {

    private final Logger logger = Logger.getLogger(CompetitorDataResExec.class);

    @Override
    public void run() {
        GameFrame gameFrame = UIFactory.getGameFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("获取对手信息失败:"+baseMsg.getErrMsg());
            gameFrame.getGameDataFailed(baseMsg.getErrMsg());
            return;
        }
        CompetitorDataResMsg resMsg = (CompetitorDataResMsg)baseMsg;
        logger.info("获取对手信息成功, 对手名:"+resMsg.getCompetitor());
        //进入房间成功后,先向
        gameFrame.setCompetitor(resMsg.getCompetitor());
    }
}
