package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class render sprite/hình ảnh
 * Single Responsibility: Chỉ lo việc vẽ sprite
 */
public class SpriteRenderer implements Renderable {
    
    private double x, y;
    private int width, height;
    private BufferedImage sprite;
    private boolean visible = true;
    private int layer = 1;
    
    // Flip sprite
    private boolean flipX = false;
    private boolean flipY = false;
    
    // Alpha (độ trong suốt)
    private float alpha = 1.0f;
    
    public SpriteRenderer(double x, double y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        if (sprite != null) {
            this.width = sprite.getWidth();
            this.height = sprite.getHeight();
        }
    }
    
    public SpriteRenderer(double x, double y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }
    
    @Override
    public void render(Graphics2D g2) {
        if (sprite == null) return;
        
        // Lưu lại transform cũ
        Composite oldComposite = g2.getComposite();
        
        // Set alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        
        // Vẽ sprite (có xử lý flip)
        int drawX = (int) x;
        int drawY = (int) y;
        int drawWidth = flipX ? -width : width;
        int drawHeight = flipY ? -height : height;
        
        if (flipX) drawX += width;
        if (flipY) drawY += height;
        
        g2.drawImage(sprite, drawX, drawY, drawWidth, drawHeight, null);
        
        // Khôi phục composite
        g2.setComposite(oldComposite);
    }
    
    // Getters và Setters
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }
    
    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }
    
    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }
    
    public void setAlpha(float alpha) {
        this.alpha = Math.max(0, Math.min(1, alpha));
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
