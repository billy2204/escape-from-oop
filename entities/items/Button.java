package entities.items;

import entities.Entity;

public class Button extends Entity {
    
    private boolean isPress = false;

    public Button(float x, float y) {
        // animation key: "button"
        super(x, y, 52, 40, "button");
        
        // stationary
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
            System.out.println("Button Pressed!"); 
        }
    }
    
    public boolean isPress() { 
        return isPress ; 
    }
}