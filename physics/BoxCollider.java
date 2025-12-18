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

    // Lấy hình chữ nhật tại vị trí hiện tại
    public Rectangle getBounds() {
        return new Rectangle(
            (int) (transform.x + offsetX),
            (int) (transform.y + offsetY),
            width,
            height
        );
    }

    // Lấy hình chữ nhật tại vị trí DỰ ĐOÁN (để check trước khi đi)
    public Rectangle getBoundsAt(float nextX, float nextY) {
        return new Rectangle(
            (int) (nextX + offsetX),
            (int) (nextY + offsetY),
            width,
            height
        );
    }
    
    // Check va chạm với collider khác
    public boolean intersects(BoxCollider other) {
        return this.getBounds().intersects(other.getBounds());
    }
    
    // Check va chạm với tường tĩnh (Rectangle)
    public boolean intersects(Rectangle wall) {
        return this.getBounds().intersects(wall);
    }
}