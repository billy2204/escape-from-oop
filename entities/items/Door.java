package entities.items;

import entities.Entity;

public class Door extends Entity {
    
    private boolean isClose = true;

    public Door(float x, float y) {
        // "chest" là key để Registry tìm animation
        super(x, y, 64, 40, "door");
        
        // Rương nặng, đứng im (Speed=0, Force=0)
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        // Logic chuyển animation
        if (isClose) {
            setAnimation("close");
        } else {
            setAnimation("open");
        }
    }

    // open the door
    public void open() {
        if (isClose) {
            isClose = false;
            System.out.println("Door opened!"); 
        }
    }
    
    // return true if door is closed
    public boolean isClose() { 
        return isClose ; 
    }
}