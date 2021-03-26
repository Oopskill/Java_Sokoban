package com.taskSokoban.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable{

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
//        Graphics2D g = (Graphics2D) graphics;
        graphics.setColor(Color.RED);
        graphics.fillOval(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
