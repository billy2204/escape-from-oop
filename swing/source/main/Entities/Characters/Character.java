package Entities.Characters;

/**
 * Base class for all characters in the game
 * Template: Extend this class to create Player, Enemy, NPC, etc.
 */
public abstract class Character {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String type;
    protected String state;
    protected int speed;
    
    public Character(int x, int y, int width, int height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.state = getDefaultState();
        this.speed = getDefaultSpeed();
    }
    
    /**
     * Template methods - Override in subclasses
     */
    protected abstract String getDefaultState();
    protected abstract int getDefaultSpeed();
    
    /**
     * Movement methods
     */
    public void moveUp() {
        y -= speed;
    }
    
    public void moveDown() {
        y += speed;
    }
    
    public void moveLeft() {
        x -= speed;
    }
    
    public void moveRight() {
        x += speed;
    }
    
    /**
     * Update character logic (override for AI, animations, etc.)
     */
    public void update() {
        // Template method - override in subclasses
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public String getType() { return type; }
    public String getState() { return state; }
    public int getSpeed() { return speed; }
    
    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setState(String state) { this.state = state; }
    public void setSpeed(int speed) { this.speed = speed; }
}
