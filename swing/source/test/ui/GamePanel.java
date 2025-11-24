package ui;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

/**
 * GamePanel - Màn hình game
 */
public class GamePanel extends JPanel {
    
    public GamePanel() {
        setLayout(null);
        setBackground(Color.BLACK);
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Vẽ chest
        
        // UI
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("GAME STARTED!", 150, 50);
        
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Press ESC to return to menu", 130, 450);
    }
    
}
