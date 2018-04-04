package com.play001.gobang.client.ui.frame;

import com.play001.gobang.client.service.factory.ServiceFactory;
import com.play001.gobang.client.ui.UIFactory;
import com.play001.gobang.support.entity.*;
import com.play001.gobang.support.entity.msg.server.ServerTextMsg;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GameFrame extends JFrame{

    private Logger logger = Logger.getLogger(GameFrame.class);
    //棋盘左上角位置
    private final Point BOARD_ZERO_POINT = new Point(60, 67);
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
    private JLabel statusLabel;
    //消息框
    private JTextArea msgArea;
    //消息输入框
    private JTextField msgField;
    //发送按钮
    private JButton sendBtn;
    //游戏数据
    private ClientGameData gameData;
    private  Image image;


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

        //标签
        selfLabel = new JLabel("玩家一:");
        selfLabel.setBounds(800, 50, 200, 50);
        pane.add(selfLabel);

        competitorLabel = new JLabel();
        competitorLabel.setText("玩家二:");
        competitorLabel.setBounds(800, 100, 200, 50);
        pane.add(competitorLabel);

        statusLabel = new JLabel();
        statusLabel.setText("当前游戏状态:");
        statusLabel.setBounds(800, 180, 200, 50);
        pane.add(statusLabel);

        //聊天框
        msgArea = new JTextArea();
        msgArea.setBounds(750, 250, 400,400);
        msgArea.setFont(new Font("宋体", Font.BOLD, 14));
        msgArea.setLineWrap(true);
        msgArea.setEditable(false);
        JScrollPane jsp = new JScrollPane(msgArea);
        jsp.setBounds(750, 250, 400,400);
        pane.add(jsp);
        msgField = new JTextField();
        msgField.setBounds(750, 680, 330, 30);
        msgField.addKeyListener(new KeyAdapter() {
            //回车事件
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendMsg();
                }
            }
        });
        pane.add(msgField);
        //按钮
        sendBtn = new JButton("发送");
        sendBtn.setBounds(1100,680, 60, 30);
        sendBtn.addActionListener(e->sendMsg());
        pane.add(sendBtn);
        readyBtn = new JButton("准备");
        readyBtn.setBounds(50, 730, 100, 50);
        readyBtn.addActionListener(e->{
            boolean ready = !gameData.getSelfPlayer().getReady();
            readyBtn.setText(ready?"已准备":"准备");
            gameData.getSelfPlayer().setReady(ready);
            gameData.setChessboard(new ChessBoard());
            ServiceFactory.getGameService().readyChange(ready);
            repaint();
        });
        pane.add(readyBtn);

        exitBtn = new JButton("退出");
        exitBtn.setBounds(200, 730, 100, 50);
        exitBtn.addActionListener(e -> {
            exitBtn.setEnabled(false);
            ServiceFactory.getGameService().ExitRoom();
            UIFactory.getGameFrame().setVisible(false);
            UIFactory.getRoomFrame().init();
            UIFactory.getRoomFrame().setVisible(true);
        });
        pane.add(exitBtn);
        //监听鼠标按下事件
        this.addMouseListener(new ClickListener());
    }

    //每次显示界面都要执行初始化
    void init(){
        //请求获取对手数据
        ServiceFactory.getGameService().getCompetitorData();
        //初始化数据
        gameData = new ClientGameData();
        gameData.setStatus(GameStatus.INITIALIZING);
        Player selfPlayer = new Player();
        selfPlayer.setUsername(ServiceFactory.getUserService().getUser().getUsername());
        selfPlayer.setReady(false);
        gameData.setSelfPlayer(selfPlayer);
        //组件初始化
        selfLabel.setText("玩家一:"+selfPlayer.getUsername());
        competitorLabel.setText("玩家二:等待加入");
        readyBtn.setEnabled(true);
        readyBtn.setText("准备");
        exitBtn.setEnabled(true);
        statusLabel.setText("当前游戏状态:");
        msgArea.append(ServiceFactory.getUserService().getUser().getUsername()+"进入游戏\r\n");
    }

    //更新游戏数据
    @Deprecated
    public void updateGameData(ServerGameData serverGameData){
        //保存自身数据
        Player clientPlayer = serverGameData.getByUsername(ServiceFactory.getUserService().getUser().getUsername());
        gameData.setSelfPlayer(clientPlayer);
        if(clientPlayer.getChessType() == ChessType.BLACKNESS){
            selfLabel.setText("玩家一:" + clientPlayer.getUsername()+"   黑棋");
        }else{
            selfLabel.setText("玩家一:" + clientPlayer.getUsername()+"   白棋");
        }
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
/*        if(serverGameData.getStatus().equals(GameStatus.START)){
            gameData.setMyTurn(serverGameData.getMoveUsername().equals(gameData.getSelfPlayer().getUsername()));
            gameData.setChessboard(serverGameData.getChessBoard());
            moveUsername.setText("当前执子玩家:"+gameData.getSelfPlayer().getUsername());
        }*/
    }

    @Override
    public void paint(Graphics g) {
        Graphics pane = getContentPane().getGraphics();
        if(image == null){
            image = createImage(getContentPane().getWidth(), getContentPane().getHeight());
        }
        Graphics imageGraphics = image.getGraphics();
        super.paint(imageGraphics);
        //画棋盘
        imageGraphics.drawImage(boardImage, 26, 50, BOARD_WIDTH, BOARD_HEIGHT, this);
        //画棋子
        if(gameData != null){
            for(int x = 0;x < 19;x++){
                for(int y = 0; y< 19; y++){
                    ChessType chess = gameData.getChess(x, y);
                    if(chess != null && chess != ChessType.EMPTY){
                        //有棋, 计算坐标
                        Point p = new Point();
                        p.x = (x * LATTICE_SIZE) + BOARD_ZERO_POINT.x - CHESS_SIZE/2;
                        p.y = (y * LATTICE_SIZE) + BOARD_ZERO_POINT.y - CHESS_SIZE/2;
                        if(chess == ChessType.WHITENESS){
                            imageGraphics.drawImage(whiteChessImage, p.x, p.y, CHESS_SIZE, CHESS_SIZE, this);
                        }else{
                            imageGraphics.drawImage(blackChessImage, p.x, p.y, CHESS_SIZE, CHESS_SIZE, this);
                        }
                    }
                }
            }
        }
        g.drawImage(image, 0 , 0,null);
    }



    //玩家准备事件
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

    /**
     * 开始游戏
     * @param theInitiativeName 先手用户名
     */
    public void startGame(String theInitiativeName) {
        msgArea.append("开始游戏\r\n");
        readyBtn.setEnabled(false);
        gameData.setStatus(GameStatus.START);
        //判断谁是先手
        if(gameData.getSelfPlayer().getUsername().equals(theInitiativeName)){
            gameData.getSelfPlayer().setChessType(ChessType.BLACKNESS);
            gameData.getCompetitor().setChessType(ChessType.WHITENESS);
            gameData.setMyTurn(true);
            statusLabel.setText("当前执子玩家:"+theInitiativeName);
        }else{
            gameData.getSelfPlayer().setChessType(ChessType.WHITENESS);
            gameData.getCompetitor().setChessType(ChessType.BLACKNESS);
            gameData.setMyTurn(false);
            statusLabel.setText("当前执子玩家:"+theInitiativeName);
        }
    }

    //对方离开房间
    public void CompetitorLeave(){
        readyBtn.setText("准备");
        msgArea.append(gameData.getCompetitor().getUsername()+"离开了游戏");
        gameData.getSelfPlayer().setReady(false);
        readyBtn.setEnabled(true);
        gameData.setCompetitor(null);
        competitorLabel.setText("玩家二:");
        if(gameData.getStatus() == GameStatus.START){
            JOptionPane.showMessageDialog(this, "对方离开游戏, 你获胜了", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        gameData.setStatus(GameStatus.END);
    }
    public void setCompetitor(Player competitor) {
        competitorLabel.setText("玩家二:"+competitor.getUsername());
        gameData.setCompetitor(competitor);
    }

    //鼠标点击事件
    private class ClickListener extends MouseAdapter {
        //判断点击有效
        @Override
        public void mouseReleased(MouseEvent e) {
            //仅鼠标右键点击有效
            if(!(e.getButton() == MouseEvent.BUTTON1)){
                return;
            }
            //游戏未开始,或者当前非自己走棋,不进行任何操作
            if(!gameData.getStatus().equals(GameStatus.START) || !gameData.isMyTurn()){
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
                //落子
                if(gameData.moveChess(x_, y_,gameData.getSelfPlayer().getChessType())){
                    gameData.setMyTurn(false);
                    statusLabel.setText("当前执子玩家:"+gameData.getCompetitor().getUsername());
                    //发送消息
                    ServiceFactory.getGameService().moveChess(x_, y_);
                    repaint();
                }
            }
        }
    }

    //游戏结束
    public void gameOver(String winner){
        msgArea.append("游戏结束, 玩家:"+winner+"获胜\r\n");
        gameData.setStatus(GameStatus.END);
        readyBtn.setText("准备");
        gameData.getSelfPlayer().setReady(false);
        readyBtn.setEnabled(true);
        if(winner.equals(gameData.getSelfPlayer().getUsername())){
            JOptionPane.showMessageDialog(this, "游戏结束,恭喜你获胜!", "提示", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "游戏结束, 玩家:"+winner+"获胜", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        repaint();
    }
    //对手落子
    public void competitorMove(int x, int y){
        gameData.moveChess(x, y, gameData.getCompetitor().getChessType());
        gameData.setMyTurn(true);
        statusLabel.setText("当前执子玩家:"+gameData.getSelfPlayer().getUsername());
        repaint();
    }

    //消息来了
    public void msgComing(ServerTextMsg textMsg){
        msgArea.append(textMsg.getFromUsername()+":"+textMsg.getText());
    }
    //有用户进入
    public void userEnter(String username){
        msgArea.append(username + "进入房间\r\n");
        Player competitor;
        if(gameData.getSelfPlayer().getChessType() == ChessType.BLACKNESS){
            competitor = new Player(username, ChessType.WHITENESS, false);
        }else{
            competitor = new Player(username, ChessType.BLACKNESS, false);
        }

        gameData.setCompetitor(competitor);
        competitorLabel.setText("玩家二:"+username);
    }

    private void sendMsg(){
        String msg = msgField.getText();
        if(msg.equals("")){
            return;
        }
        ServiceFactory.getMessageService().sendTextMsg(msg);
        msgField.setText("");
        msgArea.append("你" + ":" + msg + "\r\n");
    }
    //文本消息
    public void textMsg(String username, String msg){
        msgArea.append(username + ":" + msg + "\r\n");
    }

}
