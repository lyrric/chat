package com.play001.gobang.client.service;

/**
 * service工厂,用于获取各种service
 */
public class ServiceFactory {
    private static LoginService loginService;
    private static UserService userService;
    private static NettyService nettyService;

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
}
