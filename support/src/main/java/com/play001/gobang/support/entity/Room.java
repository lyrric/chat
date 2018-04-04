package com.play001.gobang.support.entity;

import java.io.Serializable;

public class Room implements Serializable{
    //房间ID
    private Integer roomId;
    //房主名
    private String hostUsername;
    //当前人数
    private Integer userCount;

    public Room() {
    }

    public Room(Integer roomId, String hostUsername, Integer userCount) {
        this.roomId = roomId;
        this.hostUsername = hostUsername;
        this.userCount = userCount;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
    public void increaseUserCount(){
        userCount++;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", hostUsername='" + hostUsername + '\'' +
                ", userCount=" + userCount +
                '}';
    }
}
