package graphics;

/**
 * AnimationFactory - Tạo AnimationController cho các entity
 * Single Responsibility: Chỉ tạo và config animation
 */
public class AnimationFactory {
    
    private static final String ITEMS = "resources/items/";
    private static final String CHARS = "resources/characters/";
    
    // ===== CHEST =====
    public static AnimationController createChestAnimator(String state) {
        AnimationController a = new AnimationController(10);
        switch (state) {
            case "idle" -> { a.loadFrames(ITEMS + "chest/idle"); a.setLoop(true); }
            case "open" -> { a.loadFrames(ITEMS + "chest/open"); a.setLoop(false); }
        }
        return a;
    }
    
    // ===== PLAYER =====
    public static AnimationController createPlayerAnimator(String state) {
        AnimationController a = new AnimationController(8);
        String path = CHARS + "shin/";
        switch (state) {
            case "idle"       -> { a.loadFrames(path + "stand"); a.setLoop(true); }
            case "walk_left"  -> { a.loadFrames(path + "walk_left"); a.setLoop(true); }
            case "walk_right" -> { a.loadFrames(path + "walk_right"); a.setLoop(true); }
        }
        return a;
    }
    
    // ===== DOOR =====
    public static AnimationController createDoorAnimator(String state) {
        AnimationController a = new AnimationController(10);
        switch (state) {
            case "idle" -> { a.loadFrames(ITEMS + "door/idle"); a.setLoop(false); }
            case "open" -> { a.loadFrames(ITEMS + "door/open"); a.setLoop(false); }
        }
        return a;
    }
    
    // ===== ENEMY =====
    public static AnimationController createEnemyAnimator(String state) {
        AnimationController a = new AnimationController(10);
        if ("idle".equals(state)) {
            a.loadFrames(CHARS + "enemy/idle");
            a.setLoop(true);
        }
        return a;
    }

    // ===== COIN =====
    public static AnimationController createCoinAnimator(String state) {
        AnimationController a = new AnimationController(10);
        if ("idle".equals(state) || "spin".equals(state)) {
            a.loadFrames(ITEMS + "coin");
            a.setLoop(true);
        }
        return a;
    }
}
