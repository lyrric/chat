package com.play001.gobang.server.service;

import com.play001.gobang.support.entity.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏数据操作
 */
public class GameService {

    private static Logger logger = Logger.getLogger(GameService.class);
    //游戏数据
    private static Map<Integer, ServerGameData> gameDataMap = new HashMap<>();

    /**
     * 进入房间,创建游戏数据
     * @param roomId 房间ID
     * @param username 玩家名
     */
    public static void add(Integer roomId, String username){
        logger.info("玩家:"+username+", 进入房间:"+roomId);
        ServerGameData gameData = gameDataMap.get(roomId);
        if(gameData == null){
            //创建房间数据
            logger.info("创建房间:"+roomId);
            gameData = new ServerGameData();
            gameData.addPlayer(username);
            //等待另一个用户进入
            gameData.setStatus(GameStatus.WAITING_PLAYER);
            gameDataMap.put(roomId, gameData);
        }else{
            logger.info("玩家进入");
            Player player = new Player();
            gameData.addPlayer(username);
            gameData.setStatus(GameStatus.READY);
        }
    }


    public static ServerGameData getGameData(Integer roomId){
        return gameDataMap.get(roomId);
    }

    public static void remove(Integer roomId){
        gameDataMap.remove(roomId);
    }
}
