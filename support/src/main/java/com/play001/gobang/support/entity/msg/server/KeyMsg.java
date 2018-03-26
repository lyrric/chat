package com.play001.gobang.support.entity.msg.server;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.msg.BaseMsg;
import com.play001.gobang.support.entity.res.ResMsgType;

/**
 * 返回key给用户
 */
@MsgAnnotation(msgType = ResMsgType.LOGIN)
public class KeyMsg extends BaseMsg {

    private String userKey;

    public KeyMsg(String userKey, Long time) {
        super(ResMsgType.LOGIN, time);
        this.userKey = userKey;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
