package com.taskSokoban.model;

import java.awt.*;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        switch (direction){
            case LEFT :
                return gameObject.getX() == x - Model.FIELD_CELL_SIZE && gameObject.getY() == y;
            case RIGHT:
                return gameObject.getX() == x + Model.FIELD_CELL_SIZE && gameObject.getY() == y;
            case UP:
                return gameObject.getX() == x && gameObject.getY() == y - Model.FIELD_CELL_SIZE;
            case DOWN:
                return gameObject.getX() == x && gameObject.getY() == y + Model.FIELD_CELL_SIZE;
            default: return false;
        }
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
