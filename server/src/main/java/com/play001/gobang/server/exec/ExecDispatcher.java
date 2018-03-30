package com.play001.gobang.server.exec;

import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.util.ClassUtil;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.unix.Socket;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将handler消息分发给各个executor
 */
public class ExecDispatcher {

    private static Logger logger = Logger.getLogger(ExecDispatcher.class);
    //最大线程数
    private static final int MAX_THREAD_NUM = 50;

    //线程
    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);

    //判断消息类型,分发任务
    public static void dispatch(Channel channel, Object msgObj) throws IllegalAccessException, InstantiationException {
        ClientBaseMsg baseMsg = (ClientBaseMsg)msgObj;
        logger.info("收到消息:"+baseMsg.toString());
        Class<?> execClass = ClassUtil.getExecutorClassByType(baseMsg.getType());
        BaseExecutor executor = (BaseExecutor)execClass.newInstance();
        executor.setBaseMsg(baseMsg);
        executor.setChannel(channel);
        executorService.execute(executor);
    }
}
