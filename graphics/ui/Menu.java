package graphics.ui;

import input.*;
import utils.GameState;

import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.net.URL;

public class Menu {
    private KeyboardInput keyInput;
    private MouseInput mouseInput;
    private Image backgroundImage;
    private Image startButtonImage;
    private int startX, startY, startW, startH;
    private boolean prevLeftPressed = false;
    private int lastWidth = 0, lastHeight = 0;

    public Menu(KeyboardInput keyInput, MouseInput mouseInput) {
        this.keyInput = keyInput;
        this.mouseInput = mouseInput;

        loadBackground();
        loadStartButton();
    }

    public void update() {
        if (keyInput == null) {
            return;
        }

        if (keyInput.isStart()) {
            GameState.state = GameState.PLAYING;
            System.out.println("Switching to Playing (Keyboard)...");
        }

        if (mouseInput != null && startButtonImage != null && lastWidth > 0 && startW > 0 && startH > 0) {
            boolean pressed = mouseInput.leftPressed;
            if (pressed && !prevLeftPressed) {
                int mx = mouseInput.x;
                int my = mouseInput.y;
                // Kiểm tra xem chuột có click vào vùng nút không
                if (mx >= startX && mx <= startX + startW && my >= startY && my <= startY + startH) {
                    GameState.state = GameState.PLAYING;
                    System.out.println("Switching to Playing (Mouse Click)...");
                }
            }
            prevLeftPressed = pressed;
        }
    }

    public void draw(Graphics g, int width, int height) {
        // 1. Vẽ Background
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            g.setColor(new Color(30, 30, 30));
            g.fillRect(0, 0, width, height);
        }

        // 2. Vẽ Nút Start
        if (startButtonImage != null) {
            // Lấy kích thước thực của ảnh
            int imgW = startButtonImage.getWidth(null);
            int imgH = startButtonImage.getHeight(null);

            // Chỉ vẽ nếu ảnh đã load xong (kích thước > 0)
            if (imgW > 0 && imgH > 0) {
                startW = imgW;
                startH = imgH;
                // Ví dụ: Căn giữa theo chiều ngang và đặt ở 70% chiều cao màn hình
                startX = 884;
                startY = 466; 

                // Vẽ ảnh tại vị trí đã tính
                g.drawImage(startButtonImage, startX, startY, startW, startH, null);

                // Lưu lại trạng thái để dùng cho việc bắt click chuột
                lastWidth = width;
                lastHeight = height;
            }
        } else {
            // Vẽ một hình chữ nhật màu đỏ để biết vị trí nút nếu chưa có ảnh
            
            g.setColor(Color.RED);
            startW = 200; startH = 80;
            startX = (width - startW) / 2;
            startY = (int) (height * 0.7);
            g.fillRect(startX, startY, startW, startH);
            g.setColor(Color.WHITE);
            g.drawString("Start Game (No Image)", startX + 30, startY + 45);
            
            lastWidth = 0;
        }
    }

    private void loadBackground() {
        String path = "resources/backGround.png";
        try {
            File f = new File(path);
            if (f.exists()) {
                backgroundImage = new ImageIcon(f.getAbsolutePath()).getImage();
                System.out.println("Loaded menu background: " + f.getAbsolutePath());
            }
        } catch (Exception ignored) { }
    }

    // debug: preload start button image
    private void loadStartButton() {
        String path = "resources/startButton.png";   
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            try {
                // Dùng ImageIcon để preload ảnh
                startButtonImage = new ImageIcon(f.getAbsolutePath()).getImage();
                    System.out.println("Load Start Button Image" + path);
            } catch (Exception e) {
                System.err.println("Load Start Button Failed: " + e.getMessage());
                startButtonImage = null;
            }
        }
    }
}