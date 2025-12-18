package entities.items;

import entities.Entity;

public class Door extends Entity {
    
    private boolean isClose = true;

    public Door(float x, float y) {
        // animation key: "door"
        super(x, y, 64, 40, "door");
        
        // stationary
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