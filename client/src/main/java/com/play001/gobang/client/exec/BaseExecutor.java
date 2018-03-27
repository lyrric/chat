package com.play001.gobang.client.exec;

import com.play001.gobang.support.entity.msg.server.ServerBaseMsg;

public abstract class BaseExecutor implements Runnable{

    //消息
    protected ServerBaseMsg baseMsg;

    public BaseExecutor() {
    }

    protected BaseExecutor(ServerBaseMsg baseMsg) {
        this.baseMsg = baseMsg;
    }
}
