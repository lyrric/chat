package com.play001.gobang.server.handler;

import com.play001.gobang.server.exec.ExecDispatcher;
import com.play001.gobang.server.service.ClientService;
import com.play001.gobang.support.entity.msg.client.LoginReqMsg;
import com.play001.gobang.support.entity.msg.server.LoginResMsg;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.springframework.stereotype.Component;

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
        ExecDispatcher.dispatch(ctx.channel(), msg);
    }
}
