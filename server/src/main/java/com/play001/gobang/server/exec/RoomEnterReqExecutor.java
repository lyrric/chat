package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.server.service.GameService;
import com.play001.gobang.server.service.RoomService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Room;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.RoomEnterReqMsg;
import com.play001.gobang.support.entity.msg.server.GameDataResMsg;
import com.play001.gobang.support.entity.msg.server.RoomEnterResMsg;
import com.play001.gobang.support.entity.msg.server.UserEnterMsg;
import io.netty.channel.Channel;
import org.apache.log4j.Logger;


/**
 * 进入房间
 */
@MsgAnnotation(msgType = ClientMsgType.ROOM_ENTER_REQ)
public class RoomEnterReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(RoomEnterReqExecutor.class);

    @Override
    public void run() {
        RoomEnterResMsg resMsg = new RoomEnterResMsg(System.currentTimeMillis());
        try {
            RoomEnterReqMsg reqMsg = (RoomEnterReqMsg)baseMsg;
            String username =  reqMsg.getUser().getUsername();
            Integer roomId = reqMsg.getUser().getRoomId();
            //判断房间是否存在
            if(roomId == null){
                resMsg.setErrMsg("房间不存在");
            }else{
                Room room = RoomService.get(roomId);
                if(room == null){
                    resMsg.setErrMsg("房间不存在");
                }else if(room.getUserCount() > 1){
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
                        String competitorName = GameService.getOtherUsername(roomId, username);
                        Channel competitorChannel = ClientService.getByUsername(competitorName).getChannel();
                        //发送游戏最新数据
                        UserEnterMsg userEnterMsg = new UserEnterMsg(System.currentTimeMillis(), username);
                        competitorChannel.writeAndFlush(userEnterMsg);
                    }
                    logger.info("用户:" + username+"进入房间:" + reqMsg.getUser().getRoomId());
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
