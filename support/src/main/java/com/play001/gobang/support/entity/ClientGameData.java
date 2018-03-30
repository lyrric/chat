package com.play001.gobang.support.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 客户端数据类
 */
public class ClientGameData implements Serializable{
    //游戏状态
    private Integer status;
    //对手
    private Player competitor;
    //自己
    private Player selfPlayer;
    //是否准备
    private boolean ready;
    //该我走棋么?
    private boolean myTurn;
    //棋盘19*19
    private ChessBoard chessboard;

    public void initBoardData(){
        //初始化棋盘
    }

    public Player getCompetitor() {
        return competitor;
    }

    public void setCompetitor(Player competitor) {
        this.competitor = competitor;
    }

    /**
     * 落子
     * @param x x坐标1开始
     * @param y y坐标1开始
     * @param color 颜色 see ChessboardItemStatus
     * @return  当前位置无子则返回true,其它返回false
     */
    public boolean moveChess(int x, int y, byte color){
        return chessboard.moveChess(x, y, color);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Player getSelfPlayer() {
        return selfPlayer;
    }

    public void setChessboard(ChessBoard chessboard) {
        this.chessboard = chessboard;
    }

    public void setSelfPlayer(Player selfPlayer) {
        this.selfPlayer = selfPlayer;
    }

    public byte getChess(int x, int y) {
        return chessboard.getChess(x, y);
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }





}
