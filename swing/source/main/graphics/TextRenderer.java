package graphics;

import java.awt.*;

/**
 * Class render text
 */
public class TextRenderer implements Renderable {
    
    private String text;
    private double x, y;
    private Font font;
    private Color color;
    private boolean visible = true;
    private int layer = 2; // UI layer
    
    public TextRenderer(String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.font = new Font("Arial", Font.PLAIN, 16);
        this.color = Color.WHITE;
    }
    
    @Override
    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, (int)x, (int)y);
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setFont(Font font) {
        this.font = font;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public void setLayer(int layer) {
        this.layer = layer;
    }
    
    @Override
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @Override
    public int getLayer() {
        return layer;
    }
}