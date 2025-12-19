

import core.Game;
import core.GamePanel;
import javax.swing.JFrame;

class main {
    public static void main(String[] args) {
        Game game = new Game();

        GamePanel panel = new GamePanel(game);
        JFrame frame = new JFrame("Escape From OOP");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}