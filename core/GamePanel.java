package core;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private Game game;
    private final int TARGET_FPS = 60;
    private boolean running = false;

    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
        
        this.addKeyListener(game.getKeyInput());
        this.addMouseListener(game.getMouseInput());
        this.addMouseMotionListener(game.getMouseInput());
        this.setFocusable(true);
        
        startGameLoop();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1024, 512);
        setPreferredSize(size);
    }

    private void startGameLoop() {
        running = true;
        Thread gameThread = new Thread(() -> {
            final double frameTime = 1_000_000_000.0 / TARGET_FPS;
            long previous = System.nanoTime();
            
            while (running) {
                long now = System.nanoTime();
                double delta = now - previous;
                
                if (delta >= frameTime) {
                    game.update(delta);
                    repaint();
                    previous = now;
                } else {
                    try {
                        long sleep = (long)((frameTime - delta) / 1000000);
                        if(sleep > 0) Thread.sleep(sleep);
                    } catch (Exception e) {}
                }
            }
        });
        gameThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g, getWidth(), getHeight());
    }
}