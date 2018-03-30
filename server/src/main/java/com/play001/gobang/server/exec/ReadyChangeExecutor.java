package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.ReadyChangeReqMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.ReadyChangeResMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

/**
 * 用户准备事件
 */
@MsgAnnotation(msgType = ClientMsgType.READY_CHANGE)
public class ReadyChangeExecutor extends BaseExecutor {

    private Logger logger = Logger.getLogger(ReadyChangeExecutor.class);
    @Override
    public void run() {
        User user = baseMsg.getUser();
        Boolean ready = ((ReadyChangeReqMsg)baseMsg).getReady();
        logger.info("玩家:"+user.getUsername()+", 改变准备状态为" + ready);
        GameService.setUserReady(user.getRoomId(), user.getUsername(), ready);
        ReadyChangeResMsg resMsg = new ReadyChangeResMsg(System.currentTimeMillis(), user.getUsername(), ready);

        //判断房间有无对手
        if(GameService.getPlayerCount(user.getRoomId()) == 2){
            //通知对方
            String competitorName = GameService.getOtherUsername(user.getRoomId(), user.getUsername());
            logger.info("通知对手玩家:"+competitorName+", 准备状态为" + ready);
            Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
            competitorChannel.writeAndFlush(resMsg);
            //判断是否都已经准备了
            if(GameService.isAllReady(user.getRoomId())){
                logger.info("房间:"+user.getRoomId()+",开始游戏");
                //广播, 游戏开始
                ServerBaseMsg gameStartMsg = new ServerBaseMsg(ServerMsgType.GAME_START, System.currentTimeMillis());
                channel.writeAndFlush(gameStartMsg);
                competitorChannel.writeAndFlush(gameStartMsg);
            }
        }
    }
}
