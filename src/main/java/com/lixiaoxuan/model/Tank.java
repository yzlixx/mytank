package com.lixiaoxuan.model;

import com.lixiaoxuan.GameModel;
import com.lixiaoxuan.GameObject;
import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.config.ResourceMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;
import com.lixiaoxuan.strategy.DefaultFireStrategy;
import com.lixiaoxuan.strategy.FireStrategy;
import lombok.Data;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.UUID;

/**
 * @author lixiaoxuan
 * @description: tank类
 * @date 2021/5/27 16:37
 */
@Data
public class Tank extends GameObject {

    public static int WIDTH = ResourceMgr.goodTankU.getWidth();

    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public DirectionEnum dir;

    private final static int SPEED = Integer.parseInt((String) PropertyMgr.get("tankSpeed"));

    public Group group;

    private Random random = new Random();

    private boolean living = true;

    private boolean moving = true;

    public Rectangle rect = new Rectangle();

    public UUID id = UUID.randomUUID();

    int oldX, oldY;

    FireStrategy fs;

    public void paint(Graphics g) {


        if (!living) {
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {

        oldX = x;
        oldY = y;

        if (!living) return;

        if (!moving) return;

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

        if (this.group == Group.BAD && random.nextInt(1000) > 995) {
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

        if (group == Group.BAD) {
            String badFs = (String) PropertyMgr.get("badFS");
            try {
                fs = (FireStrategy) Class.forName(badFs).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String goodFs = (String) PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFs).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        GameModel.getInstance().add(this);
    }

    public void fire() {
        fs.fire(this);
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
    }

    public void back() {
        x = oldX;
        y = oldY;
    }
}
