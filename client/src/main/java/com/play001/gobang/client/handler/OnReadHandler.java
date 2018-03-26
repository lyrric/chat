package com.play001.gobang.client.handler;

import com.play001.gobang.support.entity.msg.BaseMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class OnReadHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = Logger.getLogger(OnReadHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        BaseMsg baseMsg = (BaseMsg)msg;
        System.out.println("收到消息, 消息类型="+baseMsg.getType());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("channelReadComplete");
    }
}
