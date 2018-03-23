package com.play001.chat.support.entity;

/**
 * 用户登陆信息
 * 用户名, 密码
 */
public class LoginMsg extends AbstractMsg {
    private String username;
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
