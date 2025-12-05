package entities.characters;
package entities.Characters;

import entities.*;
import interfaces.ICollidable;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Enemy character with AI behavior
 * Uses State Pattern for different behaviors
 */
public class Enemy extends Entity {
    
    public enum AIState {
        IDLE, PATROL, CHASE, ATTACK
    }
    
    private AIState aiState = AIState.PATROL;
    private Player target;
    
    // Patrol settings
    private int patrolStartX;
    private int patrolEndX;
    private boolean patrollingRight = true;
    
    // Chase settings
    private int detectionRange = 150;
    private int attackRange = 30;
    
    // Attack settings
    private int attackDamage = 10;
    private int attackCooldown = 60;
    private int attackTimer = 0;
    
    public Enemy(int x, int y) {
        super(x, y, 32, 32);
        this.patrolStartX = x - 100;
        this.patrolEndX = x + 100;
        this.renderLayer = 5;
    }
    
    public void setTarget(Player player) {
        this.target = player;
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 2;
    }
    
    @Override
    protected void onDeath() {
        visible = false;
        System.out.println("Enemy died!");
    }
    
    @Override
    public void update() {
        if (attackTimer > 0) attackTimer--;
        
        switch (aiState) {
            case PATROL:
                doPatrol();
                checkForPlayer();
                break;
            case CHASE:
                doChase();
                checkAttackRange();
                break;
            case ATTACK:
                doAttack();
                break;
            case IDLE:
                // Do nothing
                break;
        }
        
        super.update();
    }
    
    private void doPatrol() {
        if (patrollingRight) {
            moveRight();
            if (x >= patrolEndX) patrollingRight = false;
        } else {
            moveLeft();
            if (x <= patrolStartX) patrollingRight = true;
        }
    }
    
    private void checkForPlayer() {
        if (target == null || !target.isAlive()) return;
        
        double distance = getDistanceToTarget();
        if (distance < detectionRange) {
            aiState = AIState.CHASE;
        }
    }
    
    private void doChase() {
        if (target == null || !target.isAlive()) {
            aiState = AIState.PATROL;
            return;
        }
        
        double distance = getDistanceToTarget();
        if (distance > detectionRange * 1.5) {
            aiState = AIState.PATROL;
            return;
        }
        
        // Move toward player
        if (target.getX() > x) moveRight();
        else if (target.getX() < x) moveLeft();
        else stopX();
        
        if (target.getY() > y) moveDown();
        else if (target.getY() < y) moveUp();
        else stopY();
    }
    
    private void checkAttackRange() {
        if (getDistanceToTarget() < attackRange) {
            aiState = AIState.ATTACK;
        }
    }
    
    private void doAttack() {
        stop();
        
        if (target == null || !target.isAlive()) {
            aiState = AIState.PATROL;
            return;
        }
        
        if (getDistanceToTarget() > attackRange) {
            aiState = AIState.CHASE;
            return;
        }
        
        if (attackTimer <= 0) {
            target.takeDamage(attackDamage);
            attackTimer = attackCooldown;
        }
    }
    
    private double getDistanceToTarget() {
        if (target == null) return Double.MAX_VALUE;
        int dx = target.getX() - x;
        int dy = target.getY() - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    @Override
    public void onCollision(ICollidable other) {
        // Handle collision
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        
        // Color based on AI state
        switch (aiState) {
            case PATROL: g2.setColor(Color.ORANGE); break;
            case CHASE: g2.setColor(Color.RED); break;
            case ATTACK: g2.setColor(Color.MAGENTA); break;
            default: g2.setColor(Color.YELLOW); break;
        }
        g2.fillRect(x, y, width, height);
    }
    
    // Setters for patrol range
    public void setPatrolRange(int startX, int endX) {
        this.patrolStartX = startX;
        this.patrolEndX = endX;
    }
    
    public AIState getAIState() { return aiState; }
}
