package entities.items;

import entities.Entity;

public class Chest extends Entity {
    
    private boolean isOpen = false;
    private boolean price = false;
    public Chest(float x, float y) {
        // "chest" là key để Registry tìm animation
        super(x, y, 32, 32, "chest");
        
        // Rương nặng, đứng im (Speed=0, Force=0)
        rb.setPhysicsParams(0, 0, 0);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        // Logic chuyển animation
        if (isOpen && !price) {
            setAnimation("open");
        }
        else if (isOpen && price) {
            setAnimation("open_with_price");
        } else {
            setAnimation("idle");
        }
    }

    // open the chest
    public void collect() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Chest opened!"); 
        }
    }
    
    // return true if chest has been opened
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