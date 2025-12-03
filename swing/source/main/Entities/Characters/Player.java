package entities.Characters;

import interfaces.ICollidable;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Player character controlled by user input
 */
public class Player extends Character {
    
    private int health = 100;
    private int maxHealth = 100;
    private boolean alive = true;
    
    public Player(int x, int y) {
        super(x, y, 32, 32);
        this.renderLayer = 10; // Player renders above most entities
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 4;
    }
    
    @Override
    protected void onDeath() {
        alive = false;
        setState("dead");
        System.out.println("Player died!");
    }
    
    public void takeDamage(int damage) {
        if (!alive) return;
        
        health -= damage;
        if (health <= 0) {
            health = 0;
            onDeath();
        }
    }
    
    public void heal(int amount) {
        if (!alive) return;
        
        health = Math.min(health + amount, maxHealth);
    }
    
    @Override
    public void onCollision(ICollidable other) {
        // Handle collision with enemies, items, etc.
        if (other instanceof Enemy) {
            takeDamage(10);
        }
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        // Placeholder rendering - draw colored rectangle
        g2.setColor(alive ? Color.BLUE : Color.GRAY);
        g2.fillRect(x, y, width, height);
        
        // Health bar
        g2.setColor(Color.RED);
        g2.fillRect(x, y - 8, width, 5);
        g2.setColor(Color.GREEN);
        g2.fillRect(x, y - 8, (int)(width * ((double)health / maxHealth)), 5);
    }
    
    // Getters
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public boolean isAlive() { return alive; }
}
