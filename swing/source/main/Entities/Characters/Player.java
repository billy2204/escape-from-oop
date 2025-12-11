package entities.characters;

import entities.Entity;
import java.awt.Color;

/**
 * Player - Nhân vật người chơi
 * Single Responsibility: Di chuyển + màu fallback
 */
public class Player extends Entity {
    
    private int speed = 3;
    // Player does not use persistent velocity; movement applied directly.
    
    public Player(int x, int y) {
        super(x, y, 48, 48);
        this.renderLayer = 2;
    }
    @Override
    public void update() { // or 'public void update(long delta)' if that's the signature
        // TODO: implement behavior for player update
    }

    /** Đăng ký các animation cần thiết thông qua AnimationProvider (DI) */
    public void registerAnimations(graphics.AnimationProvider provider) {
        if (provider == null) return;
        registerAnimation("idle", provider.createPlayerAnimator("idle"));
        registerAnimation("walk_left", provider.createPlayerAnimator("walk_left"));
        registerAnimation("walk_right", provider.createPlayerAnimator("walk_right"));
        setAnimation("idle");
    }
    
    // ===== MOVEMENT =====
    
    // Movement methods modify position directly
    public void moveUp()    { y -= speed; }
    public void moveDown()  { y += speed; }
    public void moveLeft()  { x -= speed; }
    public void moveRight() { x += speed; }
    public void stopMoving() { /* no-op for direct movement */ }
    
    // ===== GETTERS/SETTERS =====
    
    public int getSpeed() { return speed; }
    public void setSpeed(int speed) { this.speed = speed; }
}
