package graphics.animation;

public class AnimationRegistry {

    public static void load(AnimationManager manager, String type) {
        switch (type) {
            // ================= PLAYER =================
            case "player":
                manager.addAction("idle", "resources/characters/shin/idle", 200, true);
                manager.addAction("run", "resources/characters/shin/walk_right", 100, true);
                break;
            // ================= GHOST =================
            case "ghost":
                manager.addAction("idle", "resources/characters/ghost/idle", 150, true);
                manager.addAction("run", "resources/characters/ghost/walk_right", 100, true);
                break;

            // ================= HIMA =================
            case "hima":
                manager.addAction("idle", "resources/characters/hima/idle", 150, true);
                manager.addAction("run", "resources/characters/hima/walk_right", 80, true);
                break;
            // ================= CHEST =================
            case "chest":
                manager.addAction("idle", "resources/items/chest/idle", 100, true);
                manager.addAction("open", "resources/items/chest/open", 100, false);
                manager.addAction("open_with_price", "resources/items/chest/open_with_price", 150, false);
                break;
                
            case "coin":
                manager.addAction("spin", "resources/items/coin", 100, true);
                manager.play("spin");
                break;
            case "door":
                manager.addAction("close", "resources/items/door/close", 100, false);
                manager.addAction("open", "resources/items/door/open", 100, false);
                break;
            case "button":
                manager.addAction("idle", "resources/items/button/idle", 100, false);
                manager.addAction("press", "resources/items/button/press", 100, false);
                break;
            case "nanako":
                manager.addAction("idle", "resources/characters/nanako/idle", 200, true);
                manager.addAction("run", "resources/characters/nanako/walk_right",200, true);
                break;
            default:
                System.err.println("No animation config for: " + type);
        }
    }
}