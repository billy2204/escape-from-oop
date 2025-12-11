package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import interfaces.IUpdateAnimation;

/**
 */
public class AnimationController implements IUpdateAnimation {
    
    private List<BufferedImage> frames;
    private int currentFrame;
    private int frameDelay;      // Số lần update trước khi đổi frame
    private int frameCounter;    // Đếm số lần update
    private boolean playing;
    private boolean loop;
    
    /**
     * Constructor với custom delay
     */
    public AnimationController(int frameDelay) {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
        this.frameDelay = frameDelay;
        this.frameCounter = 0;
        this.playing = true;
        this.loop = true;
    }
    
    /**
     * Constructor mặc định (delay = 10)
     */
    public AnimationController() {
        this(10);
    }
    
    /**
     * Load frames từ folder
     * @param folderPath Đường dẫn đến folder chứa frames (1.png, 2.png, ...)
     */
    public void loadFrames(String folderPath) {
        frames.clear();
        currentFrame = 0;
        
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Animation folder not found: " + folderPath);
            return;
        }
        
        // Load frames theo thứ tự 1.png, 2.png, 3.png, ...
        int i = 1;
        while (true) {
            File frameFile = new File(folder, i + ".png");
            if (!frameFile.exists()) break;
            
            try {
                BufferedImage frame = ImageIO.read(frameFile);
                frames.add(frame);
            } catch (IOException e) {
                System.err.println("Failed to load frame: " + frameFile.getPath());
            }
            i++;
        }
        
        System.out.println("Loaded " + frames.size() + " frames from " + folderPath);
    }
    
    /**
     * Update animation - gọi mỗi game tick
     */
    public void update() {
        updateAnimation();
    }
    
    /**
     * Update animation - gọi mỗi game tick
     */
    public void updateAnimation() {
        if (!playing || frames.isEmpty()) return;
        
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            
            if (loop) {
                currentFrame = (currentFrame + 1) % frames.size();
            } else {
                if (currentFrame < frames.size() - 1) {
                    currentFrame++;
                }
            }
        }
    }
    
    /**
     * Lấy frame hiện tại để vẽ
     */
    public BufferedImage getCurrentFrame() {
        if (frames.isEmpty()) return null;
        return frames.get(currentFrame);
    }
    
    /**
     * Reset về frame đầu tiên
     */
    public void reset() {
        currentFrame = 0;
        frameCounter = 0;
    }
    
    /**
     * Chạy animation từ đầu
     */
    public void play() {
        playing = true;
    }
    
    /**
     * Dừng animation
     */
    public void stop() {
        playing = false;
    }
    
    /**
     * Pause/Resume
     */
    public void pause() {
        playing = false;
    }
    
    public void resume() {
        playing = true;
    }
    
    /**
     * Kiểm tra animation đã kết thúc chưa (chỉ khi loop = false)
     */
    public boolean isFinished() {
        return !loop && currentFrame >= frames.size() - 1;
    }
    
    /**
     * Đặt frame cụ thể
     */
    public void setCurrentFrame(int frame) {
        if (frame >= 0 && frame < frames.size()) {
            this.currentFrame = frame;
        }
    }
    
    // Getters & Setters
    public void setLoop(boolean loop) { this.loop = loop; }
    public boolean isLoop() { return loop; }
    public void setFrameDelay(int delay) { this.frameDelay = delay; }
    public int getFrameDelay() { return frameDelay; }
    public int getFrameCount() { return frames.size(); }
    public int getCurrentFrameIndex() { return currentFrame; }
    public boolean isPlaying() { return playing; }
    public boolean hasFrames() { return !frames.isEmpty(); }
}
