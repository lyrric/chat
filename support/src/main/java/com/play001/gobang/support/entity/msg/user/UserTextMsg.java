package com.play001.gobang.support.entity.msg.user;

import com.play001.gobang.support.annotation.MsgAnnotation;
import com.play001.gobang.support.entity.User;
import com.play001.gobang.support.entity.msg.BaseMsg;

/**
 * 用户所发文本信息
 */
@MsgAnnotation(msgType = UserMsgType.TEXT)
public class UserTextMsg extends BaseMsg {

    private User formUser;
    private String text;

    public UserTextMsg() {
    }

    public UserTextMsg(long time, User formUser, String text) {
        super(UserMsgType.TEXT, time);
        this.formUser = formUser;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getFormUser() {
        return formUser;
    }

    public void setFormUser(User formUser) {
        this.formUser = formUser;
    }
}
