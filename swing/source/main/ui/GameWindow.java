package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;


public class GameWindow extends JFrame {
    private static final int DEFAULT_WIDTH = 1024;
    private static final int DEFAULT_HEIGHT = 512;
    private int windowWidth;
    private int windowHeight;
    private BackgroundPanel menuPanel;
    private GamePanel gamePanel;
    
    public GameWindow() {
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public GameWindow(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;
        initializeWindow();
        initializeComponents();
    }
    


    /**
     * Khởi tạo cấu hình cơ bản của cửa sổ
     */
    private void initializeWindow() {
        setTitle("Escape from OOP");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Tạo menu panel (màn hình chính)
        menuPanel = new BackgroundPanel("resources/backGround.png");
        menuPanel.setPreferredSize(new java.awt.Dimension(windowWidth, windowHeight));
        
        // Tạo game panel (màn hình chơi game)
        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new java.awt.Dimension(windowWidth, windowHeight));
        
        // Bắt đầu với menu panel
        setContentPane(menuPanel);
        pack(); // Size window to content so content area matches preferred sizes
        setLocationRelativeTo(null); // Center window
    }
    
    /**
     * Khởi tạo các component trong cửa sổ
     */
    private void initializeComponents() {
        // Label hiển thị tọa độ chuột
        JLabel coordLabel = new JLabel("X: 0, Y: 0");
        coordLabel.setBounds(10, 10, 150, 20);
        coordLabel.setForeground(Color.RED);
        coordLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
        menuPanel.add(coordLabel);
        
        // Lắng nghe di chuyển chuột để hiển thị tọa độ
        menuPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                coordLabel.setText("X: " + e.getX() + ", Y: " + e.getY());
            }
        });
        
        // Tính toán vị trí căn giữa cho button
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (windowWidth - buttonWidth) / 2;
        int buttonY = (windowHeight - buttonHeight) / 2;
        
        Button startButton = new Button("Start", buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        menuPanel.add(startButton);
    }
    
    /**
     * Chuyển sang màn hình game
     */
    public void startGame() {
        setContentPane(gamePanel);
        pack(); // Re-pack so the window fits the game panel exactly
        gamePanel.requestFocusInWindow();
        revalidate();
        repaint();
    }
}
