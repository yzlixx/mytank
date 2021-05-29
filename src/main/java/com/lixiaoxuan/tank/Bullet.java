package com.lixiaoxuan.tank;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.config.ResourceMgr;
import com.lixiaoxuan.enums.DirectionEnum;

import java.awt.*;
import java.util.UUID;

/**
 * @author lixiaoxuan
 * @description: 子弹类
 * @date 2021/5/27 20:09
 */
public class Bullet {

    public static int WIDTH = ResourceMgr.bulletD.getWidth();

    public static int HEIGHT = ResourceMgr.bulletD.getHeight();

    private int x, y;

    private DirectionEnum dir;

    Rectangle rect = new Rectangle();

    private UUID playerId;

    private boolean living = true;

    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("bulletSpeed"));

    public Bullet(int x, int y, DirectionEnum dir, UUID playerId) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.playerId = playerId;
        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {

        if (!living) {
            TankFrame.getInstance().bulletList.remove(this);
            return;
        }
        //窗口绘画
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
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

        //边界检测
        rect.x = this.x;
        rect.y = this.y;
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collideWith(Tank tank) {
        if (this.playerId.equals(tank.getId())) {
            return;
        }
        if (this.living && tank.isLiving() && this.rect.intersects(tank.rect)) {
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
