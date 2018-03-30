package com.play001.gobang.support.entity;

public class GameStatus {

    //初始化中
    public static final int INITIALIZING = -1;
    //等待用户进入
    public static final int WAITING_PLAYER = 0;
    //准备中
    public static final int READY = 1;
    //游戏中
    public static final int START = 2;
    //游戏结束
    public static final int END = 4;
}
