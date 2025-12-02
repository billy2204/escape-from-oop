package components.items;

/**
 * Base class for all items in the game
 * Template: Extend this class to create specific item types
 */
public abstract class Item {
    private int x; // coordinate x
    private int y; // coordinate y
    protected String type;
    protected String state; // Trạng thái hiện tại (idle, open, etc.)
    
    public Item(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.state = getDefaultState();
    }
    
    /**
     * Override this method to define default state for each item type
     */
    protected String getDefaultState() {
        return "idle";
    }
    
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public String getType() { return type; }
    public String getState() { return state; }
    
    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setState(String state) { this.state = state; }
    
    /**
     * Update item logic (override for specific behavior)
     */
    public void update() {
        // Template method - override in subclasses
    }
}

/**
 * Template for items that cannot be used/interacted with
 * Example: Decorative objects, obstacles
 */
abstract class UnusableItem extends Item {
    public UnusableItem(int x, int y, String type) {
        super(x, y, type);
    }
}

/**
 * Template for items that can be used/interacted with
 * Example: Chest, Door, Button
 */
abstract class UsableItem extends Item {
    public UsableItem(int x, int y, String type) {
        super(x, y, type);
    }
    
    /**
     * Template method - implement interaction logic
     */
    public abstract void interact();
}