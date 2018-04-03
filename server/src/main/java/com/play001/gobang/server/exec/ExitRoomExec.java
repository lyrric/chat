package com.play001.gobang.server.exec;

import com.play001.gobang.server.entity.ClientData;
import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.GameStatus;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.UserLeaveResMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

/**
 * 玩家离开房间
 */
@MsgAnnotation(msgType = ClientMsgType.EXIT_ROOM)
public class ExitRoomExec extends BaseExecutor {

    private Logger logger = Logger.getLogger(ExitRoomExec.class);

    @Override
    public void run() {
        String username = baseMsg.getUser().getUsername();
        logger.info("收到消息,退出房间:"+baseMsg.toString());
        //1.获取用户信息
        ClientData clientData = ClientService.getByUsername(username);
        if(clientData == null){
            return ;
        }
        Integer roomId = clientData.getRoomId();
        if(roomId != null){
            //获取房间信息
            Room room = RoomService.get(roomId);
            if(room != null){
                //2.1获取游戏数据
                ServerGameData gameData = GameService.getGameData(roomId);
                if(gameData != null){
                    //房间有另一个人
                    if(gameData.playerCount() == 2){
                        //获取对手用户名
                        String competitorName = gameData.getCompetitorName(clientData.getUsername());
                        Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
                        //通知对方
                        UserLeaveResMsg userLeaveMsg = new UserLeaveResMsg(System.currentTimeMillis(), clientData.getUsername());
                        competitorChannel.writeAndFlush(userLeaveMsg);
                        room.setUserCount(1);
                        //从新设置房主
                        room.setHostUsername(competitorName);
                    }
                    //如果游戏已经开始,则结束游戏,并且初始化数据
                    if(gameData.getStatus() == GameStatus.START){
                        gameData.setStatus(GameStatus.WAITING_PLAYER);
                        gameData.setChessBoard(null);
                    }
                    //移除用户
                    gameData.removeByUsername(clientData.getUsername());
                    //如果移除后房间人数为0, 则移除游戏数据和房间数据
                    if(gameData.playerCount() == 0){
                        GameService.remove(clientData.getRoomId());
                        RoomService.remove(roomId);
                    }
                }
            }
        }
    }
}
