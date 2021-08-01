package com.lixiaoxuan.decorator;

import com.lixiaoxuan.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator {
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics graphics) {
        this.x = go.x;
        this.y = go.y;

        go.paint(graphics);

        Color c = graphics.getColor();
        graphics.setColor(Color.YELLOW);
        graphics.drawRect(x, y, go.getWidth() + 2, go.getHeight() + 2);
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
