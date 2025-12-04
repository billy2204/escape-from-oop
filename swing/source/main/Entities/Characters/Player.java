package entities.characters;
import java.awt.Graphics2D;
import java.awt.Color;
import entities.*;
/**
 * Player character controlled by user input
 */
public class Player extends Entity {
    private int speed;
    
    public Player(int x, int y) {
        super(x, y, 32, 32);
        this.renderLayer = 1; // Player renders above most entities
        this.speed = 4;
    }

    @Override
    public void render(Graphics2D g2) {
        if (!visible) return;
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, width, height);
    }
    @Override
    public void updateLogic() {
        // Logic update cho Player (nếu cần)

    }
    @Override
    public void updateAnimation() {
        // Cập nhật animation hiện tại  
    }

    public void moveUp() {
        y -= speed;
        System.out.println("Player moving up");
    }
    public void moveDown() {
        y += speed;
        System.out.println("Player moving down");
    }
    public void moveLeft() {
        x -= speed;
        System.out.println("Player moving left");
    }
    public void moveRight() {          
        x += speed;
        System.out.println("Player moving right");
    }
}
