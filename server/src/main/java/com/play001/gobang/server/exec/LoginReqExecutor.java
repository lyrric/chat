package com.play001.gobang.server.exec;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.Client;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import org.apache.log4j.Logger;

import java.util.UUID;


/**
 * 登陆请求
 */
@MsgAnnotation(msgType = ClientMsgType.LOGIN_REQ)
public class LoginReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(LoginReqExecutor.class);

    public LoginReqExecutor(ClientBaseMsg baseMsg) {
        super(baseMsg);
    }

    @Override
    public void run() {
        LoginReqMsg loginReqMsg = (LoginReqMsg)baseMsg;
        logger.info("有用户登陆,用户名为" + loginReqMsg.getUsername());
        Client user = loginReqMsg.getUser();
        String userKey = UUID.randomUUID().toString();
        user.setUserKey(userKey);
        logger.info("用户"+loginReqMsg.getUsername()+"登陆成功, userKey="+userKey);
    }
}
