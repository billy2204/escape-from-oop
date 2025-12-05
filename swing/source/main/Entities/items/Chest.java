package entities.items;

import interfaces.ICollidable;
import graphics.AnimationController;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import entities.Entity;

/**
 * Chest item that can be opened
 * Design Pattern: State Pattern + Strategy Pattern
 * - State Pattern: Chest có nhiều state (idle, open)
 * - Strategy Pattern: Mỗi state có animation riêng
 */
public class Chest extends Entity {
    
    private boolean open = false;
    
    // Strategy Pattern: Map state -> animation
    private Map<String, AnimationController> animations;
    private AnimationController currentAnimation;
    
    public Chest(int x, int y) {
        super(x, y, 32, 32);
        animations = new HashMap<>();
    }
    
    public Chest(int x, int y, int width, int height) {
        super(x, y, width, height);
        animations = new HashMap<>();
    }
    
    // === ANIMATION MANAGEMENT (Strategy Pattern) ===
    
    /**
     * Register animation cho một state
     * @param stateName Tên state ("open", "close",)
     * @param animation AnimationController tương ứng
     */
    public void registerAnimation(String stateName, AnimationController animation) {
        animations.put(stateName, animation);
        if (currentAnimation == null && stateName.equals("idle")) {
            currentAnimation = animation;
        }
    }
    
    /**
     * Chuyển sang animation của state khác (State Pattern)
     * @param stateName Tên state cần chuyển đến
     */
    public void setAnimation(String stateName) {
        AnimationController newAnimation = animations.get(stateName);
        if (newAnimation != null && newAnimation != currentAnimation) {
            currentAnimation = newAnimation;
            currentAnimation.reset();
            currentAnimation.play();
            setState(stateName);
        }
    } 
    // === ACTIONS ===
    
    public void open() {
        if (!open) {
            setState("open");
            setAnimation("open");
        }
    }
    
    public void close() {
        if (open) {
            setState("idle");
            setAnimation("idle");
        }
    }
    // === Abstract methods từ Entity ===
    // === IUpdateState ===
    




    @Override
    public void updateLogic() {
        // Logic update cho Chest (nếu cần)
    }
    
    @Override
    public void updateAnimation() {
        if (currentAnimation != null) {
            currentAnimation.update();
        }
    }
    
    public void onCollision(ICollidable other) {
        // Xử lý va chạm nếu cần
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        // Vẽ sprite animation
        if (currentAnimation != null && currentAnimation.hasFrames()) {
            BufferedImage frame = currentAnimation.getCurrentFrame();
            if (frame != null) {
                g2.drawImage(frame, x, y, width, height, null);
                return;
            }
        }
        
        // Fallback: vẽ hình chữ nhật nếu không có sprite
        if (open) {
            g2.setColor(new Color(139, 90, 43));
        } else {
            g2.setColor(new Color(101, 67, 33));
        }
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.YELLOW);
        g2.fillRect(x + 4, y + 4, width - 8, 8);
    }
}
