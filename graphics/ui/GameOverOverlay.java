package graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameOverOverlay {

    public void draw(Graphics g, int screenWidth, int screenHeight) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        String text = "GAME OVER";
        int textWidth = g.getFontMetrics().stringWidth(text);
        g.drawString(text, (screenWidth - textWidth) / 2, screenHeight / 2 - 50);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String text2 = "Press R to Restart";
        int textWidth2 = g.getFontMetrics().stringWidth(text2);
        g.drawString(text2, (screenWidth - textWidth2) / 2, screenHeight / 2 + 20);
    }
}