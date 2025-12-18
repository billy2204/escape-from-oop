package entities.characters;

import entities.Entity;
import input.IInputHandler;
import physics.Vector2D;

public class Player extends Entity {

    private IInputHandler input;
    private boolean moving = false;
    public Player(float x, float y, IInputHandler input) {
        super(x, y, 48, 48, "player"); 
        this.input = input;
        rb.setPhysicsParams(5.0f, 1.0f, 0.8f);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        float xDir = 0;
        float yDir = 0;

        if (input.isLeft())  xDir = -1;
        if (input.isRight()) xDir = 1;
        if (input.isUp())    yDir = -1;
        if (input.isDown())  yDir = 1;
        // mark moving implicitly via velocity; no manual flag needed
        if (input.isPressed()) {
            this.moving = true;
        } 
        Vector2D direction = new Vector2D(xDir, yDir);
        
        if (direction.length() > 0) {
            direction.normalize();
            direction.multiply(rb.getMaxSpeed());
            rb.setVelocity(direction.x, direction.y);
        } else {
            rb.applyFriction();
        }

        // --- GỌI HÀM CỦA CHA (Code ngắn gọn hẳn) ---
        updateStandardAnimation(); 
        
    }

    // Return true if the player's rigidbody currently has non-zero velocity
    public boolean isMoving() { return moving ;}
    
}