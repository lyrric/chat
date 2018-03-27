package com.play001.gobang.support.entity.msg.client;

/**
 * server来自用户的消息类型
 */
public class ClientMsgType {
    //登陆
    public static final short LOGIN_REQ = 1;
    //进入房间
    public static final short ENTER_THE_ROOM = 2;
    //准备游戏
    public static final short READY_GAME = 3;
    //开始游戏
    public static final short START_GAME = 4;
    //落子
    public static final short MOVE= 5;
    //游戏结束
    public static final short END_GAME= 6;
    //文本消息
    public static final short TEXT = 7;
    //退出房间
    public static final short OUT_THE_ROOM = 8;
    //退出
    public static final short LOGOUT = 9;
}
