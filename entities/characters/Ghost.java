package entities.characters;

import physics.Vector2D;

public class Ghost extends Enemy {

    public Ghost(float x, float y) {
        super(x, y, 50, 50, "ghost");
        // Bay lượn: Force thấp, Friction cao
        rb.setPhysicsParams(1.5f, 0.05f, 0.99f);
        setdDetectionRadius(200.0f);
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

        // Nếu chạy xuống đây nghĩa là đang đuổi
        Vector2D seek = getSeekForce(targetPos);
        seek.multiply(1.5f); 

        rb.applyForce(seek); // Ghost không cần Separation
        updateStandardAnimation();
    }
}