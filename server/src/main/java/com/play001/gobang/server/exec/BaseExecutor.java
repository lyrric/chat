package com.play001.gobang.server.exec;

import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;
import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

public abstract class BaseExecutor implements Runnable{

    //消息
    protected ClientBaseMsg baseMsg;
    protected Channel channel;

    public ClientBaseMsg getBaseMsg() {
        return baseMsg;
    }

    public void setBaseMsg(ClientBaseMsg baseMsg) {
        this.baseMsg = baseMsg;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
