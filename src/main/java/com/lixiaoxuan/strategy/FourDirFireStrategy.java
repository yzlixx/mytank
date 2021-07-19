package com.lixiaoxuan.strategy;

import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;
import com.lixiaoxuan.model.Audio;
import com.lixiaoxuan.model.Bullet;
import com.lixiaoxuan.model.Tank;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        DirectionEnum[] dirs = DirectionEnum.values();
        for (DirectionEnum dir : dirs) {
            new Bullet(bX, bY, dir, t.id, t.group);
        }
        if (t.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
