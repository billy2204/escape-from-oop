package managers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import entities.Entity;

/**
 * Quản lý game - clean version
 */
public class GameManager {
    
    private static GameManager instance;
    private List<Entity> entities;
    
    private GameManager() {
        entities = new ArrayList<>();
    }
    
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    /** Gọi khi bắt đầu game */
    public void startGame() {
        entities.clear();
        // TODO: Thêm entities ở đây
        // entities.add(new Player(100, 100));
    }
    
    /** Thêm entity vào game */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /** Xóa entity khỏi game */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    
    /** Cập nhật tất cả entities */
    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
    
    /** Vẽ tất cả entities */
    public void render(Graphics2D g2) {
        for (Entity entity : entities) {
            if (entity.isVisible()) {
                entity.render(g2);
            }
        }
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
}
