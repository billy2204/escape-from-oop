package core;

import entities.Entity;
import physics.BoxCollider;
import java.awt.Rectangle;
import java.util.List;

public class CollisionManager {

    private MapManager mapManager;

    public CollisionManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }
    public boolean checkExit(Rectangle area) {
        List<Rectangle> exitAreas = mapManager.getExit();
        for (Rectangle exit : exitAreas) {
            if (area.intersects(exit)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Kiểm tra một vùng (Rect) có bị kẹt vào TƯỜNG không.
     * Dùng cho thuật toán di chuyển của Player và AI.
     */
    public boolean isSolid(Rectangle area) {
        List<Rectangle> walls = mapManager.getWalls();
        for (Rectangle wall : walls) {
            if (area.intersects(wall)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Kiểm tra xem Player có đi vào CỔNG không
     */
    public boolean checkGate(Rectangle area) {
        List<Rectangle> gates = mapManager.getGates();
        for (Rectangle gate : gates) {
            if (area.intersects(gate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kiểm tra va chạm giữa Entity này với danh sách các Entity khác.
     * Trả về Entity đầu tiên va chạm (hoặc null).
     * Dùng cho: Player va chạm Enemy, Enemy tránh nhau (Separation Steering).
     */
    public Entity checkEntityCollision(Entity source, List<Entity> others) {
        BoxCollider sourceBounds = source.getCollider();
        
        for (Entity target : others) {
            if (target == source) continue; // Bỏ qua chính mình
            if (!target.isActive()) continue; // Bỏ qua entity đã chết

            if (sourceBounds.intersects(target.getCollider())) {
                return target;
            }
        }
        return null;
    }
}