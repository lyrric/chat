package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Player;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.server.CompetitorDataResMsg;
import org.apache.log4j.Logger;


/**
 * 返回游戏对手信息
 */
@MsgAnnotation(msgType = ClientMsgType.COMPETITOR_DATA_REQ)
public class CompetitorDataReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(CompetitorDataReqExecutor.class);

    @Override
    public void run() {
        User user = baseMsg.getUser();
        CompetitorDataResMsg resMsg = new CompetitorDataResMsg(System.currentTimeMillis());
        try {
            Integer roomId = ClientService.getByUsername(user.getUsername()).getRoomId();
            ServerGameData gameData = GameService.getGameData(roomId);
            if(gameData.playerCount() != 2){
                //什么都不做
                return ;
            }
            String competitorName = gameData.getCompetitorName(user.getUsername());
            resMsg.setCompetitor(new Player(competitorName, null, null));
        }catch (Exception e){
            e.printStackTrace();
            resMsg.setErrMsg("获取房间数据失败!");
        }
        //返回结果给客户端
        channel.writeAndFlush(resMsg);

    }
}
