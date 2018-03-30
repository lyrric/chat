package com.play001.gobang.support.entity.msg.client;

/**
 * server来自用户的消息类型
 */
public class ClientMsgType {
    //登陆
    public static final short LOGIN_REQ = 1;
    //请求房间列表
    public static final short ROOM_LIST_REQ = 2;
    //创建房间
    public static final short ROOM_CREATE_REQ = 3;
    //进入房间
    public static final short ROOM_ENTER_REQ = 4;
    //状态改变
    public static final short READY_CHANGE = 5;
    //开始游戏
    public static final short START_GAME = 6;
    //请求游戏数据
    public static final short GAME_DATA_REQ = 7;

    //文本消息
    public static final short TEXT = 8;
/*    //落子
    public static final short MOVE= 5;
    //游戏结束
    public static final short END_GAME= 6;
    //退出房间
    public static final short OUT_THE_ROOM = 8;
    //退出
    public static final short LOGOUT = 9;*/
}
