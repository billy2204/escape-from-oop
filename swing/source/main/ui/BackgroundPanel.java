package ui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

/**
 * BackgroundPanel - Panel hiển thị hình nền
 */
public class BackgroundPanel extends JPanel {
    
    private Image backgroundImage;
    
    public BackgroundPanel(String imagePath) {
        setLayout(null);
        loadBackground(imagePath);
    }
    
    /**
     * Load hình nền từ đường dẫn
     */
    private void loadBackground(String path) {
        File file = new File(path);
        if (file.exists()) {
            backgroundImage = new ImageIcon(file.getAbsolutePath()).getImage();
            System.out.println("Background loaded: " + path);
        } else {
            System.err.println("Background not found: " + path);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
