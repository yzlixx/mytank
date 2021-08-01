package com.lixiaoxuan.decorator;

import com.lixiaoxuan.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics graphics) {

    }
}
