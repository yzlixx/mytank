package com.lixiaoxuan;

import com.lixiaoxuan.model.Audio;
import com.lixiaoxuan.model.TankFrame;

/**
 * @author lixiaoxuan
 * @description: main方法
 * @date 2021/5/27 13:29
 */
public class SpringApplication {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = TankFrame.getInstance();

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
