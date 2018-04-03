package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.ClientTextMsg;
import com.play001.gobang.support.entity.msg.server.ServerTextMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;

//文本消息
@MsgAnnotation(msgType = ClientMsgType.TEXT)
public class TextMsgExecutor extends BaseExecutor {

    private Logger logger = Logger.getLogger(TextMsgExecutor.class);
    @Override
    public void run() {
        ClientTextMsg textMsg = (ClientTextMsg)baseMsg;
        logger.info("收到文本消息:"+textMsg.toString());
        User user = textMsg.getUser();
        ServerTextMsg serverTextMsg = new ServerTextMsg(System.currentTimeMillis(), user.getUsername(), textMsg.getText());
        Integer roomId = ClientService.getByUsername(user.getUsername()).getRoomId();
        ServerGameData gameData = GameService.getGameData(roomId);
        if(gameData.playerCount() == 2){
            String competitorName = gameData.getCompetitorName(user.getUsername());
            Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
            logger.info("发送文本消息:"+serverTextMsg.toString());
            competitorChannel.writeAndFlush(serverTextMsg);
        }
    }
}
