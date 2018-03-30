package com.play001.gobang.client;

import com.play001.gobang.client.handler.ExceptionHandler;
import com.play001.gobang.client.handler.OnReadHandler;
import com.play001.gobang.client.util.ServerMsgDecoder;
import com.play001.gobang.support.util.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //编码解码器
        pipeline.addLast(new MsgEncoder());

        //客户端解析服务器发送过来的数据ServerMsgDecoder
        pipeline.addLast(new ServerMsgDecoder());

        pipeline.addLast(new ExceptionHandler());
        pipeline.addLast(new OnReadHandler());

    }
}
