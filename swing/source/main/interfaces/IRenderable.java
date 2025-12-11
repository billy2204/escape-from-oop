package interfaces;

/**
 * Interface for objects that can be rendered on screen
 */
public interface IRenderable {
    void render(java.awt.Graphics2D g2);
    boolean isVisible();
    int getRenderLayer();
}
