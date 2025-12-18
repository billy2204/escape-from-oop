package graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameOverOverlay {

    public void draw(Graphics g, int screenWidth, int screenHeight) {
        // 1. Vẽ nền đen mờ (Che đi màn hình chơi game)
        // new Color(0, 0, 0, 200) -> Màu đen, độ trong suốt 200 (max 255)
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, screenWidth, screenHeight);

        // 2. Vẽ chữ "GAME OVER"
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "GAME OVER";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (screenWidth - textWidth) / 2, screenHeight / 2 - 50);

        // 3. Vẽ hướng dẫn "Press Enter"
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String text2 = "Press R to Restart";
        int textWidth2 = g.getFontMetrics().stringWidth(text2);
        g.drawString(text2, (screenWidth - textWidth2) / 2, screenHeight / 2 + 20);
    }
}