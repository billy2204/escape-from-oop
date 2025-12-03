package interfaces;

/**
 * Interface for objects that can be animated
 */
public interface IAnimatable {
    void updateAnimation();
    String getCurrentState();
    void setState(String state);
}
