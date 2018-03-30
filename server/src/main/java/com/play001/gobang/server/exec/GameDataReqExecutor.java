package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.GameDataReqMsg;
import com.play001.gobang.support.entity.msg.server.GameDataResMsg;
import com.play001.gobang.support.entity.msg.server.RoomListResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 处理游戏数据请求
 * 返回游戏数据
 */
@MsgAnnotation(msgType = ClientMsgType.GAME_DATA_REQ)
public class GameDataReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(GameDataReqExecutor.class);

    @Override
    public void run() {
        GameDataResMsg resMsg = new GameDataResMsg(System.currentTimeMillis());
        try {
            GameDataReqMsg reqMsg = (GameDataReqMsg)baseMsg;
            resMsg.setGameData(GameService.getGameData(reqMsg.getUser().getRoomId()));
        }catch (Exception e){
            e.printStackTrace();
            resMsg.setErrMsg("获取房间数据失败!");
        }
        //返回结果给客户端
        channel.writeAndFlush(resMsg);

    }
}
