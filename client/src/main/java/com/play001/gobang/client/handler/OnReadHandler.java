package com.play001.gobang.client.handler;

import com.play001.gobang.client.exec.ExecDispatcher;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;


public class OnReadHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = Logger.getLogger(OnReadHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        ExecDispatcher.dispatch(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("channelReadComplete");
    }
}
