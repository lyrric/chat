package com.play001.gobang.client;

import com.play001.gobang.client.handler.ExceptionHandler;
import com.play001.gobang.client.handler.OnReadHandler;
import com.play001.gobang.support.util.MsgDecoder;
import com.play001.gobang.support.util.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    private ExceptionHandler exceptionHandler;
    private OnReadHandler goOnlineHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //编码解码器
        pipeline.addLast(new MsgEncoder());
        pipeline.addLast(new MsgDecoder());

        pipeline.addLast(exceptionHandler);
        pipeline.addLast(goOnlineHandler);

    }
}
