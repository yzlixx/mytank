package com.lixiaoxuan.tank;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.config.ResourceMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import lombok.Data;

import java.awt.*;

/**
 * @author lixiaoxuan
 * @description: tank类
 * @date 2021/5/27 16:37
 */
@Data
public class Tank {

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private int x, y;

    private DirectionEnum dir;

    private TankFrame tankFrame = null;

    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));

    public void paint(Graphics g) {
        //窗口绘画
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
    }

    public Tank(int x, int y, DirectionEnum dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Bullet bullet = new Bullet(bX,bY,dir);
        tankFrame.bulletList.add(bullet);
    }
}