package com.lixiaoxuan;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;
import com.lixiaoxuan.model.Tank;
import com.lixiaoxuan.model.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 所有游戏模型的门面(单例)
 */
public class GameModel {

    private static final GameModel INSTANCE = new GameModel();

    Tank myTank;


    static {
        INSTANCE.init();
    }

    private List<GameObject> objects = new ArrayList<>();



    private void init() {
        //添加主战坦克
        myTank = new Tank(200, 400, DirectionEnum.DOWN, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        // 初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i * 20, 200, DirectionEnum.DOWN, Group.BAD));
        }

        // 初始化墙
        add(new Wall(150, 150, 200, 50));
        add(new Wall(550, 150, 200, 50));
        add(new Wall(300, 300, 50, 200));
        add(new Wall(550, 300, 50, 200));
    }

    private GameModel() {
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
    }


    public Tank getMainTank() {
        return myTank;
    }

    // 删除模型
    public void remove(GameObject o) {
        this.objects.remove(o);
    }

    // 添加模型
    public void add(GameObject o) {
        this.objects.add(o);
    }
}
