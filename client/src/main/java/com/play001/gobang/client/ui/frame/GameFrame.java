package com.play001.gobang.client.ui.frame;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.support.entity.*;
import com.play001.gobang.support.entity.msg.server.ServerTextMsg;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class GameFrame extends JFrame{

    private Logger logger = Logger.getLogger(GameFrame.class);
    //棋盘左上角位置
    private final Point BOARD_ZERO_POINT = new Point(60, 38);
    //棋盘大小14*50
    private final int BOARD_WIDTH = 700;
    private final int BOARD_HEIGHT = 659;
    //格子大小
    private final int LATTICE_SIZE = 35;
    //棋子大小
    private final int CHESS_SIZE = 30;
    private Image boardImage;
    private Image whiteChessImage;
    private Image blackChessImage;
    //按钮
    private JButton readyBtn;
    private JButton exitBtn;

    private JLabel selfLabel;
    private JLabel competitorLabel;
    //消息框
    private JTextArea msgArea;
    //消息输入框
    private JTextField msgField;
    //发送按钮
    private JButton sendBtn;
    //游戏数据
    private ClientGameData gameData;
    public GameFrame() throws HeadlessException {
        //窗体大小
        final int WINDOW_WIDTH = 1200;
        final int WINDOW_HEIGHT = 900;
        this.setTitle("五子棋");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //居中显示, 需要在setSize后面
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        //加载图片
        URL url = ClassLoader.class.getResource("/image/board.jpg");
        boardImage = pane.getToolkit().getImage(url);
        url = ClassLoader.class.getResource("/image/black_chess.png");
        blackChessImage = pane.getToolkit().getImage(url);
        url = ClassLoader.class.getResource("/image/white_chess.png");
        whiteChessImage = pane.getToolkit().getImage(url);

        //右边
        selfLabel = new JLabel("玩家一:");
        selfLabel.setBounds(800, 50, 200, 50);
        pane.add(selfLabel);

        competitorLabel = new JLabel();
        competitorLabel.setText("玩家二:");
        competitorLabel.setBounds(800, 100, 200, 50);
        pane.add(competitorLabel);
        //聊天框
        msgArea = new JTextArea();
        msgArea.setBounds(750, 200, 400,400);
        msgArea.setEnabled(false);
        pane.add(msgArea);
        msgField = new JTextField();
        msgField.setBounds(750, 630, 330, 30);
        pane.add(msgField);
        sendBtn = new JButton("发送");
        sendBtn.setBounds(1100,630, 60, 30);
        sendBtn.addActionListener(new sendBtnListener());
        pane.add(sendBtn);
        //下面
        readyBtn = new JButton("准备");
        readyBtn.setBounds(50, 730, 100, 50);
        readyBtn.addActionListener(e->{
            boolean ready = !gameData.getSelfPlayer().getReady();
            readyBtn.setText(ready?"已准备":"准备");
            gameData.getSelfPlayer().setReady(ready);
            ServiceFactory.getGameService().readyChange(ready);
        });
        pane.add(readyBtn);

        exitBtn = new JButton("退出");
        exitBtn.setBounds(200, 730, 100, 50);
        pane.add(exitBtn);
        //监听鼠标按下事件
        pane.addMouseListener(new ClickListener());
    }

    //初始化
    void init(){
        //请求游戏数据
        gameData = new ClientGameData();
        gameData.setStatus(GameStatus.INITIALIZING);
        ServiceFactory.getGameService().getGameData();
        msgArea.append(ServiceFactory.getUserService().getUser().getUsername()+"进入游戏\r\n");
    }

    //更新游戏数据
    public void updateGameData(ServerGameData serverGameData){
        //保存自身数据
        Player clientPlayer = serverGameData.getByUsername(ServiceFactory.getUserService().getUser().getUsername());
        gameData.setSelfPlayer(clientPlayer);
        selfLabel.setText("玩家一:" + clientPlayer.getUsername());
        //是否存在对手, 存在则保存对手数据
        if(serverGameData.playerCount() == 2){
            Player competitor = serverGameData.getPlayer1();
            //如果玩家一是自己,则获取玩家二的名字作为对手名
            if(competitor == null || competitor.getUsername().equals(gameData.getSelfPlayer().getUsername())){
                competitor = serverGameData.getPlayer2();
            }
            //保存对手数据
            gameData.setCompetitor(competitor);
            competitorLabel.setText("玩家二:" + competitor.getUsername());
        }

        gameData.setStatus(serverGameData.getStatus());
        //如果游戏已经开始,则保存游戏棋盘数据和当前该谁移动
        if(serverGameData.getStatus().equals(GameStatus.START)){
            gameData.setMyTurn(serverGameData.getMoveUsername().equals(gameData.getSelfPlayer().getUsername()));
            gameData.setChessboard(serverGameData.getChessBoard());
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics pane = getContentPane().getGraphics();
        //画棋盘
        pane.drawImage(boardImage, 26, 21, BOARD_WIDTH, BOARD_HEIGHT, this);
        //画棋子
        if(gameData != null && GameStatus.START == gameData.getStatus()){
            for(int x = 0;x < 19;x++){
                for(int y = 0; y< 19; y++){
                    byte chess = gameData.getChess(x, y);
                    if(chess != ServerGameData.ChessboardItemStatus.EMPTY){
                        //有棋, 计算坐标
                        Point p = new Point();
                        p.x = (x * LATTICE_SIZE) + BOARD_ZERO_POINT.x - CHESS_SIZE/2;
                        p.y = (y * LATTICE_SIZE) + BOARD_ZERO_POINT.y - CHESS_SIZE/2;
                        if(chess == ServerGameData.ChessboardItemStatus.WHITENESS){
                            pane.drawImage(whiteChessImage, p.x, p.y, CHESS_SIZE, CHESS_SIZE, this);
                        }else{
                            pane.drawImage(blackChessImage, p.x, p.y, CHESS_SIZE, CHESS_SIZE, this);
                        }
                    }
                }
            }
        }
    }

    public void readyChange(String username, boolean ready){
        if(ready){
            msgArea.append("玩家:"+username+"已准备\r\n");
        }else{
            msgArea.append("玩家:"+username+"取消准备\r\n");
        }
    }
    //获取游戏数据失败
    public void getGameDataFailed(String errMsg) {
        logger.info(errMsg);
    }

    //开始游戏
    public void startGame() {
        msgArea.append("开始游戏\r\n");
        readyBtn.setEnabled(false);
        gameData.setStatus(GameStatus.START);
        gameData.setChessboard(new ChessBoard());
    }

    //鼠标点击事件
    private class ClickListener extends MouseAdapter {
        //判断点击有效
        @Override
        public void mouseReleased(MouseEvent e) {
            //游戏未开始,不进行任何操作
            if(gameData == null || !gameData.getStatus().equals(GameStatus.START)){
                return ;
            }
            //相对于棋盘的坐标
            int x = e.getX()-BOARD_ZERO_POINT.x;
            int y = e.getY()-BOARD_ZERO_POINT.y;
            if(x < 0 || y < 0){
                //棋盘外的点击无效
                return ;
            }
            System.out.println("相对坐标x="+x+", y="+y);

            //有效点击计算规则
            //离坐标交接点距离不大于10
            if((x % LATTICE_SIZE < 10 || (x+10) % LATTICE_SIZE < 10)
                && (y % LATTICE_SIZE < 10 || (y+10) % LATTICE_SIZE < 10)){
                //计算所点击的棋盘位置
                System.out.println("点击有效");
                int x_ = (x + 10) / LATTICE_SIZE;
                int y_ = (y + 10) / LATTICE_SIZE;
                System.out.println("棋盘坐标 :x=" + x_ + ", y=" + y_);
                gameData.moveChess(x_, y_, ServerGameData.ChessboardItemStatus.WHITENESS);
                repaint();
            }
        }
    }
    //消息来了
    public void msgComing(ServerTextMsg textMsg){
        msgArea.append(textMsg.getFromUsername()+":"+textMsg.getText());
    }
    //有用户进入
    public void userEnter(String username){
        msgArea.append(username + "进入房间\r\n");
        Player competitor = new Player(username, null, false);
        gameData.setCompetitor(competitor);
        competitorLabel.setText("玩家二:"+username);
    }
    //发送消息
    private class sendBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = msgField.getText();
            if(msg.equals("")){
                return;
            }
            ServiceFactory.getMessageService().sendTextMsg(msg);
            msgField.setText("");
        }
    }

}
