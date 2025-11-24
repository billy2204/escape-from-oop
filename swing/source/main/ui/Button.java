package ui;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

/**
 * Custom Button component
 * Tuân theo Single Responsibility: chỉ quản lý hiển thị của button
 */
public class Button extends JButton {
    
    public Button(String text, int x, int y, int width, int height) {
        super(text);
        setBounds(x, y, width, height);
        applyDefaultStyle();
    }
    
    /**
     * Áp dụng style mặc định
     */
    private void applyDefaultStyle() {
        setFont(new Font("Arial", Font.BOLD, 14));
        setFocusPainted(false);
        setBackground(new Color(100, 149, 237));
        setForeground(Color.WHITE);
    }
}
