package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.GameOverMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;

@MsgAnnotation(msgType = ServerMsgType.GAME_OVER)
public class GameOverExec extends BaseExecutor{

    private Logger logger = Logger.getLogger(GameOverExec.class);
    @Override
    public void run() {
        GameOverMsg msg = (GameOverMsg)baseMsg;
        logger.info("收到消息: 游戏结束");
        UIFactory.getGameFrame().gameOver(msg.getWinner());
    }
}
