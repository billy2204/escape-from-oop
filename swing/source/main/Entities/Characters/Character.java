package entities.Characters;

import entities.Entity;
import interfaces.ICollidable;
import java.awt.Graphics2D;

/**
 * Abstract base class for all characters (Player, Enemy, NPC)
 * Uses Template Method Pattern for movement and behavior
 */
public abstract class Character extends Entity {
    
    // Movement
    protected int speed;
    protected int velocityX;
    protected int velocityY;
    
    // Direction (for animation)
    protected String direction = "down";
    
    // Animation
    protected int animationFrame = 0;
    protected int animationTick = 0;
    protected int animationSpeed = 10;
    
    public Character(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.speed = getDefaultSpeed();
    }
    
    // Template methods - subclasses define their behavior
    protected abstract int getDefaultSpeed();
    protected abstract void onDeath();
    
    // Movement methods
    public void moveUp() {
        velocityY = -speed;
        direction = "up";
    }
    
    public void moveDown() {
        velocityY = speed;
        direction = "down";
    }
    
    public void moveLeft() {
        velocityX = -speed;
        direction = "left";
    }
    
    public void moveRight() {
        velocityX = speed;
        direction = "right";
    }
    
    public void stop() {
        velocityX = 0;
        velocityY = 0;
    }
    
    public void stopX() {
        velocityX = 0;
    }
    
    public void stopY() {
        velocityY = 0;
    }
    
    @Override
    public void update() {
        // Apply velocity
        x += velocityX;
        y += velocityY;
        
        // Update animation if moving
        if (velocityX != 0 || velocityY != 0) {
            updateAnimation();
        }
    }
    
    @Override
    public void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationFrame++;
        }
    }
    
    @Override
    protected String getDefaultState() {
        return "idle";
    }
    
    // Getters
    public String getDirection() { return direction; }
    public int getSpeed() { return speed; }
    public int getVelocityX() { return velocityX; }
    public int getVelocityY() { return velocityY; }
    public int getAnimationFrame() { return animationFrame; }
}
