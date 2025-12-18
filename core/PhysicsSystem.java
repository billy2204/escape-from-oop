package core;

import entities.Entity;
import core.CollisionManager;
import physics.BoxCollider;
import physics.RigidBody;
import physics.Vector2D;
import java.awt.Rectangle;

public class PhysicsSystem {
    private CollisionManager collisionManager;

    public PhysicsSystem(CollisionManager cm) {
        this.collisionManager = cm;
    }

    public void update(Entity entity, long deltaTime) {
        RigidBody rb = entity.getRigidBody();
        BoxCollider col = entity.getCollider();

        rb.update(); 
        
        Vector2D vel = rb.getVelocity();
        
        // 2. Compute displacement (note: deltaTime may need conversion to seconds)
        float dx = vel.x; 
        float dy = vel.y;

        // 3. CHECK VA CHẠM TƯỜNG (Sliding Logic)
        
        // --- Trục X ---
        float nextX = entity.getTransform().x + dx;
        Rectangle boxX = col.getBoundsAt(nextX, entity.getTransform().y);
        
        if (!collisionManager.isSolid(boxX)) {
            entity.getTransform().x += dx;
        } else {
            // Đụng tường X -> Dừng vận tốc X
            // Giúp quái không bị dính vào tường mà vẫn trượt dọc được
            vel.x = 0; 
        }

        // --- Trục Y ---
        float nextY = entity.getTransform().y + dy;
        Rectangle boxY = col.getBoundsAt(entity.getTransform().x, nextY);
        
        if (!collisionManager.isSolid(boxY)) {
            entity.getTransform().y += dy;
        } else {
            vel.y = 0;
        }
    }
}