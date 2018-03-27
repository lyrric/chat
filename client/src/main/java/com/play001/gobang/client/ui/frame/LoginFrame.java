package com.play001.gobang.client.ui.frame;

import com.play001.gobang.client.ClientChannelInitializer;
import com.play001.gobang.client.service.NettyService;
import com.play001.gobang.client.service.ServiceFactory;
import com.play001.gobang.client.service.UserService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.play001.gobang.client.Config.*;
/**
 * 登陆面板
 */
public class LoginFrame extends JFrame{

    private final Logger logger = Logger.getLogger(LoginFrame.class);
    private JTextField usernameText;
    private JButton loginBtn;

    public LoginFrame() throws HeadlessException {
        logger.info("LoginFrame初始化");
        this.setTitle("登陆");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400,250);
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

        logger.info("LoginFrame初始化完成");
    }

    //登陆监听器
    private class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            loginBtn.setEnabled(false);
            //获取用户名
            String username = usernameText.getText();
            if(username.length() < 2){
                JOptionPane.showMessageDialog(null,"用户名长度需大于等于2", "提示", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null,"登陆失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                //登陆激活按钮
                loginBtn.setEnabled(true);
            }

        }
    }

    /**
     * 登陆失败后将按钮激活
     */
    public void setLoginBtnEnable(){
        loginBtn.setEnabled(false);
    }

}
