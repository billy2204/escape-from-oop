package graphics;

import java.awt.Graphics2D;

/**
 * Interface cho tất cả objects có thể render được
 * Open/Closed Principle: Mở rộng bằng cách implement interface, không sửa code cũ
 */
public interface Renderable {
    void render(Graphics2D g2);
    boolean isVisible();
    int getLayer(); // Để sắp xếp thứ tự vẽ (background = 0, player = 1, UI = 2, etc.)
}
