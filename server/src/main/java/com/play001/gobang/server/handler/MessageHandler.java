package com.play001.gobang.server.handler;

import com.play001.gobang.server.entity.ClientData;
import com.play001.gobang.server.exec.ExecDispatcher;
import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.client.ClientMsgType;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
@ChannelHandler.Sharable
public class MessageHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(MessageHandler.class);
    /**
     * 连接建立后,生成key发送给客户端
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.info(ctx.channel().remoteAddress()+", 连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        ClientBaseMsg baseMsg = (ClientBaseMsg)msg;
        //非登陆请求都需要进行登陆校验
        if(baseMsg == null){
            return;
        }
        if(ClientMsgType.LOGIN_REQ != baseMsg.getType()){
            //数据是否异常
            if(baseMsg.getUser() == null){
               return;
            }
            User user =  baseMsg.getUser();
            //是否传递了用户信息
            if(user.getUsername() == null || user.getUserKey() == null){
                return;
            }
            ClientData clientData = ClientService.getByUsername(user.getUsername());
            //用户信息是否存在,并且key是否正确
            if(clientData == null || clientData.getUserKey() == null || !user.getUserKey().equals(clientData.getUserKey())) {
                return;
            }

        }
        //分发任务
        ExecDispatcher.dispatch(ctx.channel(), msg);
    }

}
