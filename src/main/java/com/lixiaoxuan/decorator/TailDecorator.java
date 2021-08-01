package com.lixiaoxuan.decorator;

import com.lixiaoxuan.GameObject;

import java.awt.*;

public class TailDecorator extends GODecorator{
    public TailDecorator(GameObject go) {
        super(go);
    }


    @Override
    public void paint(Graphics graphics) {
        this.x = go.x;
        this.y = go.y;

        go.paint(graphics);
        Color c = graphics.getColor();
        graphics.setColor(Color.WHITE);
        graphics.drawLine(go.x, go.y, go.x + getWidth(), go.y + getHeight());
        graphics.setColor(c);
    }

    @Override
    public int getWidth() {
        return go.getWidth();
    }

    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
