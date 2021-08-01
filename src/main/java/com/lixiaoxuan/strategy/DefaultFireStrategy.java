package com.lixiaoxuan.strategy;

import com.lixiaoxuan.GameModel;
import com.lixiaoxuan.decorator.RectDecorator;
import com.lixiaoxuan.decorator.TailDecorator;
import com.lixiaoxuan.enums.Group;
import com.lixiaoxuan.model.Audio;
import com.lixiaoxuan.model.Bullet;
import com.lixiaoxuan.model.Tank;

public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
//        new Bullet(bX, bY, t.dir, t.id, t.group);
        GameModel.getInstance().add(
                new RectDecorator(
                        new TailDecorator(
                                new Bullet(bX, bY, t.dir, t.id, t.group))));
        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
