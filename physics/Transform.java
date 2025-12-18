package physics;

import java.awt.Rectangle;

public class Transform {
    // Dùng float để lưu vị trí chính xác (vd: 10.5) giúp di chuyển mượt.
    // Khi vẽ lên màn hình mới ép kiểu về int.
    public float x, y;
    public int width, height;

    public Transform(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
  //
    // Tạo hitbox để check va chạm
    public Rectangle getHitbox() {
        return new Rectangle((int)x, (int)y, width, height);
    }
}