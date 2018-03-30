package com.play001.gobang.support.entity;

import com.sun.deploy.util.ArrayUtil;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class ServerGameData implements Serializable{
    //游戏状态
    private Integer status;
    private Player player1;
    private Player player2;
    //当前落子玩家名(该谁走棋)
    private String moveUsername;
    //棋盘19*19
    private ChessBoard chessBoard;

    public ServerGameData() {
    }
    public void initBoardData(){
        //初始化棋盘
    }
    /**
     * 加入玩家
     */
    public void addPlayer(String username, boolean isFirst){
        if(player1 == null){
            player1 = new Player(username, isFirst, false);
        }else{
            player2 = new Player(username, isFirst, false);;
        }
    }

    //获取房间用户数
    public int playerCount(){
        int i = 0;
        if(player1 != null)i++;
        if(player2 != null)i++;
        return i;
    }
    /**
     * 落子
     * @param x x坐标1开始
     * @param y y坐标1开始
     * @param color 颜色 see ChessboardItemStatus
     * @return  当前位置无子则返回true,其它返回false
     */
    public boolean moveChess(int x, int y, byte color){
        return chessBoard.moveChess(x, y, color);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMoveUsername() {
        return moveUsername;
    }

    public void setMoveUsername(String moveUsername) {
        this.moveUsername = moveUsername;
    }

    public byte getChess(int x, int y){
        return chessBoard.getChess(x, y);
    }

    public void removeByUsername(String username){
        if(player1 != null && player1.getUsername().equals(username)){
            player1=null;
        }else if(player2 != null && player2.getUsername().equals(username)){
            player2 = null;
        }
    }



    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    //设置先手
    public void setFirst(String username){
        getByUsername(username).setFirst(true);
    }
    //设置准备
    public void setReady(String username){
        getByUsername(username).setReady(true);
    }

    public Player getByUsername(String username){
        if(player1 != null && player1.getUsername().equals(username)){
            return player1;
        }else if(player2 != null && player2.getUsername().equals(username)){
            return player2;
        }
        return null;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public static class ChessboardItemStatus{
        //空
        public static final byte EMPTY = 0;
        //黑棋
        public static final byte WHITENESS = 1;
        //白棋
        public static final byte BLACKNESS = 2;
    }




}
