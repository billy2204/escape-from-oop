// === AnimationController.java ===
// Single Responsibility: Chỉ quản lý animation frames
package graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class AnimationController {
    private Map<String, BufferedImage[]> animations; // state -> frames
    private String currentState;
    private int currentFrame = 0;
    private int animationTick = 0;
    private int animationSpeed = 10;
    
    public AnimationController() {
        animations = new HashMap<>();
    }
    
    // Thêm animation mới mà KHÔNG cần sửa code cũ (OCP)
    public void addAnimation(String state, BufferedImage[] frames) {
        animations.put(state, frames);
    }
    
    public void setState(String state) {
        if (!state.equals(currentState)) {
            currentState = state;
            currentFrame = 0;
        }
    }
    
    public void update() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            currentFrame++;
            BufferedImage[] frames = animations.get(currentState);
            if (frames != null && currentFrame >= frames.length) {
                currentFrame = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame() {
        BufferedImage[] frames = animations.get(currentState);
        if (frames != null && frames.length > 0) {
            return frames[currentFrame % frames.length];
        }
        return null;
    }
}
