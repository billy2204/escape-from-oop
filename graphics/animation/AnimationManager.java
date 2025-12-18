package graphics.animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {

    // Class nội bộ lưu cấu hình
    private static class AnimState {
        List<BufferedImage> frames;
        long speedMs;   // milliseconds per frame
        boolean loop;
        
        AnimState(List<BufferedImage> frames, long speedMs, boolean loop) {
            this.frames = frames;
            this.speedMs = speedMs;
            this.loop = loop;
        }
    }

    private Map<String, AnimState> states = new HashMap<>();
    
    private AnimState currentState;
    private String currentActionName;
    
    private int frameIndex = 0;
    private long timer = 0;      // elapsed time accumulator (ms)
    private boolean finished = false;

    /**
     * CONFIG: speedMs là thời gian hiển thị của 1 frame
     * Ví dụ: 100 -> Mỗi ảnh hiện trong 100ms (0.1 giây)
     */
    public void addAction(String actionName, String path, long speedMs, boolean isLoop) {
        List<BufferedImage> frames = ResourceManager.loadFrames(path);
        if (!frames.isEmpty()) {
            states.put(actionName, new AnimState(frames, speedMs, isLoop));
        }
    }

    /**
     * UPDATE: Nhận vào thời gian trôi qua (deltaTime)
     */
    public void update(long deltaTime) {
        if (currentState == null) return;
        if (finished && !currentState.loop) return;

        // Cộng dồn thời gian trôi qua từ frame trước
        timer += deltaTime;

        // Nếu thời gian tích lũy vượt quá thời gian quy định của 1 frame
        if (timer >= currentState.speedMs) {
            timer = 0; // Reset đồng hồ (hoặc timer -= speedMs để cực chuẩn xác)
            frameIndex++;
            
            // Logic vòng lặp
            if (frameIndex >= currentState.frames.size()) {
                if (currentState.loop) {
                    frameIndex = 0;
                } else {
                    frameIndex = currentState.frames.size() - 1;
                    finished = true;
                }
            }
        }
    }

    public boolean hasAction(String action) {
        return states.containsKey(action);
    }

    // --- Các hàm hỗ trợ giữ nguyên ---
    public void play(String action) {
        // 1. Kiểm tra xem có đúng là action đang chạy không
        if (action.equals(currentActionName)) {
            // Nếu animation này ĐANG chạy -> Bỏ qua
            if (!finished) return;

            // If non-looping animation finished, don't reset it
            if (currentState != null && !currentState.loop) return;
        }

        // 2. Logic khởi tạo animation mới (hoặc chạy lại animation loop)
        if (states.containsKey(action)) {
            this.currentActionName = action;
            this.currentState = states.get(action);
            this.frameIndex = 0;
            this.timer = 0;
            this.finished = false;
        } else {
            // Log lỗi để bạn biết entity nào thiếu config (như lỗi Button bạn gặp)
            System.out.println("No animation config for: " + action);
        }
    }
    public BufferedImage getCurrentFrame() {
        if (currentState == null || currentState.frames.isEmpty()) return null;
        return currentState.frames.get(frameIndex);
    }
    
    public boolean isFinished() { return finished; }
    public String getCurrentAction() { return currentActionName; }

    /**
     * Clear animation references to help GC when an owning entity is disposed.
     * Note: this does not evict shared image cache in ResourceManager.
     */
    public void clear() {
        if (states != null) states.clear();
        currentState = null;
        currentActionName = null;
        frameIndex = 0;
        timer = 0;
        finished = false;
    }
}