package physics;

import java.awt.Rectangle;

public class Transform {
    public float x, y;
    public int width, height;

    public Transform(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public Rectangle getHitbox() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}