package entities.characters;

import entities.Entity;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Enemy character with AI behavior
 */
public class Enemy extends Entity {
    
    private int speed = 2;
    
    
    public Enemy(int x, int y) {
        super(x, y, 32, 32);
        this.renderLayer = 5;
    }
    @Override
    public void update() { // or 'public void update(long delta)' if that's the signature
        // TODO: implement behavior for player update
    }
    public void registerAnimations(graphics.AnimationProvider provider) {
        if (provider == null) return;
        registerAnimation("idle", provider.createPlayerAnimator("idle"));
        registerAnimation("walk_left", provider.createPlayerAnimator("walk_left"));
        registerAnimation("walk_right", provider.createPlayerAnimator("walk_right"));
        setAnimation("idle");
    }
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        // Color based on AI state
        g2.fillRect(x, y, width, height);
    }
    
}
