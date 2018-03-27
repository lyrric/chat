package com.play001.gobang.server.handler;

import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MessageHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接建立后,生成key发送给客户端
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().id();
        System.out.println("建立连接");
    }

}
