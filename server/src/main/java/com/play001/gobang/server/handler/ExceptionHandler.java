package com.play001.gobang.server.handler;

import com.play001.gobang.server.entity.ClientData;
import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.entity.GameStatus;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.msg.server.UserLeaveResMsg;
import io.netty.channel.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(ExceptionHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        logger.info(channel.remoteAddress()+"-下线了");
        /*
         * 断开连接后的处理
         * 1.获取用户信息
         * 2.用户所在房间是否存在, 不存在->3, 存在->2.1
         *  2.1 获取游戏数据,是否有其它用户, 有
         *   2.11 发送用户离开消息
         */

        //1.获取用户信息
        ClientData clientData = ClientService.getByChannel(channel);
        if(clientData == null){
            return ;
        }
        ClientService.removeByChannel(channel);

        Integer roomId = clientData.getRoomId();

        if(roomId != null){
            Room room = RoomService.get(roomId);
            if(room != null){
                synchronized (room){
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
                        }
                        //如果游戏已经开始,则结束游戏,并且初始化数据
                        if(gameData.getStatus() == GameStatus.START){
                            gameData.setStatus(GameStatus.WAITING_PLAYER);
                            gameData.setChessBoard(null);
                        }
                        //移除用户
                        gameData.removeByUsername(clientData.getUsername());
                        //如果房间人数为0, 则移除游戏数据和房间数据
                        if(gameData.playerCount() == 0){
                            GameService.remove(clientData.getRoomId());
                            RoomService.remove(roomId);
                        }
                    }
                }

            }
        }
        ctx.channel().close();
        ctx.close();
    }
}
