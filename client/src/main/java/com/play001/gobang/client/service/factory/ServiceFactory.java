package com.play001.gobang.client.service.factory;

import com.play001.gobang.client.service.*;
import sun.misc.resources.Messages;

/**
 * service工厂,用于获取各种service
 */
public class ServiceFactory {
    private static LoginService loginService;
    private static UserService userService;
    private static NettyService nettyService;
    private static RoomService roomService;
    private static GameService gameService;
    private static MessageService messageService;
    public static LoginService getLoginService() {
        if(loginService == null){
            loginService = new LoginService();
        }
        return loginService;
    }

    public static UserService getUserService() {
        if(userService == null){
            userService = new UserService();
        }
        return userService;
    }

    public static NettyService getNettyService() {
        if(nettyService == null){
            nettyService = new NettyService();
        }
        return nettyService;
    }

    public static RoomService getRoomService() {
        if(roomService == null){
            roomService = new RoomService();
        }
        return roomService;
    }

    public static GameService getGameService() {
        if(gameService == null){
            gameService = new GameService();
        }
        return gameService;
    }

    public static MessageService getMessageService() {
        if(messageService == null){
            messageService = new MessageService();
        }
        return messageService;
    }
}
