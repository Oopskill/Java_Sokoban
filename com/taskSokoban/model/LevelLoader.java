package com.taskSokoban.model;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class LevelLoader {
    File file;
    public LevelLoader(Path levels) {
        file = levels.toFile();
    }

    public GameObjects getLevel(int level){
        String resultStrng = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            boolean isLevelMap = false;
            String line;
            if (level % 60 != 0)
            level %= 60;
            else level = 60;
            int readLevel = 0;
            while ((line = reader.readLine()) != null){

                if (line.contains("Maze: ")) {
                    readLevel = Integer.parseInt(line.split("Maze: ")[1]);
                    continue;
                }
                    if (readLevel == level){
                        if (line.length() == 0) {
                            boolean isEnd = isLevelMap;

                            isLevelMap = !isLevelMap;

                            if (isEnd && !isLevelMap) {
                                break;
                            } else {
                                continue;
                            }
                        }
                        if (line.contains("X") && !line.contains("Size"))
                            resultStrng += line + "\r\n";
                    }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<Wall> walls = new LinkedHashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        int y = Model.FIELD_CELL_SIZE / 2;
        int x = Model.FIELD_CELL_SIZE / 2;
        char[] chars = resultStrng.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            if (chars[j] == '\r') {
                y += Model.FIELD_CELL_SIZE;
                x = Model.FIELD_CELL_SIZE / 2;
                j++;
                continue;
            }

            if (chars[j] == 'X'){
                walls.add(new Wall(x, y));
            }
            if (chars[j] == '*'){
                boxes.add(new Box(x, y));
            }
            if (chars[j] == '.'){
                homes.add(new Home(x, y));
            }
            if (chars[j] == '@'){
                player = new Player(x, y);
            }
            if (chars[j] == '&'){
                boxes.add(new Box(x, y));
                homes.add(new Home(x, y));
            }
            x += Model.FIELD_CELL_SIZE;
        }

        return new GameObjects(walls, boxes, homes, player);
    }
}
