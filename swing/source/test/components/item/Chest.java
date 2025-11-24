package models.entities;

/**
 * Chest - Interactive object extends Character
 * Không phải enemy hay player, nhưng cùng hệ thống rendering
 */
public class Chest extends Item {
    
    public Chest(int x, int y) {
        super(x, y, "chest");
    }
    
    @Override
    protected String getDefaultState() {
        return "idle";
    }
    
    public void open() {
        if (state.equals("idle")) {
            setState("open");
        }
    }
    
    public void close() {
        if (state.equals("open")) {
            setState("idle");
        }
    }
    
    public boolean isOpen() {
        return state.equals("open");
    }
}
