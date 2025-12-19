package entities;

import graphics.animation.*;
import physics.BoxCollider;
import physics.RigidBody;
import physics.Transform;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected Transform transform;
    protected RigidBody rb;
    protected BoxCollider collider;
    protected AnimationManager anim;
    protected String type;

    protected boolean facingRight = true;
    protected boolean active = true;

    public Entity(float x, float y, int width, int height, String type) {
        this.transform = new Transform(x, y, width, height);
        this.rb = new RigidBody(transform);
        this.type = type;
        int cW = (int)(width * 0.6);
        int cH = (int)(height * 0.4);
        float offX = (width - cW) / 2f;
        float offY = height - cH;
        this.collider = new BoxCollider(transform, cW, cH, offX, offY);

        this.anim = new AnimationManager();
        AnimationRegistry.load(this.anim, type);
    }

    public void update(long deltaTime) {
        if (!active) return;
        updateBehavior(deltaTime);
        anim.update(deltaTime);
    }

    protected abstract void updateBehavior(long deltaTime);
    protected void updateStandardAnimation() {
        if (Math.abs(rb.getVelocity().x) > 0.1f) {
            facingRight = rb.getVelocity().x > 0;
            setAnimation("run"); 
        } else {
            setAnimation("idle");
        }
    }
    public void render(Graphics2D g) {
        if (!active) return;
        
        BufferedImage frame = anim.getCurrentFrame();
        if (frame != null) {
            int drawX = (int) transform.x;
            int drawY = (int) transform.y;
            
            if (facingRight) {
                g.drawImage(frame, drawX, drawY, transform.width, transform.height, null);
            } else {
                g.drawImage(frame, drawX + transform.width, drawY, -transform.width, transform.height, null);
            }
        }

    }
    
    public Transform getTransform() { return transform; }
    public RigidBody getRigidBody() { return rb; }
    public BoxCollider getCollider() { return collider; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }

    protected void setAnimation(String name) {
        if (anim == null) return;
        if (!anim.hasAction(name)) {
            return;
        }
        anim.play(name);
    }

    public String getType() { return type; }

    /**
     * Dispose entity-local resources (animation state) to help GC when entity removed.
     */
    public void dispose() {
        if (anim != null) {
            anim.clear();
            anim = null;
        }
    }
}