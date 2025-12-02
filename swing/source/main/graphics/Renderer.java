package graphics;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Renderer chính - quản lý việc vẽ tất cả objects
 * Single Responsibility: Chỉ lo việc render graphics
 */
public class Renderer implements initRenderer {
    
    private JPanel panel;
    private List<Renderable> renderables; // Danh sách các đối tượng cần vẽ
    
    public Renderer() {
        this.renderables = new ArrayList<>();
    }
    
    @Override
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
    
    /**
     * Thêm đối tượng vào danh sách render
     */
    public void addRenderable(Renderable renderable) {
        renderables.add(renderable);
    }
    
    /**
     * Xóa đối tượng khỏi danh sách render
     */
    public void removeRenderable(Renderable renderable) {
        renderables.remove(renderable);
    }
    
    /**
     * Xóa tất cả đối tượng
     */
    public void clearRenderables() {
        renderables.clear();
    }
    
    /**
     * Render tất cả đối tượng lên màn hình
     */
    @Override
    public void render(Graphics2D g2) {
        // Bật anti-aliasing để graphics mượt hơn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                           RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                           RenderingHints.VALUE_RENDER_QUALITY);
        
        // Vẽ từng đối tượng theo thứ tự (layer)
        for (Renderable renderable : renderables) {
            if (renderable.isVisible()) {
                renderable.render(g2);
            }
        }
    }
    
    /**
     * Vẽ background
     */
    public void renderBackground(Graphics2D g2, Image backgroundImage) {
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, panel.getWidth(), panel.getHeight(), null);
        }
    }
    
    /**
     * Vẽ debug info (FPS, vị trí, etc.)
     */
    public void renderDebugInfo(Graphics2D g2, String... info) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        
        int y = 20;
        for (String line : info) {
            g2.drawString(line, 10, y);
            y += 20;
        }
    }
}
