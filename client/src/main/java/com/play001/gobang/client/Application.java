package com.play001.gobang.client;

import com.play001.gobang.client.ui.UiFactory;
import com.play001.gobang.client.ui.frame.LoginFrame;
import com.play001.gobang.client.ui.frame.RoomFrame;
import com.play001.gobang.support.util.ClassUtil;
import io.netty.channel.socket.SocketChannel;
import org.apache.log4j.Logger;

import java.net.URL;

/**
 * spring 启动
 */

public class Application {

    private static Logger logger = Logger.getLogger(Application.class);


    public static void main(String[] args) throws Exception {
        logger.info("程序启动");
        //初始化msg类和msgExecutor类

        ClassUtil.initTypeToMsgClassMap("com.play001.gobang.support.entity.msg.client");
        ClassUtil.initTypeToMsgExecClassMap("com.play001.gobang.client.exec");


        UiFactory.getLoginFrame().setVisible(true);
    }
}
