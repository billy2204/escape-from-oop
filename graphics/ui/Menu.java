package graphics.ui;

import input.*;
import utils.GameState;

import java.io.File;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

public class Menu {
    private IMenuInput keyInput;
    private IMouseInput mouseInput;
    private Image backgroundImage;
    private Image startButtonImage;
    private int startX, startY, startW, startH;
    private boolean prevLeftPressed = false;
    private int lastWidth = 0;

    public Menu(IMenuInput keyInput, IMouseInput mouseInput) {
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
        }

        if (mouseInput != null && startButtonImage != null && lastWidth > 0 && startW > 0 && startH > 0) {
            boolean pressed = mouseInput.isLeftPressed();
            if (pressed && !prevLeftPressed) {
                int mx = mouseInput.getX();
                int my = mouseInput.getY();
                    if (mx >= startX && mx <= startX + startW && my >= startY && my <= startY + startH) {
                    GameState.state = GameState.PLAYING;
                }
            }
            prevLeftPressed = pressed;
        }
    }

    public void draw(Graphics g, int width, int height) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            g.setColor(new Color(30, 30, 30));
            g.fillRect(0, 0, width, height);
        }

        
        if (startButtonImage != null) {
            int imgW = startButtonImage.getWidth(null);
            int imgH = startButtonImage.getHeight(null);
            if (imgW > 0 && imgH > 0) {
                startW = imgW;
                startH = imgH;
                startX = 884;
                startY = 466; 

                g.drawImage(startButtonImage, startX, startY, startW, startH, null);

                lastWidth = width;
            }
        } else {
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
            }
        } catch (Exception ignored) { }
    }

    private void loadStartButton() {
        String path = "resources/startButton.png";   
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            try {
                startButtonImage = new ImageIcon(f.getAbsolutePath()).getImage();
            } catch (Exception e) {
                startButtonImage = null;
            }
        }
    }
}