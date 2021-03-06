package com.taskSokoban.view;

import com.taskSokoban.controller.Controller;
import com.taskSokoban.controller.EventListener;
import com.taskSokoban.model.GameObjects;

import javax.swing.*;

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void update(){
        field.repaint();
    }

    public void completed(int level){
        update();
        JOptionPane.showMessageDialog(this,"Level " + level  + " complete");
        controller.startNextLevel();
    }

    public GameObjects getGameObjects(){
        return controller.getGameObjects();
    }

    public void setEventListener(EventListener eventListener){
        field.setEventListener(eventListener);
    }
}
