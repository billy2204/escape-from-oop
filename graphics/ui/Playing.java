
package graphics.ui;

import entities.Entity;
import entities.characters.*;
import entities.items.*;
import input.KeyboardInput;
import input.MouseInput;
import core.*;
import utils.GameState;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playing {

    private KeyboardInput input;
    private MouseInput mouseInput;
    private MapManager mapManager;
    private CollisionManager collisionManager;
    private PhysicsSystem physicsSystem;
    private int countCoins = 0;
    public boolean nanakoFollow = false;
    public boolean isOpenChest = false;

    private Player player;
    private List<Entity> entities; 
    private List<Entity> entitiesToAdd = new ArrayList<>(); 
    private List<Entity> entitiesToRemove = new ArrayList<>(); // queue removals to avoid concurrent modification
    
    private Image mapImage;
    
    private GameOverOverlay gameOverOverlay;
    private GameWinOverlay gameWinOverlay;
    private String gameOver = "none"; // "none", "win", "lose"

    public Playing(KeyboardInput input, MouseInput mouseInput) {
        this.input = input;
        this.mouseInput = mouseInput;
        this.gameOverOverlay = new GameOverOverlay();
        this.gameWinOverlay = new GameWinOverlay();
        
        initClasses();
        loadMapImage();
    }

    private void initClasses() {
        mapManager = new MapManager();
        collisionManager = new CollisionManager(mapManager);
        physicsSystem = new PhysicsSystem(collisionManager);

        entities = new ArrayList<>();
        player = new Player(5, 100, input);

        entities.add(new Chest(400, 300)); 
        entities.add(new Ghost(606, 17)); 
        entities.add(new Hima(119, 3));
        entities.add(new Hima(119, 221));
        entities.add(new Hima(942, 353));
        entities.add(new Hima(887, 452));
        entities.add(new Coin(129, 127));
        entities.add(new Coin(413, 38));
        entities.add(new Coin(613, 334));
        entities.add(new Coin(750, 190));
        entities.add(new Coin(850, 403));//850
        entities.add(new Coin(128, 437));
        entities.add(new Door(391, 103));
        entities.add(new Button(240, 235));
        entities.add(new Nanako(842, 67));
        gameOver = "none";
        entitiesToAdd.clear();
    }
    
    public void resetGame() {
        initClasses();
        setCountCoins(0);
    }
/**
 * Remove an entity from the world. Safe to call during update (it will be queued).
 */
    public void removeEntity(Entity e) {
        if (e == null) return;
        e.dispose();
        e.setActive(false);
        entitiesToRemove.add(e);
    }
    public void spawnEntity(Entity e) {
        entitiesToAdd.add(e);
    }

    public void update(double deltaNs) {
        if (gameOver == "lose") {
            if (input.isReStart()) {
                setCountCoins(0);
                resetGame();
            }
            return;
        }
        else if (gameOver == "win") {
            if (input.isEnterPressed()) {
                GameState.state = GameState.MENU;
                gameOver = "none";
                resetGame();
            }
            return;
        }

        long dt = (long) (deltaNs / 1_000_000);

        if (input != null && input.isEscapePressed()) {
            GameState.state = GameState.MENU;
            return;
        }

        if (input != null && input.isToggleWallsPressed()) {
            System.out.println("Playing: Toggle walls key pressed");
            mapManager.toggleShowWalls();
        }

        player.update(dt);
        for (Entity e : entities) {
            if (e.isActive()) {
                if (e instanceof Enemy) {
                    ((Enemy) e).setAIContext(player, entities);
                } else if (e instanceof NonEnemy) {
                    ((NonEnemy) e).setAIContext(player, entities);
                }
            }
            e.update(dt);
        }

        physicsSystem.update(player, dt);
        if (player.isMoving()) {
            for (Entity e : entities) {
                if (e.isActive()) physicsSystem.update(e, dt);
        }
        }
        

        if (!entitiesToAdd.isEmpty()) {
            entities.addAll(entitiesToAdd);
            entitiesToAdd.clear();
        }

        if (!entitiesToRemove.isEmpty()) {
            entities.removeAll(entitiesToRemove);
            entitiesToRemove.clear();
        }

        checkGameplayCollisions();
        if (input == null) {
            return;
        }

        if (input.isStart()) {
            GameState.state = GameState.MENU;
            System.out.println("Switching to Playing (Keyboard)...");
        }
    }

    private void checkGameplayCollisions() {
        if (gameOver == "lose") return;

        Entity hitEntity = collisionManager.checkEntityCollision(player, entities);
        
        if (hitEntity != null) {
            if (hitEntity instanceof Enemy) {
                System.out.println("PLAYER DIED!");
                gameOver = "lose";
            }
            else if (hitEntity instanceof Chest) {
                Random rand = new Random();
                int random = (rand.nextInt(2) + 1 );
                Chest chest = (Chest) hitEntity;
                if (!chest.isCollected()) {
                    chest.collect();
                    isOpenChest = true;
                    if (random == 1)
                        spawnEntity(new Ghost(chest.getTransform().x + 40, chest.getTransform().y));
                    else {
                        chest.setPrice();
                    }
                }
            } 
            else if (hitEntity instanceof Coin) {
                Coin coin = (Coin) hitEntity;
                if (!coin.isEarned()) {
                    coin.earn();
                    increaseCoinCount();
                    removeEntity(coin);
                }
            }
    
            else if (hitEntity instanceof Button) {
                Button button = (Button) hitEntity;
                
                if (!button.isPress()) {
                    System.out.println("Button Pressed!");
                    button.press();
                    mapManager.removeWallAt(388,103,453,146);
                    for (Entity e : entities) {
                        if (e instanceof Door) {
                            ((Door) e).open();
                        }
                    }
                }
            }
        }
        
        for (Entity e : entities) {
                        if (e instanceof Nanako && ((Nanako) e).isFollowPlayer()) {
                            nanakoFollow = true;
                        }
        }               
        if (collisionManager.checkExit(player.getCollider().getBounds()) && countCoins >= 6 && nanakoFollow && isOpenChest) {
            System.out.println("VICTORY!"); 
            gameOver = "win"; // Bật cờ chiến thắng
        }
    }

    public void removeEntityAtPosition(float x, float y, String typeName) {
        for (Entity e : entities) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(typeName)) {
                if (Math.abs(e.getTransform().x - x) < 0.5f && Math.abs(e.getTransform().y - y) < 0.5f) {
                    removeEntity(e);
                    break;
                }
            }
        }
    }
    public void draw(Graphics g, int width, int height) {
        if (mapImage != null) g.drawImage(mapImage, 0, 0, width, height, null);
        else { g.setColor(new Color(30, 30, 30)); g.fillRect(0, 0, width, height); }

        java.util.List<Entity> renderList = new ArrayList<>();
        renderList.addAll(entities);
        if (player != null) renderList.add(player);
        renderList.sort((a, b) -> {
            float ay = a.getTransform().y + a.getTransform().height;
            float by = b.getTransform().y + b.getTransform().height;
            return Float.compare(ay, by);
        });
        for (Entity e : renderList) e.render((java.awt.Graphics2D) g);

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Coin: "+ getCountCoins() + "/6" , 20, 30);

        mapManager.draw((java.awt.Graphics2D) g);
        String wallsStatus = "Walls: " + (mapManager.isShowWalls() ? "ON" : "OFF") + "  (press T to toggle)";
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        int textW = g.getFontMetrics().stringWidth(wallsStatus);
        int pad = 6;
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(18, 34, textW + pad, 20);
        g.setColor(Color.WHITE);
        g.drawString(wallsStatus, 20, 50);

        if (mouseInput != null) {
            String mousePos = "Mouse: " + mouseInput.getX() + ", " + mouseInput.getY();
            int mw = g.getFontMetrics().stringWidth(mousePos);
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(18, 54, mw + pad, 20);
            g.setColor(Color.WHITE);
            g.drawString(mousePos, 20, 70);
        }

        if (gameOver == "lose") {
            gameOverOverlay.draw(g, width, height);
        }
        else if (gameOver == "win") {
            gameWinOverlay.draw(g, width, height);
        }
    }

    private void loadMapImage() {
        String path = "resources/map/map.png";
        try {
            File f = new File(path);
            if (f.exists()) mapImage = new ImageIcon(f.getAbsolutePath()).getImage();
        } catch (Exception ignored) { }
    }
    public int getCountCoins() {
        return countCoins;
    }
    public void increaseCoinCount() {
        this.countCoins = countCoins+1;
    }
    public void setCountCoins(int count) {
        this.countCoins = count;
    }
}