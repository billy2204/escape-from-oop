import ui.GameWindow;
import javax.swing.SwingUtilities;

public class Application {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    //Tạo và hiển thị giao diện game
    private static void createAndShowGUI() {
        GameWindow gameWindow = new GameWindow(1024,512);
        gameWindow.setVisible(true);
    }
}
