package com.play001.chat.server;

import com.play001.chat.server.handler.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring 启动
 */

@ComponentScan(basePackages = "com.play001.chat")
public class Application {


    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(Application.class);
    }
}
