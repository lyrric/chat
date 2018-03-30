package com.play001.gobang.support.entity.msg.server;

public class ServerMsgType {
    //登陆返回消息
    public static final short LOGIN_RES = 1;
    //返回房间列表
    public static final short ROOM_LIST_RES = 2;
    //返回进入房间消息结果
    public static final short ROOM_ENTER_RES = 3;
    //有用户进入房间消息
    public static final short USER_ENTER = 4;
    //创建房间
    public static final short ROOM_CREATE_RES = 5;
    //返回游戏数据
    public static final short GAME_DATA_RES = 6;
    //用户准备状态变化
    public static final short READY_CHANGE = 7;
    //开始游戏
    public static final short GAME_START = 8;

}
