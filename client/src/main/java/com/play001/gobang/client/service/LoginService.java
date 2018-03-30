package com.play001.gobang.client.service;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import org.apache.log4j.Logger;

public class LoginService {

    private Logger logger = Logger.getLogger(LoginService.class);
    /**
     * 登陆
     */
    public void login(String username){
        logger.info(username + "登陆");
        try {
            NettyService nettyService = ServiceFactory.getNettyService();
            LoginReqMsg msg = new LoginReqMsg(System.currentTimeMillis());
            User user = new User();
            user.setUsername(username);
            //保存user
            ServiceFactory.getUserService().setUser(user);
            nettyService.send(msg);
        }catch (Exception e){
            e.printStackTrace();
            UiFactory.getLoginFrame().loginFailed("登陆失败");
        }

    }
}
