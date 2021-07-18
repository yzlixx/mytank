package com.lixiaoxuan;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    //游戏物体的坐标
    protected int x,y;

    public abstract void paint(Graphics graphics);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
