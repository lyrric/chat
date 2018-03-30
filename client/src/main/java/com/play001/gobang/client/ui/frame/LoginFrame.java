package com.play001.gobang.client.ui.frame;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UIFactory;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登陆面板
 */
public class LoginFrame extends JFrame{

    private final Logger logger = Logger.getLogger(LoginFrame.class);
    private JTextField usernameText;
    private JButton loginBtn;
    private JLabel tip;
    public LoginFrame() throws HeadlessException {
        logger.info("LoginFrame初始化");
        this.setTitle("登陆");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,250);
        //居中显示, 需要在setSize后面
        this.setLocationRelativeTo(null);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);
        //用户名
        usernameText = new JTextField(20);
        usernameText.setBounds(100,75,165,30);
        panel.add(usernameText);

        JLabel label = new JLabel("用户名:");
        label.setBounds(50,75, 50, 30);
        panel.add(label);

        //登陆按钮
        loginBtn = new JButton("登陆");
        loginBtn.setBounds(280, 75, 80, 30);
        loginBtn.addActionListener(new LoginListener());
        panel.add(loginBtn);

        tip = new JLabel("登陆中,请稍后...");
        tip.setBounds(50,100, 300, 30);
        tip.setVisible(false);
        panel.add(tip);

        logger.info("LoginFrame初始化完成");
    }

    //登陆监听器
    private class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loginBtn.setEnabled(false);
            usernameText.setEnabled(false);
            tip.setVisible(true);
            //获取用户名
            String username = usernameText.getText();
            if(username.length() < 2){
                loginFailed("用户名长度需大于等于2");
                return;
            }
            try {
                //连接
                ServiceFactory.getNettyService().connect();
                //登陆
                ServiceFactory.getLoginService().login(username);
                //登陆后,等待handler的事件(等待服务器返回消息),让handler来处理
            }catch (Exception ex){
                ex.printStackTrace();
                loginFailed("登陆失败");
            }

        }
    }

    /**
     * 登陆失败后事件
     */
    public void loginFailed(String errMsg){
        tip.setVisible(false);
        JOptionPane.showMessageDialog(null,errMsg, "提示", JOptionPane.INFORMATION_MESSAGE);
        loginBtn.setEnabled(true);
        usernameText.setEnabled(true);
    }
    /**
     * 登陆成功后事件
     */
    public void loginSuccess(){
        JOptionPane.showMessageDialog(null,"登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        //显示房间输入
        UIFactory.getRoomFrame().setVisible(true);
    }

}
