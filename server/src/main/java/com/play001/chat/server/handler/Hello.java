package com.play001.chat.server.handler;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Hello {

    public String value = "hello";

    @PostConstruct
    public void run(){
        System.out.println("run !!!!");
    }
}
