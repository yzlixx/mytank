package com.lixiaoxuan.tank;

import com.lixiaoxuan.enums.DirectionEnum;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author lixiaoxuan
 * @description: 按键事件监听
 * @date 2021/5/27 14:46
 */
public class MyKeyListener implements KeyListener {

    private Tank tank;
    private boolean dirL = false;
    private boolean dirR = false;
    private boolean dirU = false;
    private boolean dirD = false;

    public MyKeyListener(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                dirL = true;
                break;
            case KeyEvent.VK_D:
                dirR = true;
                break;
            case KeyEvent.VK_W:
                dirU = true;
                break;
            case KeyEvent.VK_S:
                dirD = true;
                break;
            default:
                break;
        }
        setMainTankDir();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_A:
                dirL = false;
                break;
            case KeyEvent.VK_D:
                dirR = false;
                break;
            case KeyEvent.VK_W:
                dirU = false;
                break;
            case KeyEvent.VK_S:
                dirD = false;
                break;
            case KeyEvent.VK_SPACE:
                // todo fire()
                tank.fire();
                break;
            default:
                break;
        }
        setMainTankDir();
    }

    private void setMainTankDir() {
        if(dirL){
            tank.setDir(DirectionEnum.LEFT);
        }
        if(dirR){
            tank.setDir(DirectionEnum.RIGHT);
        }
        if(dirU){
            tank.setDir(DirectionEnum.UP);
        }
        if(dirD){
            tank.setDir(DirectionEnum.DOWN);
        }

    }
}
