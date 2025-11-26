package components.items;

/**
 * Door - Example of UsableItem
 * Can be opened/closed like Chest but with different behavior
 */
public class Door extends UsableItem {
    private boolean isLocked;
    
    public Door(int x, int y) {
        super(x, y, "door");
        this.isLocked = false;
    }
    
    @Override
    protected String getDefaultState() {
        return "closed";
    }
    
    @Override
    public void interact() {
        if (isLocked) {
            System.out.println("Door is locked!");
            return;
        }
        
        if (state.equals("closed")) {
            open();
        } else {
            close();
        }
    }
    
    public void open() {
        if (!isLocked && state.equals("closed")) {
            setState("open");
        }
    }
    
    public void close() {
        if (state.equals("open")) {
            setState("closed");
        }
    }
    
    public void lock() {
        if (state.equals("closed")) {
            isLocked = true;
        }
    }
    
    public void unlock() {
        isLocked = false;
    }
    
    public boolean isLocked() {
        return isLocked;
    }
}
