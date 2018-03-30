package com.play001.gobang.support.entity;

/**
 * 棋盘
 */
public class ChessBoard {
    private byte[][] chessboard;

    public ChessBoard() {
        this.chessboard = new byte[19][19];
    }

    //落子
    boolean moveChess(int x, int y, byte color){
        if(chessboard[x][y] != ChessStatus.EMPTY){
            return false;
        }
        chessboard[x][y] = color;
        return true;
    }

    byte getChess(int x, int y) {
        return chessboard[x][y];
    }

    public static class ChessStatus{
        //空
        public static final byte EMPTY = 0;
        //黑棋
        public static final byte WHITENESS = 1;
        //白棋
        public static final byte BLACKNESS = 2;
    }
}
