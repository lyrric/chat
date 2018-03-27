package com.play001.gobang.client.exec;

import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;
import com.play001.gobang.support.util.ClassUtil;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 将handler消息分发给各个executor
 */
public class ExecDispatcher {

    private Logger logger = Logger.getLogger(ExecDispatcher.class);
    //最大线程数
    private static final int MAX_THREAD_NUM = 50;

    //线程
    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_NUM);

    //判断消息类型,分发任务
    public static void dispatch(Object msgObj) throws IllegalAccessException, InstantiationException {
        ServerBaseMsg baseMsg = (ServerBaseMsg)msgObj;
        Class<?> execClass = ClassUtil.getExecutorClassByType(baseMsg.getType());
        BaseExecutor executor = (BaseExecutor)execClass.newInstance();
        executorService.submit(executor);
    }
}
