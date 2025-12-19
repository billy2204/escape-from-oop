package graphics.animation;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationManager {

    private static class AnimState {
        List<BufferedImage> frames;
        long speedMs;
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


    public void addAction(String actionName, String path, long speedMs, boolean isLoop) {
        List<BufferedImage> frames = ResourceManager.loadFrames(path);
        if (!frames.isEmpty()) {
            states.put(actionName, new AnimState(frames, speedMs, isLoop));
        }
    }
    public void update(long deltaTime) {
        if (currentState == null) return;
        if (finished && !currentState.loop) return;

        timer += deltaTime;

        if (timer >= currentState.speedMs) {
            timer = 0; // Reset đồng hồ 
            frameIndex++;
            
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

    public void play(String action) {
        if (action.equals(currentActionName)) {
            if (!finished) return;


            if (currentState != null && !currentState.loop) return;
        }

        if (states.containsKey(action)) {
            this.currentActionName = action;
            this.currentState = states.get(action);
            this.frameIndex = 0;
            this.timer = 0;
            this.finished = false;
        } else {
        }
    }
    public BufferedImage getCurrentFrame() {
        if (currentState == null || currentState.frames.isEmpty()) return null;
        return currentState.frames.get(frameIndex);
    }
    
    public boolean isFinished() { return finished; }
    public String getCurrentAction() { return currentActionName; }

    public void clear() {
        if (states != null) states.clear();
        currentState = null;
        currentActionName = null;
        frameIndex = 0;
        timer = 0;
        finished = false;
    }
}