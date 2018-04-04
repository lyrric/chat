package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.EnterRoomReqMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import com.play001.gobang.support.entity.msg.server.UserEnterMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;


/**
 * 进入房间
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_ENTER_REQ)
public class EnterRoomReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(EnterRoomReqExecutor.class);

    @Override
    public void run() {
        ServerBaseMsg resMsg = new ServerBaseMsg(ServerMsgType.ROOM_ENTER_RES, System.currentTimeMillis());
        try {
            String username =  baseMsg.getUser().getUsername();
            EnterRoomReqMsg reqMsg = (EnterRoomReqMsg)baseMsg;
            Integer roomId = reqMsg.getRoomId();
            //判断房间是否存在
            if(roomId == null){
                resMsg.setErrMsg("房间不存在");
            }else{
                Room room = RoomService.get(roomId);
                if(room == null){
                    resMsg.setErrMsg("房间不存在");
                    channel.writeAndFlush(resMsg);
                    return;
                }
                //同步锁住房间信息
                synchronized (room){
                    if(room.getUserCount() > 1){
                        resMsg.setErrMsg("房间已满");
                    }else{
                        //进入房间
                        GameService.add(roomId, username);
                        //更新用户状态
                        ClientService.enterRoom(username, roomId);
                        //用户数量+1
                        room.increaseUserCount();
                        //判断是否需要通知另一个人
                        if(room.getUserCount() == 2){
                            //获取对手名字
                            ServerGameData gameData = GameService.getGameData(roomId);
                            String competitorName = gameData.getCompetitorName(username);
                            Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
                            //发送游戏最新数据
                            UserEnterMsg userEnterMsg = new UserEnterMsg(System.currentTimeMillis(), username);
                            competitorChannel.writeAndFlush(userEnterMsg);
                        }
                        logger.info("用户:" + username+"进入房间:" + roomId);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            resMsg.setErrMsg("进入房间失败!");
        }
        //返回结果给客户端
        channel.writeAndFlush(resMsg);
    }
}
