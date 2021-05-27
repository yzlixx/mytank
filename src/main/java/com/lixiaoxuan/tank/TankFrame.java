package com.lixiaoxuan.tank;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.enums.DirectionEnum;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author lixiaoxuan
 * @description: 游戏窗口画布(单例)
 * @date 2021/5/27 13:32
 */
public class TankFrame extends Frame {

    private static volatile TankFrame tankFrame;
    private final int GAME_WIDTH = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameWidth")));
    private final int GAME_HEIGHT = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameHeight")));
    Random r = new Random();
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), DirectionEnum.DOWN, this);
    Image offScreenImage = null;

    List<Bullet> bulletList = new ArrayList<>();

    public static TankFrame getInstance() {
        if (tankFrame == null) {
            synchronized (TankFrame.class) {
                if (tankFrame == null) {
                    tankFrame = new TankFrame();
                }
            }
        }
        return tankFrame;
    }

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle((String) PropertyMgr.get("gameTitle"));
        this.addKeyListener(new MyKeyListener(myTank));
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
        myTank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
    }
}
