package com.lixiaoxuan.model;

import com.lixiaoxuan.GameModel;
import com.lixiaoxuan.config.PropertyMgr;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

/**
 * @author lixiaoxuan
 * @description: 游戏窗口画布(单例)
 * @date 2021/5/27 13:32
 */
public class TankFrame extends Frame {

    GameModel gm = GameModel.getInstance();

//    private static TankFrame INSTANCE = new TankFrame();
    static final int GAME_WIDTH = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameWidth")));
    static final int GAME_HEIGHT = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameHeight")));
    Image offScreenImage = null;

//    public static TankFrame getInstance() {
////        if (INSTANCE == null) {
////            synchronized (TankFrame.class) {
////                if (INSTANCE == null) {
////                    INSTANCE = new TankFrame();
////                }
////            }
////        }
//        return INSTANCE;
//    }

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle((String) PropertyMgr.get("gameTitle"));
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * @description: 消除双缓冲
     * @author lixiaoxuan
     * @date 2021/5/27 15:22
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
    }

}
