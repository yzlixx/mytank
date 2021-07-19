package com.lixiaoxuan;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    //游戏物体的坐标
    public int x,y;

    public abstract void paint(Graphics graphics);
    public abstract int getWidth();
    public abstract int getHeight();
}
