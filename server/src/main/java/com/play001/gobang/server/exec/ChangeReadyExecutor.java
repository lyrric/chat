package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.ChessType;
import com.play001.gobang.support.entity.Player;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.ReadyChangeReqMsg;
import com.play001.gobang.support.entity.msg.server.GameStartMsg;
import com.play001.gobang.support.entity.msg.server.ReadyChangeResMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

/**
 * 用户准备事件
 */
@MsgAnnotation(msgType = ClientMsgType.READY_CHANGE)
public class ChangeReadyExecutor extends BaseExecutor {

    private Logger logger = Logger.getLogger(ChangeReadyExecutor.class);
    @Override
    public void run() {
        User user = baseMsg.getUser();
        Integer roomId = ClientService.getByUsername(user.getUsername()).getRoomId();
        Boolean ready = ((ReadyChangeReqMsg)baseMsg).getReady();
        logger.info("玩家:"+user.getUsername()+", 改变准备状态为" + ready);
        ServerGameData gameData = GameService.getGameData(roomId);
        gameData.setUserReady(user.getUsername(), ready);
        ReadyChangeResMsg resMsg = new ReadyChangeResMsg(System.currentTimeMillis(), user.getUsername(), ready);

        //判断房间有无对手
        if(gameData.playerCount() == 2){
            //通知对方
            String competitorName = gameData.getCompetitorName(user.getUsername());
            logger.info("通知对手玩家:"+competitorName+", 准备状态为" + ready);
            Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
            competitorChannel.writeAndFlush(resMsg);
            //判断是否都已经准备了
            if(gameData.isAllReady()){
                logger.info("房间:"+roomId+",开始游戏");
                //初始化数据
                gameData.initChessBord();

                String hostName = RoomService.get(roomId).getHostUsername();
                Player hostPlayer = gameData.getByUsername(hostName);
                Player otherPlayer = gameData.getByUsername(gameData.getCompetitorName(hostName));
                //设置先手
                //获取房主用户名,如果房主所执棋为null(此房间还没有开始过游戏)或者房主上次执棋为白色, 则设置房主为先手
                // 其它情况房主为后手方
                if(hostPlayer.getChessType() == null || hostPlayer.getChessType() == ChessType.WHITENESS){
                    hostPlayer.setChessType(ChessType.BLACKNESS);
                    otherPlayer.setChessType(ChessType.WHITENESS);
                    gameData.setActionPlayer(hostPlayer);
                } else {
                    hostPlayer.setChessType(ChessType.WHITENESS);
                    otherPlayer.setChessType(ChessType.BLACKNESS);
                    gameData.setActionPlayer(otherPlayer);
                }
                //游戏开始, 发送先手方名字
                GameStartMsg gameStartMsg = new GameStartMsg(System.currentTimeMillis(), gameData.getBlacknessPlayer().getUsername());
                channel.writeAndFlush(gameStartMsg);
                competitorChannel.writeAndFlush(gameStartMsg);
                //将双方的准备状态设置为false
                gameData.getPlayer1().setReady(false);
                gameData.getPlayer2().setReady(false);
            }
        }
    }
}
