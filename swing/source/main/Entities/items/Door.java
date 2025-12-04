package entities.items;


import interfaces.ICollidable;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Door that can be locked/unlocked
 */
public class Door extends Entity {
    
    private boolean locked = true;
    private boolean open = false;
    
    public Door(int x, int y) {
        super(x, y, 32, 64);
    }
    
    public void unlock() {
        if (locked) {
            locked = false;
            setState("unlocked");
        }
    }
    
    public void lock() {
        if (!open) {
            locked = true;
            setState("locked");
        }
    }
    
    public void openDoor() {
        if (!locked && !open) {
            open = true;
            setState("open");
            onUse();
        }
    }
    
    public void closeDoor() {
        if (open) {
            open = false;
            setState(locked ? "locked" : "unlocked");
        }
    }
    
    public boolean isLocked() {
        return locked;
    }
    
    public boolean isOpen() {
        return open;
    }
    
    @Override
    protected void onUse() {
        System.out.println("Door opened!");
    }
    
    @Override
    public void onCollision(Ientities.characters
        if (other instanceof entities.Characters.Player) {
            if (!locked) {
                openDoor();
            } else {
                System.out.println("Door is locked!");
            }
        }
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        if (open) {
            // Open door - just outline
            g2.setColor(new Color(101, 67, 33));
            g2.drawRect(x, y, width, height);
        } else {
            // Closed door
            g2.setColor(new Color(101, 67, 33));
            g2.fillRect(x, y, width, height);
            
            // Lock indicator
            if (locked) {
                g2.setColor(Color.YELLOW);
                g2.fillOval(x + width/2 - 4, y + height/2, 8, 8);
            } else {
                g2.setColor(Color.GREEN);
                g2.fillOval(x + width/2 - 4, y + height/2, 8, 8);
            }
        }
    }
}
