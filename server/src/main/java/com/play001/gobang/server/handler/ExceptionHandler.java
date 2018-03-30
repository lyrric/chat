package com.play001.gobang.server.handler;

import com.play001.gobang.server.service.ClientService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ExceptionHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = Logger.getLogger(ExceptionHandler.class);

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.info(ctx.channel().remoteAddress()+"-下线了");
        ClientService.removeByChannel(ctx.channel());
        ctx.close();
    }
}
