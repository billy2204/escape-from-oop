package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import interfaces.*;

/**
 * Base class cho tất cả entities trong game
 * Abstract class - subclass phải implement các abstract methods
 */
public abstract class Entity implements IUpdateLogic, IRenderable, IUpdateAnimation {
    
    protected int x, y;
    protected int width, height;
    protected boolean visible = true;
    protected int renderLayer = 0;
    protected String state;
    
    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = "idle";
    }
    
    // === IUpdateLogic ===
    @Override
    public abstract void updateLogic();
    
    // === IUpdateAnimation ===
    @Override
    public abstract void updateAnimation();
    
    // === IRenderable ===
    @Override
    public abstract void render(Graphics2D g2);
    
    @Override
    public boolean isVisible() {
        return visible;
    }
    
    @Override
    public int getRenderLayer() {
        return renderLayer;
    }
    
    // === Getters & Setters ===
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setRenderLayer(int layer) { this.renderLayer = layer; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}
