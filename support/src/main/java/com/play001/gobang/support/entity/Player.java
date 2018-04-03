package com.play001.gobang.support.entity;

import java.io.Serializable;

public class Player implements Serializable{

    //对手名字
    private String username;
    //黑棋or白棋
    private ChessType chessType;
    //是否准备
    private Boolean isReady;

    public Player() {
    }

    public Player(String username, ChessType chessType, Boolean isReady) {
        this.username = username;
        this.chessType = chessType;
        this.isReady = isReady;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ChessType getChessType() {
        return chessType;
    }

    public void setChessType(ChessType chessType) {
        this.chessType = chessType;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
