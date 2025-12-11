package entities.items;
import entities.Entity;

import java.awt.Color;

/**
 * Door that can be locked/unlocked
 */
public class Door extends Entity {
    
    private boolean isLocked = true;
    
    public Door(int x, int y) {
        super(x, y, 64, 80);
        this.renderLayer = 1;
    }
    public void registerAnimations(graphics.AnimationProvider provider) {
        if (provider == null) return;
        registerAnimation("idle", provider.createDoorAnimator("idle"));
        registerAnimation("open", provider.createDoorAnimator("open"));
        setAnimation("idle");
    }

    public void open() {
        if (isLocked) {
            isLocked = false;
            setAnimation("open");
        }
    }
    public void close() {
        isLocked = true;
        setAnimation("idle");
    } 
    public boolean isLock() { return isLocked; }
    @Override
    public void update() { // or 'public void update(long delta)' if that's the signature
        // TODO: implement behavior for player update
    }
    @Override
    public void updateAnimation() {
            
    }
}
