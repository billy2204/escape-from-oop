package entities.items;
import entities.Entity;
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
    
    @Override
    public void update() { // or 'public void update(long delta)' if that's the signature
        // TODO: implement behavior for player update
    }
    @Override
    public void updateAnimation() {
            
    }
}
