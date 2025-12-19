package core;

import graphics.ui.Menu;
import graphics.ui.Playing;
import utils.GameState;
import java.awt.Graphics;
import input.*;

public class Game {
    
    private Menu menu;
    private Playing playing;
    private KeyboardInput keyInput;
    private MouseInput mouseInput;

    public Game() {
        initClasses();
    }

    private void initClasses() {
        keyInput = new input.KeyboardInput();
        mouseInput = new input.MouseInput();
        menu = new Menu(keyInput, mouseInput);
        playing = new Playing(keyInput, mouseInput, keyInput);
    }

    public KeyboardInput getKeyInput() { return keyInput; }
    public MouseInput getMouseInput() { return mouseInput; }

    public void update(double delta) {
        switch (GameState.state) {
            case MENU:
                menu.update(); // Menu
                break;
            case PLAYING:
                playing.update(delta); 
                break;
            case QUIT:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g, int width, int height) {
        switch (GameState.state) {
            case MENU:
                if (menu != null) menu.draw(g, width, height);
                break;
            case PLAYING:
                if (playing != null) playing.draw(g, width, height);
                break;
            case QUIT:
                break;
        }

    }
}