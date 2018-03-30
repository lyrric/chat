package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import org.apache.log4j.Logger;

public class RoomService {

    private Logger logger = Logger.getLogger(RoomService.class);

    /**
     * 获取房间列表
     */
    public void getRoomList(){
        try {

            ClientBaseMsg reqMsg = new ClientBaseMsg(ClientMsgType.ROOM_LIST_REQ, System.currentTimeMillis());
            ServiceFactory.getNettyService().send(reqMsg);
        }catch (Exception e){
            e.printStackTrace();
            UIFactory.getRoomFrame().enterFailed("刷新房间失败");
        }
    }
    /**
     *  创建房间
     */
    public void create(){
        try {
            ClientBaseMsg reqMsg = new ClientBaseMsg(ClientMsgType.ROOM_CREATE_REQ, System.currentTimeMillis());
            ServiceFactory.getNettyService().send(reqMsg);
        }catch (Exception e){
            e.printStackTrace();
            UIFactory.getRoomFrame().enterFailed("创建房间失败");
        }
    }
    /**
     * 进入房间
     */
    public void enter(Integer roomId){
        User user =  ServiceFactory.getUserService().getUser();
        try {
            ClientBaseMsg reqMsg = new ClientBaseMsg(ClientMsgType.ROOM_ENTER_REQ, System.currentTimeMillis());
            user.setRoomId(roomId);
            ServiceFactory.getNettyService().send(reqMsg);
        }catch (Exception e){
            e.printStackTrace();
            UIFactory.getRoomFrame().enterFailed("进入房间失败");
        }
    }
}
