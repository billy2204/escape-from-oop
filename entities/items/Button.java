package entities.items;

import entities.Entity;

public class Button extends Entity {
    
    private boolean isPress = false;

    public Button(float x, float y) {
        // "chest" là key để Registry tìm animation
        super(x, y, 52, 40, "button");
        
        // Rương nặng, đứng im (Speed=0, Force=0)
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        // Logic chuyển animation
        if (isPress) {
            setAnimation("press");
        } 
        else {
            setAnimation("idle");
        }
    }

    // press the button
    public void press() {
        if (!isPress) {
            this.isPress = true;
            System.out.println("Button Pressed!"); 
        }
    }
    
    // return true if button is pressed
    public boolean isPress() { 
        return isPress ; 
    }
}