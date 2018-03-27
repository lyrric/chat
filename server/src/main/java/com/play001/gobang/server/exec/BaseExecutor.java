package com.play001.gobang.server.exec;

import com.play001.gobang.support.entity.msg.client.ClientBaseMsg;

public abstract class BaseExecutor implements Runnable{

    //消息
    protected ClientBaseMsg baseMsg;

    public BaseExecutor() {
    }

    protected BaseExecutor(ClientBaseMsg baseMsg) {
        this.baseMsg = baseMsg;
    }
}
