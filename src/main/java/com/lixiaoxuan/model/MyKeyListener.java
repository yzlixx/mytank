package com.lixiaoxuan.model;

import com.lixiaoxuan.GameModel;
import com.lixiaoxuan.enums.DirectionEnum;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author lixiaoxuan
 * @description: 按键事件监听
 * @date 2021/5/27 14:46
 */
public class MyKeyListener implements KeyListener {

    GameModel gm = GameModel.getInstance();

    private boolean dirL = false;
    private boolean dirR = false;
    private boolean dirU = false;
    private boolean dirD = false;


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
        Tank myTank = gm.getMainTank();
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
                myTank.fire();
                break;
            default:
                break;
        }
        setMainTankDir();
    }

    private void setMainTankDir() {
        Tank myTank = gm.getMainTank();
        if(dirL){
            myTank.setDir(DirectionEnum.LEFT);
        }
        if(dirR){
            myTank.setDir(DirectionEnum.RIGHT);
        }
        if(dirU){
            myTank.setDir(DirectionEnum.UP);
        }
        if(dirD){
            myTank.setDir(DirectionEnum.DOWN);
        }

    }
}
