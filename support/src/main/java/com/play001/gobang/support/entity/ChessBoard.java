package com.play001.gobang.support.entity;

/**
 * 棋盘
 */
public class ChessBoard {
    //三维数据,八个数据,记录移动的八个方向x,y的变化
    private final int dir[][][] = {
            //横向,左右移动
            {{-1,0}, {1, 0}},
            //纵向,上下移动
            {{0, -1}, {0, 1}},
            //左斜,右上,左下
            {{1, -1}, {-1, +1}},
            //右斜,左上,右下
            {{-1, -1}, {1, 1}}
    };

    private ChessType[][] chessboard;

    public ChessBoard() {
        this.chessboard = new ChessType[19][19];
    }

    //落子
    boolean moveChess(int x, int y, ChessType chessType){
        if(x < 0 || y <0 || x > 19 || y > 19 || chessboard[x][y] != null){
            return false;
        }

        chessboard[x][y] = chessType;
        return true;
    }
    //检查是否获胜
    public boolean checkWin(int x, int y){
        ChessType chessType = chessboard[x][y];
        for(int i = 0; i < 4; i++){
            int count = 1;

            //向一个方向计算同色棋子数
            for(int j = 0; j < 2; j++){
                int temp_x = x;
                int temp_y = y;
                temp_x+=dir[i][j][0];
                temp_y+=dir[i][j][1];
                while(temp_x >= 0 && temp_x <= 19 && temp_y >= 0 && temp_y <= 19 && chessboard[temp_x][temp_y] == chessType){
                    if(++count > 4){
                        return true;
                    }
                    temp_x+=dir[i][j][0];
                    temp_y+=dir[i][j][1];
                }
            }
        }
        return false;
    }
    ChessType getChess(int x, int y) {
        return chessboard[x][y];
    }


}
