package entities.items;

import entities.Entity;

public class Button extends Entity {
    
    private boolean isPress = false;

    public Button(float x, float y) {
        super(x, y, 52, 40, "button");
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        if (isPress) {
            setAnimation("press");
        } 
        else {
            setAnimation("idle");
        }
    }

    public void press() {
        if (!isPress) {
            this.isPress = true;
        }
    }
    
    public boolean isPress() { 
        return isPress ; 
    }
}