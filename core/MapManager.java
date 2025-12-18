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
    public boolean showWalls = false;
    public MapManager() {
        walls = new ArrayList<>();
        gates = new ArrayList<>();
        exit = new ArrayList<>();
        setupMap();
    }

    private void setupMap() {
        // 1. Setup Border 
        // Top & Bottom
        addWall(0, 0, mapWidth, 1); 
        addWall(0, mapHeight - 1, mapWidth, mapHeight);
        // Left & Right
        addWall(0, 0, 1, mapHeight);
        addWall(mapWidth - 1, 0, mapWidth, mapHeight);
        // ================= ROOM 1 =================
        addWall(0, 289, 288, 343);
        addWall(288, 240, 304, 418);
        addWall(304, 240, 520, 262);
        addWall(304, 377, 520, 418);
        addWall(520, 262, 508, 288);
        addWall(510, 356, 520, 380);
        // ================= ROOM 2 =================
        addWall(289, 0, 303, 101);
        addWall(543, 0, 557, 101);
        addWall(289, 101, 387, 145);
        // ================= ROOM 3 =================
        addWall(690, 0, 703, 162);
        addWall(690, 240, 1024, 290);
        addWall(390,103,450,146);

        addWall(451, 101, 559, 145);
        addExit(0,374,10,447);
    }

    /**
     * HELPER: Tự động tính x, y, width, height từ 2 điểm bất kỳ
     * Giúp bạn copy code cũ sang mà không cần tính toán lại.
     */
    private void addWall(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        
        // Tránh lỗi width/height = 0 (tạo ra tường vô hình 1px cho an toàn)
        if (w == 0) w = 1;
        if (h == 0) h = 1;

        walls.add(new Rectangle(x, y, w, h));
    }
    // Trong file core/MapManager.java

    public void removeWallAt(int x1, int y1, int x2, int y2) {
        // 1. Tạo hình chữ nhật đại diện cho khu vực cần xóa
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        Rectangle areaToRemove = new Rectangle(x, y, w, h);

        // Use 'contains' to remove walls fully inside the area
        walls.removeIf(wall -> areaToRemove.contains(wall));
        
        // Log to check (optional)
        System.out.println("MapManager: Đã thực hiện xóa tường trong khu vực " + areaToRemove);
        System.out.println("Tổng số tường còn lại: " + walls.size());
    }
    private void addExit(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x1 - x2);
        int h = Math.abs(y1 - y2);
        exit.add(new Rectangle(x, y, w, h));
    }
    
    public void draw(Graphics2D g) {
        // Vẽ tường đỏ mờ (chỉ khi bật)
        if (showWalls) {
            // Fill with semi-transparent red
            g.setColor(new Color(255, 0, 0, 120));
            for (Rectangle r : walls) {
                g.fillRect(r.x, r.y, r.width, r.height);
            }
            // Draw solid border for visibility
            g.setColor(new Color(180, 0, 0));
            for (Rectangle r : walls) {
                g.drawRect(r.x, r.y, r.width, r.height);
            }
        }
        
        // Vẽ cổng xanh mờ
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
    public void toggleShowWalls() {
        showWalls = !showWalls;
        // In ra để debug xem đã bấm ăn chưa
        System.out.println("MapManager: Toggle Walls -> " + showWalls);
    }
    public boolean isShowWalls() {
        return showWalls;
    }
    /**
     * Remove any walls that intersect the given area.
     * Useful when opening a door (make the wall passable).
     */
    // (no extra helpers)
}