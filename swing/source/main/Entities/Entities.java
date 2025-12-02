package Entities;

import graphics.Renderable;

public class Entities implements Renderable  {
    @Override
    public void render(java.awt.Graphics2D g2) {
        // Implementation here
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public int getLayer() {
        return 0;
    }
    
}
