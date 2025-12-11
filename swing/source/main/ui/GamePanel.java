package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.io.File;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import physics.*;
import managers.GameManager;
import entities.characters.Player;

/**
 * GamePanel -Màn hình game chính
 */
public class GamePanel extends JPanel {
    
    private Image mapImage;
    private GameManager gameManager;
    private int mouseX = 0, mouseY = 0;  // Tọa độ chuột
    
    public GamePanel() {
        setBackground(Color.BLACK);
        setFocusable(true);
        
        System.out.println("=== GamePanel Constructor ===");
        
        // Load map
        loadMap("resources/map/map.png");
        
        // Setup game
        gameManager = GameManager.getInstance();
        gameManager.registerInput(this);
        gameManager.startGame();
        
        // Lắng nghe chuột di chuyển
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        
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
            // Nếu load map thành công, luôn đặt preferred size của panel bằng kích thước 1024x512
            setPreferredSize(new Dimension(1024, 512));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // 1. Vẽ map scaled để lấp đầy panel (bạn sẽ tạo cửa sổ 1024x512)
        if (mapImage != null) {
            g.drawImage(mapImage, 0, 0, getWidth(), getHeight(), this);
        }
        
        // 2. Vẽ map tường (pixel đỏ)
        if (gameManager != null) {
            physics.Wall wall = gameManager.getWall();
            if (wall.canRender()) {
                wall.render(g2);
            }
        }
        
        // 3. Vẽ entities
        gameManager.render(g2);

        // 4. Vẽ tọa độ chuột (góc trên trái)
        g2.setColor(Color.RED);
        g2.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2.drawString("X: " + mouseX + ", Y: " + mouseY, 10, 20);

        // 5. Vẽ tọa độ của Player để debug/mô phỏng thuật toán di chuyển
        if (gameManager != null) {
            Player p = gameManager.getPlayer();
            if (p != null) {
                int px = p.getX();
                int py = p.getY();
                // Tọa độ player (bên dưới dòng chuột)
                g2.setColor(Color.YELLOW);
                g2.drawString("Player: X: " + px + ", Y: " + py, 10, 40);

                // Vẽ dấu hiệu (hình tròn) tại vị trí player để dễ xác định
                int markSize = 8;
                int centerX = px + p.getWidth() / 2 - markSize/2;
                int centerY = py + p.getHeight() / 2 - markSize/2;
                g2.setColor(new Color(255, 255, 0, 160)); // vàng bán trong suốt
                g2.fillOval(centerX, centerY, markSize, markSize);
                g2.setColor(Color.BLACK);
                g2.drawOval(centerX, centerY, markSize, markSize);
            }
        }
        
    }
}
