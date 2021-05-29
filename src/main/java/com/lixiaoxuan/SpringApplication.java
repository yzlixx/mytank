package com.lixiaoxuan;

import com.lixiaoxuan.config.PropertyMgr;
import com.lixiaoxuan.enums.DirectionEnum;
import com.lixiaoxuan.enums.Group;
import com.lixiaoxuan.tank.Audio;
import com.lixiaoxuan.tank.Tank;
import com.lixiaoxuan.tank.TankFrame;

/**
 * @author lixiaoxuan
 * @description: main方法
 * @date 2021/5/27 13:29
 */
public class SpringApplication {

    public static void main(String[] args) {
        TankFrame instance = TankFrame.getInstance();
        instance.setVisible(true);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            instance.addTank(new Tank(50 + i * 80, 200, DirectionEnum.DOWN, Group.BAD));
        }

//        new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(25);
                    instance.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
