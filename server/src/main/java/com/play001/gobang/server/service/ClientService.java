package com.play001.gobang.server.service;

import com.play001.gobang.support.entity.Client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 存放所有连接客服端信息
 */
public class ClientService {

    //未登陆客户端
    private static List<Client> noLoginClientList = new LinkedList<>();
    //已登陆客户端 key为用户名
    private static Map<String, Client> clientMap = new HashMap<>();

    public static void addNoLoginClientList(Client client){
        noLoginClientList.add(client);
    }
    public static void reMoveNoLoginClientList(Client client){
        noLoginClientList.add(client);
    }

}
