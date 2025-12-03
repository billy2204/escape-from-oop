package interfaces;

/**
 * Interface for objects that can collide with others
 */
public interface ICollidable {
    java.awt.Rectangle getBounds();
    void onCollision(ICollidable other);
}
