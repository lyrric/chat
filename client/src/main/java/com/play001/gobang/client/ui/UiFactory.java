package com.play001.gobang.client.ui;

import com.play001.gobang.client.ui.frame.GameFrame;
import com.play001.gobang.client.ui.frame.LoginFrame;
import com.play001.gobang.client.ui.frame.RoomFrame;

/**
 * 面板工厂类
 */
public class UiFactory {
    private static LoginFrame loginFrame = null;
    private static RoomFrame roomFrame  = null;
    private static GameFrame gameFrame  = null;

    public static LoginFrame getLoginFrame() {
        if(loginFrame == null){
            loginFrame = new LoginFrame();
        }
        return loginFrame;
    }

    public static RoomFrame getRoomFrame() {
        if(roomFrame == null){
            roomFrame = new RoomFrame();
        }
        return roomFrame;
    }

    public static GameFrame getGameFrame() {
        if(gameFrame == null){
            gameFrame = new GameFrame();
        }
        return gameFrame;
    }
}
