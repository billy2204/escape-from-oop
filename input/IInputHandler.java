package input;

public interface IInputHandler {

    boolean isUp();
    boolean isDown();
    boolean isLeft();
    boolean isRight();
    boolean isReleased();
    boolean changeView();
    boolean isEscapePressed();
    boolean isStart();
    boolean isReStart();
    boolean isEnterPressed();
    boolean isPressed();
    boolean isToggleWallsPressed();
    

}
