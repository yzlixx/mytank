package com.lixiaoxuan.tank;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.config.ResourceMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;
import lombok.Data;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

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

    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));

    private Group group;

    private Random random = new Random();

    private boolean living = true;

    private boolean moving = true;

    Rectangle rect = new Rectangle();

    private UUID id = UUID.randomUUID();

    public void paint(Graphics g) {


        if (!living) {
            TankFrame.getInstance().tankList.remove(this);
            moving = false;
            return;
        }
        //窗口绘画
        switch (dir) {
            case UP:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankU : ResourceMgr.goodTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankD : ResourceMgr.goodTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankL : ResourceMgr.goodTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.BAD ? ResourceMgr.badTankR : ResourceMgr.goodTankR, x, y, null);
                break;
            default:
                break;
        }

        move();
    }

    private void move() {

        if(!living) return;

        if(!moving) return ;

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

        if (this.group == Group.BAD && random.nextInt(1000) > 995){
            this.fire();
        }


        if (this.group == Group.BAD && random.nextInt(1000) > 995) {
            randomDir();
        }

        boundsCheck();
        rect.x = this.x;
        rect.y = this.y;

    }

    public Tank(int x, int y, DirectionEnum dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = x;
        rect.y = y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Bullet bullet = new Bullet(bX, bY, this.dir, this.id);
        TankFrame.getInstance().bulletList.add(bullet);
    }

    //边界检测
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }

    private void randomDir() {
        this.setDir(DirectionEnum.values()[random.nextInt(4)]);
    }

    public void die() {
        this.living = false;
        int eX = this.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
        int eY = this.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
        TankFrame.getInstance().explodeList.add(new Explode(eX, eY));
    }
}
