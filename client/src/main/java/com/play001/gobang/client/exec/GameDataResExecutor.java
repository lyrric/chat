package com.play001.gobang.client.exec;

import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.client.ui.frame.GameFrame;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.GameDataResMsg;
import com.play001.gobang.support.entity.msg.server.RoomListResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 返回房间列表
 */
@MsgAnnotation(msgType = ServerMsgType.GAME_DATA_RES)
public class GameDataResExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(GameDataResExecutor.class);

    @Override
    public void run() {
        GameFrame gameFrame = UiFactory.getGameFrame();
        if(baseMsg.getErrMsg() != null){
            logger.info("获取游戏数据失败:"+baseMsg.getErrMsg());
            gameFrame.getGameDataFailed(baseMsg.getErrMsg());
            return;
        }
        GameDataResMsg resMsg = (GameDataResMsg)baseMsg;
        logger.info("获取房间数据成功");
        //进入房间成功后,先向
        gameFrame.updateGameData(resMsg.getGameData());
    }
}
