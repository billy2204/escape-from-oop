package core;

import entities.Entity;
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
        
        float dx = vel.x; 
        float dy = vel.y;

        float nextX = entity.getTransform().x + dx;
        Rectangle boxX = col.getBoundsAt(nextX, entity.getTransform().y);
        
        if (!collisionManager.isSolid(boxX)) {
            entity.getTransform().x += dx;
        } else {
            vel.x = 0; 
        }

        float nextY = entity.getTransform().y + dy;
        Rectangle boxY = col.getBoundsAt(entity.getTransform().x, nextY);
        
        if (!collisionManager.isSolid(boxY)) {
            entity.getTransform().y += dy;
        } else {
            vel.y = 0;
        }
    }
}