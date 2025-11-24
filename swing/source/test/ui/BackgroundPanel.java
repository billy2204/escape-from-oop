package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;
    
    public BackgroundPanel(String imagePath) {
        setLayout(null);
        
        // Debug: kiểm tra file có tồn tại không
        File file = new File(imagePath);
        System.out.println("Đang tải background từ: " + file.getAbsolutePath());
        System.out.println("File tồn tại? " + file.exists());
        
        if (file.exists()) {
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            this.backgroundImage = icon.getImage();
            
            if (backgroundImage != null) {
                System.out.println("✓ Background loaded: " + backgroundImage.getWidth(null) + "x" + backgroundImage.getHeight(null));
            } else {
                System.err.println("✗ Failed to load image from: " + imagePath);
            }
        } else {
            System.err.println("✗ File not found: " + file.getAbsolutePath());
            this.backgroundImage = null;
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // Vẽ background màu xám nếu không có ảnh
            g.setColor(java.awt.Color.DARK_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
