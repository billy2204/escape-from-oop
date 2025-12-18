package entities.items;

import entities.Entity;

public class Chest extends Entity {
    
    private boolean isOpen = false;
    private boolean price = false;
    public Chest(float x, float y) {
        // animation key: "chest"
        super(x, y, 32, 32, "chest");
        
        // stationary
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        if (isOpen && !price) {
            setAnimation("open");
        }
        else if (isOpen && price) {
            setAnimation("open_with_price");
        } else {
            setAnimation("idle");
        }
    }

    public void collect() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Chest opened!"); 
        }
    }
    
    public boolean isCollected() { 
        return isOpen; 
    }
    public void setPrice() {
        price = true;
    }
    public boolean isEarnPrice() {
        return price;
    }
}