package graphics;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Interface cho Renderer - tuân theo Dependency Inversion Principle
 * Các class khác chỉ phụ thuộc vào interface, không phụ thuộc vào implementation
 */
public interface initRenderer {
    void render(Graphics2D g2);
    void setPanel(JPanel panel);
}