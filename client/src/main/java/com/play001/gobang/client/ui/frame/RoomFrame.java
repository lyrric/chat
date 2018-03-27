package com.play001.gobang.client.ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 登陆面板
 */
public class RoomFrame extends JFrame{


    public RoomFrame() throws HeadlessException {
        this.setTitle("请输入房间号");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,250);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);
        //用户名
        JTextField roomText = new JTextField(20);
        roomText.setBounds(100,75,165,30);
        panel.add(roomText);

        JLabel label = new JLabel("房间号:");
        label.setBounds(50,75, 50, 30);
        panel.add(label);

        //登陆按钮
        JButton loginBtn = new JButton("进入房间");
        loginBtn.setBounds(280, 75, 80, 30);
        panel.add(loginBtn);

        this.setVisible(true);
    }
}
