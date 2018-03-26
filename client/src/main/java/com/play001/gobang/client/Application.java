package com.play001.gobang.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * spring 启动
 */

@ComponentScan(basePackages = "com.play001.gobang.client")
public class Application {

    private final int port = 8808;
    private final String host = "127.0.0.1";

    @Autowired
    private ClientChannelInitializer clientChannelInitializer;
    /**
     * netty启动
     */
    @PostConstruct
    public void run(){
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(clientChannelInitializer);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class);
    }
}
