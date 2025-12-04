package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import managers.GameManager;

/**
 * GamePanel - Màn hình game chính
 */
public class GamePanel extends JPanel {
    
    private Image mapImage;
    private GameManager gameManager;
    
    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        
        System.out.println("=== GamePanel Constructor ===");
        
        // Load map
        loadMap("/Users/billy.is.real/Documents/projectOOP/swing/source/main/resources/map/map.jpg");
        
        // Setup game
        gameManager = GameManager.getInstance();
        gameManager.registerInput(this);  // Đăng ký input handler
        gameManager.startGame();
        
        System.out.println("GamePanel initialized. Focus: " + isFocusable());
        
        // Game loop - 60 FPS
        Timer gameTimer = new Timer(16, e -> {
            gameManager.update();
            repaint();
        });
        gameTimer.start();
        
        System.out.println("Game loop started");
    }
    
    private void loadMap(String path) {
        File file = new File(path);
        if (file.exists()) {
            mapImage = new ImageIcon(file.getAbsolutePath()).getImage();
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // 1. Vẽ map
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
        
        // 2. Vẽ entities
        gameManager.render(g2);
        
        // 3. Vẽ UI
        // TODO: Thêm UI ở đây

    }
}
