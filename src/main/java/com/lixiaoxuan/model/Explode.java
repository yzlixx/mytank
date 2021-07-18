package com.lixiaoxuan.model;

import com.lixiaoxuan.GameModel;
import com.lixiaoxuan.GameObject;
import com.lixiaoxuan.config.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author lixiaoxuan
 * @description: 爆炸类
 * @date 2021/5/28 15:39
 */
public class Explode extends GameObject {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private int step = 0;
    private static final BufferedImage[] explodes = ResourceMgr.explodes;

    public void paint(Graphics g) {
        //窗口绘画
        g.drawImage(explodes[step++], x, y, null);
        if (step >= explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
