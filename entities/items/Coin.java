package entities.items;

import entities.Entity;

public class Coin extends Entity {
    
    private boolean isEarned = false;

    public Coin(float x, float y) {
        // "chest" là key để Registry tìm animation
        super(x, y, 32, 32, "coin");
        
        // Rương nặng, đứng im
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        // Logic chuyển animation dựa trên trạng thái
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