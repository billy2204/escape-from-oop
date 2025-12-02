package components.characters;

/**
 * Player - Character controlled by user
 * Has health and can die
 */
public class Player extends Character {
    private int health;
    private boolean alive;
    
    public Player(int x, int y) {
        super(x, y, 32, 32, "player");
        this.health = 100;
        this.alive = true;
    }
    
    @Override
    protected String getDefaultState() {
        return "idle";
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 4;
    }
    
    @Override
    public void update() {
        // Player update logic
    }
    
    /**
     * Called when player dies
     */
    public void die() {
        this.alive = false;
        this.state = "dead";
    }
    
    /**
     * Take damage
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            die();
        }
    }
    
    // Getters
    public int getHealth() { return health; }
    public boolean isAlive() { return alive; }
    
    // Setters
    public void setHealth(int health) { this.health = health; }
}
