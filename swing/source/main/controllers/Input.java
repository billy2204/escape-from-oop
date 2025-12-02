package controllers;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input implements KeyListener {
    private boolean[] press ; 

    public Input() {
        press = new boolean[255];
    }

    public boolean isPressed(int keyCode) {
        return press[keyCode];  
    }
    public void keyPressed(KeyEvent e) {
        press[e.getKeyCode()] = true;
    }
    public void keyReleased(KeyEvent e) {
        press[e.getKeyCode()] = false;
       
    }
    public void keyTyped(KeyEvent e) {

    }
    public static void main(String[] args) {
    }

}