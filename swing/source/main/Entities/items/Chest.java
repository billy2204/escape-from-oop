package entities.items;

import interfaces.ICollidable;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Chest item that can be opened
 */
public class Chest extends UsableItem {
    
    private boolean open = false;
    private int animationFrame = 0;
    private final int totalFrames = 4;
    
    public Chest(int x, int y) {
        super(x, y, 32, 32);
    }
    
    public void open() {
        if (!open) {
            open = true;
            setState("open");
            onUse();
        }
    }
    
    public void close() {
        open = false;
        animationFrame = 0;
        setState("idle");
    }
    
    public boolean isOpen() {
        return open;
    }
    
    @Override
    protected void onUse() {
        System.out.println("Chest opened! Found treasure!");
    }
    
    @Override
    public void updateAnimation() {
        if (open && animationFrame < totalFrames - 1) {
            animationFrame++;
        }
    }
    
    @Override
    public void onCollision(ICollidable other) {
        // Player can open chest
        if (other instanceof entities.Characters.Player) {
            open();
        }
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        // Draw chest based on state
        if (open) {
            g2.setColor(new Color(139, 90, 43)); // Brown (open)
        } else {
            g2.setColor(new Color(101, 67, 33)); // Dark brown (closed)
        }
        g2.fillRect(x, y, width, height);
        
        // Lid
        g2.setColor(Color.YELLOW);
        g2.fillRect(x + 4, y + 4, width - 8, 8);
    }
}
