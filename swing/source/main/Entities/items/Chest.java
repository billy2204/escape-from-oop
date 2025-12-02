package components.items;

/**
 * Chest - Interactive object that can be opened/closed
 * Template example of UsableItem
 */
public class Chest extends UsableItem {
    
    public Chest(int x, int y) {
        super(x, y, "chest");
    }
    
    @Override
    protected String getDefaultState() {
        return "idle";
    }
    
    @Override
    public void interact() {
        if (isClosed()) {
            open();
        } else {
            close();
        }
    }
    
    public boolean isClosed() {
        return state.equals("idle");
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
}
