package com.play001.gobang.support.entity;

import java.io.Serializable;

public class ServerGameData implements Serializable{
    //游戏状态
    private Integer status;
    private Player player1;
    private Player player2;

    //当前落子玩家
    private Player actionPlayer;
    //棋盘19*19
    private ChessBoard chessBoard;

    /**
     * 加入玩家
     */
    public void addPlayer(String username){
        if(player1 == null){
            player1 = new Player(username, null, false);
        }else{
            player2 = new Player(username, null, false);;
        }
    }

    public Player getBlacknessPlayer(){
        return player1.getChessType().equals(ChessType.BLACKNESS)?player1:player2;
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
     * @param username ....
     * @return  当前位置无子则返回true,其它返回false
     */
    public boolean moveChess(String username, int x, int y){
        return chessBoard.moveChess(x, y, getByUsername(username).getChessType());
    }
    //设置用户准备
    public void setUserReady(String username, boolean isReady){
        Player player = getByUsername(username);
        if(player != null){
            player.setReady(isReady);
        }
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }



    public ChessType getChess(int x, int y){
        return chessBoard.getChess(x, y);
    }
    public void initChessBord() {
        chessBoard = new ChessBoard();
    }
    public void removeByUsername(String username){
        if(player1 != null && player1.getUsername().equals(username)){
            player1=null;
        }else if(player2 != null && player2.getUsername().equals(username)){
            player2 = null;
        }
    }

    public Player getActionPlayer() {
        return actionPlayer;
    }

    public void setActionPlayer(Player actionPlayer) {
        this.actionPlayer = actionPlayer;
    }

    //获取对手用户名
    public  String getCompetitorName(String username){
        //获取对方用户名
        if(player1 != null && !player1.getUsername().equals(username)){
            return player1.getUsername();
        }else{
            return player2.getUsername();
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }



    public Player getByUsername(String username){
        if(player1 != null && player1.getUsername().equals(username)){
            return player1;
        }else if(player2 != null && player2.getUsername().equals(username)){
            return player2;
        }
        return null;
    }

    //判断双方是否都已经准备了
    public boolean isAllReady(){
        return (player1.getReady() && player2.getReady());
    }
    public ChessBoard getChessBoard() {
        return chessBoard;
    }


}
