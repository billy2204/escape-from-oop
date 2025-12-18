package physics;

import java.awt.Rectangle;

public class BoxCollider {
    public Transform transform; // Tham chiếu đến vị trí thật
    public int width, height;
    public float offsetX, offsetY; // Để căn hitbox vào giữa chân nhân vật

    public BoxCollider(Transform transform, int width, int height, float offsetX, float offsetY) {
        this.transform = transform;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public Rectangle getBounds() {
        return new Rectangle(
            (int) (transform.x + offsetX),
            (int) (transform.y + offsetY),
            width,
            height
        );
    }

    public Rectangle getBoundsAt(float nextX, float nextY) {
        return new Rectangle(
            (int) (nextX + offsetX),
            (int) (nextY + offsetY),
            width,
            height
        );
    }
    
    public boolean intersects(BoxCollider other) {
        return this.getBounds().intersects(other.getBounds());
    }
    
    public boolean intersects(Rectangle wall) {
        return this.getBounds().intersects(wall);
    }
}