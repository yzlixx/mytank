package com.lixiaoxuan;

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
