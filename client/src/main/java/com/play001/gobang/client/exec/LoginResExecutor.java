package com.play001.gobang.client.exec;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.service.UserService;
import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.client.ui.frame.LoginFrame;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 登陆返回key
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class LoginResExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(LoginResExecutor.class);


    @Override
    public void run() {
        logger.info("LoginResExecutor执行");
        LoginFrame loginFrame = UIFactory.getLoginFrame();
        if(baseMsg.getErrMsg() != null){
            loginFrame.loginFailed(baseMsg.getErrMsg());
            return;
        }
        LoginResMsg loginResMsg = (LoginResMsg)baseMsg;
        UserService userService = ServiceFactory.getUserService();
        //保存userKey
        userService.getUser().setUserKey(loginResMsg.getUserKey());
        logger.info("登陆成功, userKey = "+loginResMsg.getUserKey());
        logger.info("LoginResExecutor结束");
        loginFrame.loginSuccess();
    }
}
