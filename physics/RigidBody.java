package physics;

public class RigidBody {
    private Transform transform;
    private Vector2D velocity;
    private Vector2D acceleration; 

    // Config vật lý
    private float mass = 1.0f;
    private float maxSpeed = 4.0f;  
    private float maxForce = 0.2f;  
    private float friction = 0.95f; 

    public RigidBody(Transform transform) {
        this.transform = transform;
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);
    }

    public void applyForce(Vector2D force) {
        Vector2D f = force.copy();
        f.multiply(1 / mass);
        acceleration.add(f);
    }

    public void update() {
        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        acceleration.multiply(0);
    }
    
    public void applyFriction() {
        velocity.multiply(friction);
        if (velocity.length() < 0.05f) velocity.multiply(0);
    }

    // stop velocity immediately
    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
    }

    public Vector2D seekForce(Vector2D targetPos) {
        Vector2D currentPos = new Vector2D(transform.x, transform.y);
        Vector2D desired = Vector2D.subtract(targetPos, currentPos);
        desired.normalize();
        desired.multiply(maxSpeed);
        Vector2D steer = Vector2D.subtract(desired, velocity);
        steer.limit(maxForce);
        return steer;
    }

    // Getters & Setters
    public Vector2D getVelocity() { return velocity; }
    public void setVelocity(float x, float y) { velocity.x = x; velocity.y = y; }
    public float getMaxSpeed() { return maxSpeed; }
    public float getMaxForce() { return maxForce; }
    
    public void setPhysicsParams(float speed, float force, float friction) {
        this.maxSpeed = speed;
        this.maxForce = force;
        this.friction = friction;
    }
}