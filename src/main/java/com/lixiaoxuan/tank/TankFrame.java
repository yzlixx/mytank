package com.lixiaoxuan.tank;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author lixiaoxuan
 * @description: 游戏窗口画布(单例)
 * @date 2021/5/27 13:32
 */
public class TankFrame extends Frame {

    private static volatile TankFrame tankFrame;
    static final int GAME_WIDTH = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameWidth")));
    static final int GAME_HEIGHT = Integer.valueOf((String) Objects.requireNonNull(PropertyMgr.get("gameHeight")));
    Random r = new Random();
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), DirectionEnum.DOWN, Group.GOOD);
    Image offScreenImage = null;

    List<Bullet> bulletList = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();

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
        tankList.add(myTank);
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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bulletList.size(), 10, 60);
        g.drawString("tanks:" + tankList.size(), 10, 80);
        g.drawString("explodes" + explodeList.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for (int j = 0; j < explodeList.size(); j++) {
            explodeList.get(j).paint(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (Tank t : tankList)
                bulletList.get(i).collideWith(t);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }
    }

    public void addTank(Tank tank) {
        tankList.add(tank);
    }
}
