package entities.items;

import entities.Entity;

public class Door extends Entity {
    
    private boolean isClose = true;

    public Door(float x, float y) {
        super(x, y, 64, 40, "door");
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        if (isClose) {
            setAnimation("close");
        } else {
            setAnimation("open");
        }
    }

    public void open() {
        if (isClose) {
            isClose = false;
        }
    }
    
    public boolean isClose() { 
        return isClose ; 
    }
}