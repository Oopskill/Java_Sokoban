package com.taskSokoban.model;

import com.taskSokoban.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1; // change level from 1 to 60
    private LevelLoader levelLoader = new LevelLoader(Paths.get("D:\\My projects\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410\\res\\levels.txt"));

    public GameObjects getGameObjects(){
        GameObjects g = gameObjects;
        return gameObjects;

    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        restartLevel(++currentLevel);
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (this.checkWallCollision(player,direction)) return;
        if (this.checkBoxCollisionAndMoveIfAvailable(direction)) return;
        int dx = direction == Direction.LEFT ? -FIELD_CELL_SIZE : (direction == Direction.RIGHT ? FIELD_CELL_SIZE : 0);
        int dy = direction == Direction.UP ? -FIELD_CELL_SIZE : (direction == Direction.DOWN ? FIELD_CELL_SIZE : 0);
        player.move(dx,dy);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall : gameObjects.getWalls()) {
            if (gameObject.isCollision(wall,direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction){
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box,direction)){
                for (Box objectsBox : gameObjects.getBoxes()) {
                    if (box.isCollision(objectsBox,direction))
                        return true;
                }
                if (this.checkWallCollision(box,direction))
                    return true;

                int dx = direction == Direction.LEFT ? -FIELD_CELL_SIZE : (direction == Direction.RIGHT ? FIELD_CELL_SIZE : 0);
                int dy = direction == Direction.UP ? -FIELD_CELL_SIZE : (direction == Direction.DOWN ? FIELD_CELL_SIZE : 0);
                box.move(dx,dy);
            }
        }
        return false;
    }

    public void checkCompletion(){
        int checkPositionBoxes = 0;
        int countHome = gameObjects.getHomes().size();
        Player player = gameObjects.getPlayer();
        for (Home home : gameObjects.getHomes()) {
            for (Box box : gameObjects.getBoxes()) {
                if (home.x == box.x && home.y == box.y){
                    checkPositionBoxes++;
                }
            }
        }
        if (checkPositionBoxes == countHome){
            player.move(player.x + FIELD_CELL_SIZE,player.y + FIELD_CELL_SIZE);
            this.eventListener.levelCompleted(currentLevel);
        }
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

}
