package Entities.Characters;

/**
 * Enemy - Character with AI behavior
 * Doesn't have health (can't be killed)
 * Kills player instantly on contact
 */
public class Enemy extends Character {
    private int detectionRange;
    
    public Enemy(int x, int y) {
        super(x, y, 32, 32, "enemy");
        this.detectionRange = 100;
    }
    
    @Override
    protected String getDefaultState() {
        return "patrol";
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 2;
    }
    
    @Override
    public void update() {
        // AI behavior: patrol, chase player
        if (state.equals("patrol")) {
            patrol();
        } else if (state.equals("chase")) {
            chasePlayer();
        }
    }
    
    private void patrol() {
        // Simple patrol logic
        moveRight();
    }
    
    private void chasePlayer() {
        // Chase player logic
    }
    
    /**
     * Called when enemy touches player
     */
    public void onContactWithPlayer(Player player) {
        player.die();
    }
    
    public int getDetectionRange() {
        return detectionRange;
    }
}
