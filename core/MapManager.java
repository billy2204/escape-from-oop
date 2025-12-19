package core;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapManager {
    private List<Rectangle> walls;
    private List<Rectangle> gates;
    private List<Rectangle> exit;
    
    private final int mapWidth = 1024;
    private final int mapHeight = 512;
    public MapManager() {
        walls = new ArrayList<>();
        gates = new ArrayList<>();
        exit = new ArrayList<>();
        setupMap();
    }

    private void setupMap() {
        addWall(0, 0, mapWidth, 1); 
        addWall(0, mapHeight - 1, mapWidth, mapHeight);
        addWall(0, 0, 1, mapHeight);
        addWall(mapWidth - 1, 0, mapWidth, mapHeight);
        addWall(0, 289, 288, 343);
        addWall(288, 240, 304, 418);
        addWall(304, 240, 520, 262);
        addWall(304, 377, 520, 418);
        addWall(520, 262, 508, 288);
        addWall(510, 356, 520, 380);
        addWall(289, 0, 303, 101);
        addWall(543, 0, 557, 101);
        addWall(289, 101, 387, 145);
        addWall(690, 0, 703, 162);
        addWall(690, 240, 1024, 290);
        addWall(390,103,450,146);

        addWall(451, 101, 559, 145);
        addExit(0,374,10,447);
    }

    private void addWall(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        
        if (w == 0) w = 1;
        if (h == 0) h = 1;

        walls.add(new Rectangle(x, y, w, h));
    }

    public void removeWallAt(int x1, int y1, int x2, int y2) {

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        Rectangle areaToRemove = new Rectangle(x, y, w, h);

        walls.removeIf(wall -> areaToRemove.contains(wall));
    }
    private void addExit(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        exit.add(new Rectangle(x, y, w, h));
    }
    
    public void draw(Graphics2D g) {
        g.setColor(new Color(0, 255, 0, 100));
        for (Rectangle r : gates) {
            g.fillRect(r.x, r.y, r.width, r.height);
        }
    }
    public List<Rectangle> getWalls() {
        return walls;
    }
    public List<Rectangle> getGates() {
        return gates;
    } 
    public List<Rectangle> getExit() {
        return exit;
    }

}