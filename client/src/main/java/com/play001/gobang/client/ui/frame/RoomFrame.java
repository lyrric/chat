package com.play001.gobang.client.ui.frame;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 登陆面板
 */
public class RoomFrame extends JFrame{

    private JButton enterBtn;
    private JButton createBtn;
    private JButton updateBtn;
    private JLabel tip;
    private String [][] roomList;
    private String[] HEAD_TITLE = { "房间ID", "房主", "人数"};
    private JTable roomTable;
    private DefaultTableModel model;
    public RoomFrame() throws HeadlessException {
        this.setTitle("请输入房间号");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,500);
        //居中显示
        this.setLocationRelativeTo(null);
        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(null);

        //表格
        model = new DefaultTableModel();
        model.setDataVector(roomList, HEAD_TITLE);
        roomTable = new JTable(model);
        // 将表格加入到滚动条组件中
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBounds(5,5,300,450);
        panel.add(scrollPane, BorderLayout.CENTER);

        //刷新按钮
        updateBtn = new JButton("刷新");
        updateBtn.setBounds(350, 50, 100, 40);
        updateBtn.addActionListener(e->{
            tip.setText("刷新中,请稍后...");
            updateBtn.setEnabled(false);
            ServiceFactory.getRoomService().getRoomList();
        });
        panel.add(updateBtn);

        //进入
        enterBtn = new JButton("进入");
        enterBtn.setBounds(350, 150, 100, 40);
        enterBtn.addActionListener(new EnterListener());
        panel.add(enterBtn);

        //创建房间
        createBtn = new JButton("创建");
        createBtn.setBounds(350, 250, 100, 40);
        createBtn.addActionListener(e -> {
            tip.setText("创建中,请稍后...");
            createBtn.setEnabled(false);
            ServiceFactory.getRoomService().create();
        });
        panel.add(createBtn);

        tip = new JLabel();
        tip.setBounds(350,320, 300, 30);
        tip.setVisible(true);
        panel.add(tip);
    }

    //创建房间成功
    public void createSuccess(Integer roomId) {
        tip.setText("创建成功,正在进入,请稍后...");
        createBtn.setEnabled(true);
        JOptionPane.showMessageDialog(null,"创建成功, 房间ID:"+roomId, "提示", JOptionPane.INFORMATION_MESSAGE);
        //进入房间
        ServiceFactory.getRoomService().enter(roomId);
    }

    //创建房间失败
    public void createFailed(String errMsg) {
        tip.setText(errMsg);
        createBtn.setEnabled(true);
        JOptionPane.showMessageDialog(null,errMsg, "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    //获取房间列表失败
    public void getListFailed(String errMsg) {
        this.setTitle("获取房间列表失败!");
        tip.setText(errMsg);
        updateBtn.setEnabled(true);
        JOptionPane.showMessageDialog(null,errMsg, "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    //获取别表成功
    public void updateList(List<Room> roomList) {
        this.setTitle("获取房间列表成功, 更新时间:2018年4月3日 16:40:18");
        this.roomList = new String[roomList.size()][3];
        for(int i=0;i<roomList.size(); i++){
            Room room = roomList.get(i);
            this.roomList[i][0] = room.getRoomId().toString();
            this.roomList[i][1] = room.getHostUsername();
            this.roomList[i][2] = room.getUserCount().toString();
        }
        model.setDataVector(this.roomList, HEAD_TITLE);
        updateBtn.setEnabled(true);
        tip.setText("获取房间列表成功");
    }

    //二次显示需要初始化
    public void init(){
        updateBtn.setEnabled(true);
        createBtn.setEnabled(true);
        enterBtn.setEnabled(true);
        ServiceFactory.getRoomService().getRoomList();
        this.setTitle("正在获取房间列表...");
    }
    //登陆监听器
    private class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //获取房间id
            Integer roomId = getRoomId();
            if(roomId == null){
                JOptionPane.showMessageDialog(null,"请选择进入的房间", "提示", JOptionPane.INFORMATION_MESSAGE);
                return ;
            }
            enterBtn.setEnabled(false);
            createBtn.setEnabled(false);
            updateBtn.setEnabled(false);
            tip.setText("进入中,请稍后...");
            try {
                //进入房间
                ServiceFactory.getRoomService().enter(roomId);
                //等待handler的事件(等待服务器返回消息),让handler来处理
            }catch (Exception ex){
                ex.printStackTrace();
                enterFailed("进入失败");
            }

        }
    }
    /**
     * 进入失败后事件
     */
    public void enterFailed(String errMsg){
        tip.setText(errMsg);
        JOptionPane.showMessageDialog(this, errMsg, "提示", JOptionPane.INFORMATION_MESSAGE);
        enterBtn.setEnabled(true);
        createBtn.setEnabled(true);
        updateBtn.setEnabled(true);
    }
    /**
     * 进入成功后事件
     */
    public void enterSuccess(){
        JOptionPane.showMessageDialog(null,"进入成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        this.setVisible(false);
        //显示游戏界面
        GameFrame gameFrame = UIFactory.getGameFrame();
        gameFrame.init();
        this.setVisible(false);
        gameFrame.setVisible(true);
    }

    //获取选中的房间号
    private Integer getRoomId(){
        int row = roomTable.getSelectedRow();
        if (row == -1){
            return null;
        }
        try {
            return Integer.valueOf(roomList[row][0]);
        }catch (Exception e){
            return null;
        }
    }
}
