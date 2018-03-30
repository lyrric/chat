package com.play001.gobang.server.exec;

import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.server.entity.ClientData;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import org.apache.log4j.Logger;

import java.util.UUID;


/**
 * 登陆请求
 */
@MsgAnnotation(msgType = ClientMsgType.LOGIN_REQ)
public class LoginReqExecutor extends BaseExecutor {

    private final Logger logger = Logger.getLogger(LoginReqExecutor.class);

    @Override
    public void run() {
        LoginReqMsg loginReqMsg = (LoginReqMsg)baseMsg;
        String username =  loginReqMsg.getUser().getUsername();
        logger.info("有用户登陆,用户名为" + username);
        //生成key
        String userKey = UUID.randomUUID().toString();

        //保存登陆信息
        ClientData client = new ClientData();
        client.setChannel(channel);
        client.setUsername(username);
        client.setUserKey(userKey);
        client.setLoginTime(System.currentTimeMillis());
        ClientService.add(username, client);
        //返回给客户端
        LoginResMsg loginResMsg = new LoginResMsg(userKey, System.currentTimeMillis());
        channel.writeAndFlush(loginResMsg);
        logger.info("用户"+username+"登陆成功, userKey="+userKey);
    }
}
