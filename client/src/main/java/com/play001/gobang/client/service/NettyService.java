package com.play001.gobang.client.service;

import com.google.gson.Gson;
import com.play001.gobang.client.ClientChannelInitializer;
import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import static com.play001.gobang.client.Config.host;
import static com.play001.gobang.client.Config.port;

/**
 * 封装netty操作
 */
public class NettyService {

    private final Logger logger = Logger.getLogger(NettyService.class);
    private SocketChannel socketChannel = null;
    private Gson gson = new Gson();
    /**
     * netty启动连接
     */
    public void connect() throws InterruptedException {
        UserService userService = ServiceFactory.getUserService();
        //已经连接过了便不需要再连接了
        if(socketChannel != null){
            return ;
        }
        logger.info("netty连接开始");
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientChannelInitializer());
            ChannelFuture future = bootstrap.connect(host, port).sync();

            //保存socketChannel以便以后发送消息
            socketChannel = ((SocketChannel) future.channel());
            //异步等待关闭
            new Thread(()->{
                try {
                    future.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }catch (Exception e){
            e.printStackTrace();
            workerGroup.shutdownGracefully();
            throw e;
        }
        logger.info("netty连接结束");
    }


    /**
     * 发送数据
     * @param baseMsg 消息
     * @return 执行返回结果
     */
    public ChannelFuture send(ClientBaseMsg baseMsg){
        //自动将user信息置入
        baseMsg.setUser(ServiceFactory.getUserService().getUser());
        logger.info("发送消息:" + baseMsg.toString());
        return socketChannel.writeAndFlush(baseMsg);
    }
}
