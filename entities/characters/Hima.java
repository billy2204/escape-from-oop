package entities.characters;

import physics.Vector2D;

public class Hima extends Enemy {

    public Hima(float x, float y) {
        super(x, y, 32, 32, "hima");
        // higher force/speed
        rb.setPhysicsParams(2f, 0.5f, 0.9f);
        setdDetectionRadius(150.0f);
    }

    @Override
    protected void updateBehavior(long deltaTime) {
        if (target == null) return;

        Vector2D targetPos = new Vector2D(target.getTransform().x, target.getTransform().y);

        if (!shouldChasePlayer(targetPos)) {
            rb.stop(); // Dừng lại
            setAnimation("idle");
            return; // Thoát hàm
        }

        Vector2D seek = getSeekForce(targetPos);
        Vector2D sep = getSeparationForce(35.0f, 3.0f); 

        rb.applyForce(seek);
        rb.applyForce(sep);
        updateStandardAnimation();
    }
}