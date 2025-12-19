package entities.characters;

import physics.Vector2D;

public class Nanako extends NonEnemy {
    private boolean follow = false;
    public Nanako(float x, float y) {
        super(x, y, 50, 70, "nanako");
        rb.setPhysicsParams(3.5f, 0.5f, 0.9f);
        setdDetectionRadius(100.0f);
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
        else {
            setFollowPlayer();
        }

        Vector2D seek = getSeekForce(targetPos);
        Vector2D sep = getSeparationForce(35.0f, 3.0f); 

        rb.applyForce(seek);
        rb.applyForce(sep);
        updateStandardAnimation();
    }
    public boolean isFollowPlayer() {
        return follow;
    }
    public void setFollowPlayer() {
        this.follow = true;
    }
}