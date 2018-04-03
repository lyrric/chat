package com.play001.gobang.server.service;

import com.play001.gobang.support.entity.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 房间信息, 操作
 */
public class RoomService {
    //房间列表
    private static List<Room> roomList = new LinkedList<>();
    //房间最大ID
    private static Integer maxRoomId = 1;

    //创建房间
    public static synchronized Room add(String hostUsername){
        Room room = new Room(++maxRoomId, hostUsername, 0);
        roomList.add(room);
        return room;
    }
    //返回房间列表的拷贝
    public static synchronized List<Room> getRoomList(){
        return new ArrayList<>(roomList);
    }
    //移除房间
    public static synchronized void remove(Integer roomId){
        for(Room room:roomList){
            if(room.getRoomId().equals(roomId)){
                roomList.remove(room);
                return ;
            }
        }
    }
    //房间是否存在
    public static boolean isExist(Integer roomId){
        for(Room room:roomList){
            if(room.getRoomId().equals(roomId)){
                return true;
            }
        }
        return false;
    }
    //获取房间
    public static Room get(Integer roomId){
        for(Room room:roomList){
            if(room.getRoomId().equals(roomId)){
                return room;
            }
        }
        return null;
    }
}
