package input;

import input.IInputHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input
 */
public class KeyboardInput implements KeyListener, IInputHandler {
    
    private boolean[] keys = new boolean[256];
    private boolean[] consumed = new boolean[256];
    
    // WASD keys
    private static final int KEY_UP = KeyEvent.VK_W;
    private static final int KEY_DOWN = KeyEvent.VK_S;
    private static final int KEY_LEFT = KeyEvent.VK_A;
    private static final int KEY_RIGHT = KeyEvent.VK_D;
    private static final int KEY_ESCAPE = KeyEvent.VK_ESCAPE;
    private static final int KEY_CHANGE_VIEW = KeyEvent.VK_V;
    private static final int KEY_SPACE = KeyEvent.VK_SPACE;
    private static final int KEY_TOGGLE_WALLS = KeyEvent.VK_T;
    private static final int KEY_ENTER = KeyEvent.VK_ENTER;

    // Arrow keys as alternative
    private static final int KEY_UP_ALT = KeyEvent.VK_UP;
    private static final int KEY_DOWN_ALT = KeyEvent.VK_DOWN;
    private static final int KEY_LEFT_ALT = KeyEvent.VK_LEFT;
    private static final int KEY_RIGHT_ALT = KeyEvent.VK_RIGHT;
    public KeyboardInput() {
        // Initialize all keys to not pressed
        //set key event ready to use

    }
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
            // reset consumed state on release so a new press can be detected
            consumed[keyCode] = false;
        }
    }
    
    @Override
    public boolean isUp() {
        return keys[KEY_UP] || keys[KEY_UP_ALT];
    }
    
    @Override
    public boolean isDown() {
        return keys[KEY_DOWN] || keys[KEY_DOWN_ALT];
    }
    
    @Override
    public boolean isLeft() {
        return keys[KEY_LEFT] || keys[KEY_LEFT_ALT];
    }
    
    @Override
    public boolean isRight() {
        return keys[KEY_RIGHT] || keys[KEY_RIGHT_ALT];
    }
    

    
    @Override
    public boolean isReleased() {
        return !isUp() && !isDown() && 
               !isLeft() && !isRight();
    }
    @Override
    public boolean isEscapePressed() {
        return keys[KEY_ESCAPE];
    }
    
    @Override
    public boolean changeView() {
        return keys[KEY_CHANGE_VIEW];
    }

    public boolean isStart() {
        return keys[KEY_SPACE];
    }
    public boolean isReStart() {
        return keys[KeyEvent.VK_R];
    }
    public boolean isEnterPressed() {
        if (keys[KEY_ENTER] && !consumed[KEY_ENTER]) {
            consumed[KEY_ENTER] = true;
            return true;
        }
        return false;
    }
    /**
     * Return true if any key is currently pressed.
     */
    public boolean isPressed(){
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) return true;
        }
        return false;
    }
    // Return true once per key press (edge-detect behavior) for toggle keys
    public boolean isToggleWallsPressed() {
        if (keys[KEY_TOGGLE_WALLS] && !consumed[KEY_TOGGLE_WALLS]) {
            consumed[KEY_TOGGLE_WALLS] = true;
            return true;
        }
        return false;
    }
}
