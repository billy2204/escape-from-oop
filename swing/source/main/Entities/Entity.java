package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import interfaces.*;
/**
 * Base class cho tất cả entities trong game
 */
public abstract class Entity implements IUpdatable, IRenderable, ICollidable {
    
    protected int x, y;
    protected int width, height;
    protected boolean visible = true;
    protected int renderLayer = 0;
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /** Cập nhật logic - override trong subclass */
    
    /** Vẽ entity - override trong subclass */
    
    // Getters & Setters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
