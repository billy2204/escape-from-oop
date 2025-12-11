package entities.items;

import entities.Entity;
import java.awt.Color;

public class Chest extends Entity {
    
    private boolean opened = false;
    
    public Chest(int x, int y) {
        super(x, y, 32, 32);
        this.renderLayer = 1;
    }

    /** Đăng ký animation thông qua AnimationProvider (DI) */
    public void registerAnimations(graphics.AnimationProvider provider) {
        if (provider == null) return;
        registerAnimation("idle", provider.createChestAnimator("idle"));
        registerAnimation("open", provider.createChestAnimator("open"));
        setAnimation("idle");
    }
    
    public void open() {
        if (!opened) {
            opened = true;
            setAnimation("open");
        }
    }
    
    public void close() {
        opened = false;
        setAnimation("idle");
    } 
    
    public boolean isOpened() { return opened; }
    
    @Override
    public void update() {
        // Chest không cần update logic
    }
    
}
