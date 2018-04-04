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
        logger.info("收到登陆消息:"+loginReqMsg.toString());
        LoginResMsg loginResMsg = new LoginResMsg();
        String username =  loginReqMsg.getUser().getUsername();
        try {
            //判断登录名是否重复
            if(ClientService.getByUsername(username) != null){
                loginResMsg.setErrMsg("用户名已经被使用了!");

            }else{
                //生成key
                String userKey = UUID.randomUUID().toString();
                //保存登陆信息
                ClientData client = new ClientData();
                client.setChannel(channel);
                client.setUsername(username);
                client.setUserKey(userKey);
                client.setLoginTime(System.currentTimeMillis());
                ClientService.add(username, client);
                loginResMsg.setTime(System.currentTimeMillis());
                loginResMsg.setUserKey(userKey);
            }
        }catch (Exception e){
            e.printStackTrace();
            loginResMsg.setErrMsg("登陆失败!");
        }
        //返回给客户端
        channel.writeAndFlush(loginResMsg);
    }
}
