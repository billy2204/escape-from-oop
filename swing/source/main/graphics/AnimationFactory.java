package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Arrays;
/**
 * AnimationFactory - Tạo AnimationController cho các entity
 * Single Responsibility: Chỉ lo việc tạo và config animation
 */
public class AnimationFactory {
    
    private static final String ITEMS_PATH = "resources/items/";
    private static final String CHARACTERS_PATH = "resources/characters/";
    
    /**
     * Tạo animator cho Chest
     */
    public static AnimationController createChestAnimator(String state) {
        AnimationController animator = new AnimationController();
        if (state.equals("idle")) {
            animator.loadFrames(ITEMS_PATH + "chest/idle");
        } else if (state.equals("open")) {
            animator.loadFrames(ITEMS_PATH + "chest/open");
        }
        return animator;
    }
    
    /**
     * Tạo animator cho Door
     */
    public static AnimationController createDoorAnimator() {
        AnimationController animator = new AnimationController();
        
        
        return animator;
    }
    
    /**
     * Tạo animator cho Player
     */
    public static AnimationController createPlayerAnimator() {
        AnimationController animator = new AnimationController();
        
        
        return animator;
    }
    
    /**
     * Tạo animator cho Enemy
     */
    public static AnimationController createEnemyAnimator() {
        AnimationController animator = new AnimationController();

        return animator;
    }
    
    /**
     * Load frames từ folder
     */
    private static BufferedImage[] loadFrames(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Folder not found: " + folderPath);
            return new BufferedImage[0];
        }
        
        File[] files = folder.listFiles((dir, name) -> 
            name.endsWith(".png") || name.endsWith(".jpg"));
        
        if (files == null || files.length == 0) {
            return new BufferedImage[0];
        }
        
        Arrays.sort(files);
        BufferedImage[] frames = new BufferedImage[files.length];
        
        for (int i = 0; i < files.length; i++) {
            try {
                frames[i] = ImageIO.read(files[i]);
            } catch (Exception e) {
                System.err.println("Cannot load: " + files[i]);
            }
        }
        
        return frames;
    }
}
