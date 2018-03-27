package com.play001.gobang.client.exec;

import com.play001.gobang.client.service.ServiceFactory;
import com.play001.gobang.client.service.UserService;
import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerMsgType;
import org.apache.log4j.Logger;


/**
 * 登陆返回key
 */
@MsgAnnotation(msgType = ServerMsgType.LOGIN_RES)
public class LoginResExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(LoginResExecutor.class);

    public LoginResExecutor(ServerBaseMsg baseMsg) {
        super(baseMsg);
    }

    @Override
    public void run() {
        logger.info("LoginResExecutor执行");
        if(baseMsg.getErrMsg() != null){
            UiFactory.getLoginFrame().setLoginBtnEnable();
            return;
        }
        LoginResMsg loginResMsg = (LoginResMsg)baseMsg;
        UserService userService = ServiceFactory.getUserService();
        //保存userKey
        userService.getUser().setUserKey(loginResMsg.getUserKey());
        logger.info("登陆成功, userKey = "+loginResMsg.getUserKey());
        logger.info("LoginResExecutor结束");

    }
}
