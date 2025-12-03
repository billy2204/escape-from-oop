package input;

import interfaces.IInputHandler;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input
 */
public class KeyboardInput implements KeyListener, IInputHandler {
    
    private boolean[] keys = new boolean[256];
    
    // WASD keys
    private static final int KEY_UP = KeyEvent.VK_W;
    private static final int KEY_DOWN = KeyEvent.VK_S;
    private static final int KEY_LEFT = KeyEvent.VK_A;
    private static final int KEY_RIGHT = KeyEvent.VK_D;
    private static final int KEY_ACTION = KeyEvent.VK_SPACE;
    private static final int KEY_ESCAPE = KeyEvent.VK_ESCAPE;
    
    // Arrow keys as alternative
    private static final int KEY_UP_ALT = KeyEvent.VK_UP;
    private static final int KEY_DOWN_ALT = KeyEvent.VK_DOWN;
    private static final int KEY_LEFT_ALT = KeyEvent.VK_LEFT;
    private static final int KEY_RIGHT_ALT = KeyEvent.VK_RIGHT;
    
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
        }
    }
    
    @Override
    public boolean isRequestingUp() {
        return keys[KEY_UP] || keys[KEY_UP_ALT];
    }
    
    @Override
    public boolean isRequestingDown() {
        return keys[KEY_DOWN] || keys[KEY_DOWN_ALT];
    }
    
    @Override
    public boolean isRequestingLeft() {
        return keys[KEY_LEFT] || keys[KEY_LEFT_ALT];
    }
    
    @Override
    public boolean isRequestingRight() {
        return keys[KEY_RIGHT] || keys[KEY_RIGHT_ALT];
    }
    
    @Override
    public boolean isRequestingAction() {
        return keys[KEY_ACTION];
    }
    
    @Override
    public boolean isReleased() {
        return !isRequestingUp() && !isRequestingDown() && 
               !isRequestingLeft() && !isRequestingRight();
    }
    
    public boolean isEscapePressed() {
        return keys[KEY_ESCAPE];
    }
    
    public boolean isKeyPressed(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            return keys[keyCode];
        }
        return false;
    }
}
