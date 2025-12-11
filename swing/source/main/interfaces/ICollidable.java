package interfaces;

import java.awt.Rectangle;

public interface ICollidable {
    /**
     * Trả về hitbox (hình chữ nhật) của entity để check va chạm
     */
    Rectangle getHitBox();
}
