package entities.items;

import entities.Entity;

public class Coin extends Entity {
    
    private boolean isEarned = false;

    public Coin(float x, float y) {
        // animation key: "coin"
        super(x, y, 32, 32, "coin");
        
        // stationary
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        if (isEarned) {
            setAnimation("earned");
        } else {
            setAnimation("close"); // Hoặc "close"
        }
    }

    public void earn() {
        if (!isEarned) {
            isEarned = true;
        }
    }
    public boolean isEarned() {
        return isEarned;
    }
}