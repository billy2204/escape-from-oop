

import core.Game;
import core.GamePanel;
import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        System.out.println("Game Starting...");
        Game game = new Game();

        GamePanel panel = new GamePanel(game);
        JFrame frame = new JFrame("Escape From OOP");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Quan trọng: Panel phải có focus để nhận phím
        panel.requestFocusInWindow();
    }
}