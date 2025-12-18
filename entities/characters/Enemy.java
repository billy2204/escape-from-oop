package entities.characters;

import entities.Entity;
import physics.Vector2D;
import java.util.List;

public abstract class Enemy extends Entity {

    protected Player target;
    protected List<Entity> neighbors;
    
    protected boolean isProvoked = false;
    protected float detectionRadius = 120.0f;
    protected long spawnTime; 

    public Enemy(float x, float y, int width, int height, String type) {
        super(x, y, width, height, type);
        this.spawnTime = System.currentTimeMillis();
    }

    public void setAIContext(Player target, List<Entity> neighbors) {
        this.target = target;
        this.neighbors = neighbors;
    }

    protected boolean shouldChasePlayer(Vector2D targetPos) {
        if (System.currentTimeMillis() - spawnTime < 1500) {
            return false;
        }
        if (isProvoked) return true;
        Vector2D myPos = new Vector2D(transform.x, transform.y);
        float distance = Vector2D.distance(myPos, targetPos);

        if (distance <= detectionRadius) {
            isProvoked = true;
            return true;
        }

        return false;
    }


    protected Vector2D getSeekForce(Vector2D targetPos) {
        return rb.seekForce(targetPos);
    }

    protected Vector2D getSeparationForce(float radius, float strength) {

        Vector2D sum = new Vector2D(0, 0);
        int count = 0;
        Vector2D myPos = new Vector2D(transform.x, transform.y);
        if (neighbors != null) {
            for (Entity other : neighbors) {
                if (other == this || !other.isActive() || !(other instanceof Enemy)) continue;
                Vector2D otherPos = new Vector2D(other.getTransform().x, other.getTransform().y);
                float d = Vector2D.distance(myPos, otherPos);
                if (d > 0 && d < radius) {
                    Vector2D diff = Vector2D.subtract(myPos, otherPos);
                    diff.normalize(); diff.multiply(1.0f / d); sum.add(diff); count++;
                }
            }
        }
        if (count > 0) {
            sum.multiply(1.0f / count); sum.normalize(); sum.multiply(rb.getMaxSpeed());
            sum.subtract(rb.getVelocity()); sum.limit(rb.getMaxForce()); sum.multiply(strength);
        }
        return sum;
    }
    public void setdDetectionRadius(float detectionRadius) {
        this.detectionRadius = detectionRadius;
    }
}