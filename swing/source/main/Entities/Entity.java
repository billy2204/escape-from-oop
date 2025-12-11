package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import graphics.AnimationController;

/**
 * Entity - Base class cho tất cả game objects
 * Single Responsibility: Quản lý vị trí + animation state
 */
import interfaces.ICollidable;

public abstract class Entity implements ICollidable {
        
    // Vị trí và kích thước
    protected int x, y, width, height;
    
    // Hiển thị entity
    protected boolean visible = true;
    
    // Animation
    protected Map<String, AnimationController> animations = new HashMap<>();
    protected AnimationController currentAnimation;
    protected String currentState = "idle";
    
    // Render order
    protected int renderLayer = 0;
    
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    // ===== ANIMATION =====
    
    /** Đăng ký animation cho 1 state */
    public void registerAnimation(String state, AnimationController anim) {
        animations.put(state, anim);
        if (currentAnimation == null && state.equals("idle")) {
            currentAnimation = anim;
        }
    }
    
    /** Chuyển sang animation khác */
    public void setAnimation(String state) {
        if (animations.containsKey(state) && !state.equals(currentState)) {
            currentState = state;
            currentAnimation = animations.get(state);
            currentAnimation.reset();
            currentAnimation.play();
        }
    }
    
    /** Update animation mỗi frame */
    public void updateAnimation() {
        if (currentAnimation != null) {
            currentAnimation.updateAnimation();
        }
    }
    
    // ===== ABSTRACT METHODS =====
    
    public abstract void update();
    
    /** Override để đổi màu fallback */
    
    // ===== RENDER (chung cho tất cả entity) =====
    
    public void render(Graphics2D g2) {
        // Vẽ animation nếu có
        if (currentAnimation != null && currentAnimation.hasFrames()) {
            java.awt.image.BufferedImage frame = currentAnimation.getCurrentFrame();
            if (frame != null) {
                g2.drawImage(frame, x, y, width, height, null);
                return;
            }
        }
        
        // Fallback: vẽ hình chữ nhật với màu từ subclass
        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, width, height);
    }
    @Override
        public java.awt.Rectangle getHitBox() {
            return new java.awt.Rectangle(x, y, width, height);
        }
    
    
    // ===== GETTERS/SETTERS =====
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getRenderLayer() { return renderLayer; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setRenderLayer(int layer) { this.renderLayer = layer; }
}
