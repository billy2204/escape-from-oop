package ui;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
// import components.items.Chest; // TODO: Tạo class Chest
import graphics.Renderer;

/**
 * GamePanel - Màn hình game
 */
public class GamePanel extends JPanel {
    
    private Renderer renderer;
    // private Chest chest1; // TODO: Tạo class Chest

    public GamePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        
        // Khởi tạo renderer
        renderer = new Renderer();
        
        // TODO: Cấu hình animation cho chest với delay 200ms
        // renderer.configAnimation("chest", "idle", 200);
        
        // TODO: Tạo chest instance
        // chest1 = new Chest(200, 300);
        
        // Timer để repaint liên tục cho animation
        Timer animationTimer = new Timer(50, e -> repaint());
        animationTimer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // TODO: Vẽ chest sử dụng renderer
        // renderer.draw(g, chest1, chest1.getX(), chest1.getY(), 64, 64);
        
        // UI
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("GAME STARTED!", 150, 50);
        
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Press ESC to return to menu", 130, 450);
    }
    
}
