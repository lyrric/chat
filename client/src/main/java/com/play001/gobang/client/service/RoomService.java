package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import com.play001.gobang.support.entity.msg.client.RoomCreateReqMsg;
import com.play001.gobang.support.entity.msg.client.RoomEnterReqMsg;
import com.play001.gobang.support.entity.msg.client.RoomListReqMsg;
import org.apache.log4j.Logger;

public class RoomService {

    private Logger logger = Logger.getLogger(RoomService.class);

    /**
     * 获取房间列表
     */
    public void getRoomList(){
        try {
            RoomListReqMsg msg = new RoomListReqMsg(System.currentTimeMillis());
            ServiceFactory.getNettyService().send(msg);
        }catch (Exception e){
            e.printStackTrace();
            UiFactory.getRoomFrame().enterFailed("刷新房间失败");
        }
    }
    /**
     *  创建房间
     */
    public void create(){
        try {
            RoomCreateReqMsg msg = new RoomCreateReqMsg(System.currentTimeMillis());
            ServiceFactory.getNettyService().send(msg);
        }catch (Exception e){
            e.printStackTrace();
            UiFactory.getRoomFrame().enterFailed("创建房间失败");
        }
    }
    /**
     * 进入房间
     */
    public void enter(Integer roomId){
        User user =  ServiceFactory.getUserService().getUser();
        try {
            RoomEnterReqMsg msg = new RoomEnterReqMsg(System.currentTimeMillis());
            user.setRoomId(roomId);
            ServiceFactory.getNettyService().send(msg);
        }catch (Exception e){
            e.printStackTrace();
            UiFactory.getRoomFrame().enterFailed("进入房间失败");
        }
    }
}
