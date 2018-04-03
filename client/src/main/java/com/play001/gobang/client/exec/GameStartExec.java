package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.GameStartMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;

/**
 * 游戏开始
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_START)
public class GameStartExec extends BaseExecutor{

    private Logger logger = Logger.getLogger(GameStartExec.class);
    @Override
    public void run() {
        GameStartMsg msg = (GameStartMsg)baseMsg;
        logger.info("收到消息:游戏开始");
        UIFactory.getGameFrame().startGame(msg.getBlacknessUsername());
    }
}
