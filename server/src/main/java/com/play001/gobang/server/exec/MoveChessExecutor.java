package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.MoveChessReqMsg;
import com.play001.gobang.support.entity.msg.server.GameOverMsg;
import com.play001.gobang.support.entity.msg.server.MoveChessResMsg;
import io.netty.channel.Channel;

/**
 * 下棋
 */
@MsgAnnotation(msgType = ClientMsgType.MOVE_CHESS)
public class MoveChessExecutor extends BaseExecutor{


    @Override
    public void run() {
        MoveChessReqMsg reqMsg = (MoveChessReqMsg)baseMsg;
        User user = reqMsg.getUser();
        Integer roomId = ClientService.getByUsername(user.getUsername()).getRoomId();
        ServerGameData gameData = GameService.getGameData(roomId);
        if(gameData.moveChess(user.getUsername(), reqMsg.getX(), reqMsg.getY())){
            String competitorName = gameData.getCompetitorName(user.getUsername());
            Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
            MoveChessResMsg resMsg = new MoveChessResMsg(System.currentTimeMillis(),user.getUsername(), reqMsg.getX(), reqMsg.getY());
            competitorChannel.writeAndFlush(resMsg);
            //落子后,检查当前落子是否是形成五连
            if(gameData.getChessBoard().checkWin(reqMsg.getX(), reqMsg.getY())){
                GameOverMsg msg = new GameOverMsg(System.currentTimeMillis(), user.getUsername());
                channel.writeAndFlush(msg);
                competitorChannel.writeAndFlush(msg);
            }
        }

    }
}
