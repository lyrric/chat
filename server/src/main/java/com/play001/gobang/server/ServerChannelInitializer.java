package com.play001.gobang.server;

import com.play001.gobang.server.handler.ExceptionHandler;
import com.play001.gobang.server.handler.MessageHandler;
import com.play001.gobang.support.util.MsgDecoder;
import com.play001.gobang.support.util.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel>{
    @Autowired
    private ExceptionHandler exceptionHandler;
    @Autowired
    private MessageHandler goOnlineHandler;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

//        //添加对象解码器 负责对序列化POJO对象进行解码 设置对象序列化最大长度为1M 防止内存溢出
//        //设置线程安全的WeakReferenceMap对类加载器进行缓存 支持多线程并发访问  防止内存溢出
//        pipeline.addLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
//        //添加对象编码器 在服务器对外发送消息的时候自动将实现序列化的POJO对象编码
//        pipeline.addLast(new ObjectEncoder());

        //编码解码器
        pipeline.addLast(new MsgEncoder());
        pipeline.addLast(new MsgDecoder());
        pipeline.addLast(exceptionHandler);
        pipeline.addLast(goOnlineHandler);


    }
}
