package managers;

import java.awt.Graphics2D;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.items.Chest;
import entities.characters.Player;
import graphics.AnimationFactory;
import input.KeyboardInput;

/**
 * Quản lý game
 */
public class GameManager {
    
    private static GameManager instance;
    private List<Entity> entities;
    private Player player;
    private KeyboardInput input;
    
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
     * @param component Component cần lắng nghe phím (JPanel, JFrame, etc.)
     */
    public void registerInput(Component component) {
        component.addKeyListener(input);
        component.setFocusable(true);
        component.requestFocus();
    }
    
    /** Gọi khi bắt đầu game */
    public void startGame() {
        entities.clear();
        
        // Tạo Player
        player = new Player(100, 100);
        addEntity(player);
        
        // Tạo Chest
        Chest chest = new Chest(310, 290);
        chest.registerAnimation("idle", AnimationFactory.createChestAnimator("idle"));
        chest.registerAnimation("open", AnimationFactory.createChestAnimator("open"));
        addEntity(chest);
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
        // Xử lý input cho player
        if (player != null && input != null) {
            if (input.isRequestingUp()) {
                player.moveUp();
            }
            if (input.isRequestingDown()) {
                player.moveDown();
            }
            if (input.isRequestingLeft()) {
                player.moveLeft();
            }
            if (input.isRequestingRight()) {
                player.moveRight();
            }
        }
        
        // Update tất cả entities
        for (Entity entity : entities) {
            entity.updateLogic();
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
}
