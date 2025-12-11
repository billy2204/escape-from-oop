package managers;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.items.*;
import entities.characters.*;
import graphics.AnimationProvider;
import graphics.DefaultAnimationProvider;
import input.KeyboardInput;
import physics.Wall;



/**
 * Quản lý game
 */
public class GameManager {
    
    private static GameManager instance;
    private List<Entity> entities;
    private Player player;
    private Chest chest;
    private Door door;
    private KeyboardInput input;
    private Wall wall;
    private GameManager() {
        entities = new ArrayList<>();
        input = new KeyboardInput();
    }
    
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
    
    /**
     * Đăng ký KeyListener với game component (gọi từ GamePanel/GameWindow)
     */
    public void registerInput(Component component) {
        component.addKeyListener(input);
        component.setFocusable(true);
        component.requestFocus();
    }
    
    /** Gọi khi bắt đầu game */
    public void startGame() {
        entities.clear();
        wall = new Wall();
 
        // Tạo provider để tiêm animation 
        AnimationProvider provider = new DefaultAnimationProvider();

        // Dùng EntityFactory để tạo entity đã cấu hình
        entities.EntityFactory factory = new entities.EntityFactory(provider);

        player = factory.createPlayer(100, 100);
        addEntity(player);

        chest = factory.createChest(310, 263);
        addEntity(chest);

        door = factory.createDoor(390, 102);
        addEntity(door);

        
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
        if (player != null && input != null) {
            int speed = player.getSpeed();
            int dx = 0;
            int dy = 0;

            // 1. Xác định hướng di chuyển
            if (input.isRequestingLeft()) dx = -speed;
            else if (input.isRequestingRight()) dx = speed;

            if (input.isRequestingUp()) dy = -speed;
            else if (input.isRequestingDown()) dy = speed;

            // 2. Cập nhật vị trí (Đơn giản là cộng dồn)
            // Lưu ý: Nếu muốn sửa lỗi đi chéo nhanh, cần xử lý vector ở đây (nâng cao)
            player.setX(player.getX() + dx);
            player.setY(player.getY() + dy);

            // 3. Xử lý Animation
            // Ưu tiên hướng ngang (Left/Right) cho animation kể cả khi đi chéo
            if (dx < 0) {
                player.setAnimation("walk_left");
            } else if (dx > 0) {
                player.setAnimation("walk_right");
            } else if (dy != 0) {
                // Nếu chỉ đi dọc (dx == 0) thì giữ animation hướng cũ hoặc có animation riêng
                // Ví dụ: vẫn đang quay mặt sang phải thì giữ walk_right
            } else {
                player.setAnimation("idle");
            }
            wall.setGate(390,100, 453,145);
        }
        
        // Update tất cả entities
        for (Entity entity : entities) {
            entity.update();
            entity.updateAnimation();
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

    // Cho phép truy cập wall từ ngoài (GamePanel)
    public Wall getWall() {
        return wall;
    }

    // Trả về player (để GamePanel có thể truy cập vị trí để debug/render tọa độ)
    public Player getPlayer() {
        return player;
    }
}
