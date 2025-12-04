package interfaces;

/**
 * Interface for input handling
 */
public interface IInputHandler {
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingLeft();
    boolean isRequestingRight();
    boolean isReleased();
    boolean changeView();
    boolean isEscapePressed();
}
