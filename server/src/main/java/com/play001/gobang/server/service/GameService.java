package com.play001.gobang.server.service;

import com.play001.gobang.support.entity.Player;
import com.play001.gobang.support.entity.ServerGameData;
import com.play001.gobang.support.entity.GameStatus;
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
            //第一个用户为先手方
            gameData.addPlayer(username, true);
            //等待另一个用户进入
            gameData.setStatus(GameStatus.WAITING_PLAYER);
            gameDataMap.put(roomId, gameData);
        }else{
            logger.info("玩家进入");
            Player player = new Player();
            gameData.addPlayer(username,false);
            gameData.setStatus(GameStatus.READY);

        }
    }

    /**
     * 玩家离开
     */
    public static void remove(Integer roomId, String username){
        logger.info("玩家:"+username+", 离开房间:"+roomId);
        ServerGameData gameData = gameDataMap.get(roomId);
        if(gameData != null){
            gameData.removeByUsername(username);
            //判断房间是否还有人存在, 将剩下的玩家设置为先手玩家
            if(gameData.playerCount() != 0){
                gameData.setFirst(username);
            }else{
                //玩家一二都已离开房间,则移除此房间数据
                logger.info("房间"+roomId+"无玩家, 销毁房间数据");
                gameDataMap.remove(roomId);
            }
        }
    }
    //获取房间另一个的用户名
    public static String getOtherUsername(Integer roomId, String username){
        //获取对方用户名
        ServerGameData gameData = gameDataMap.get(roomId);
        Player player = gameData.getPlayer1();
        if(player != null && !player.getUsername().equals(username)){
            return player.getUsername();
        }else{
            return gameData.getPlayer2().getUsername();
        }
    }
    public static ServerGameData getGameData(Integer roomId){
        return gameDataMap.get(roomId);
    }
}
