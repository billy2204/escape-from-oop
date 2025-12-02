package Entities;

public abstract class Entities  {
    
    private int x; // coordinate x
    private int y; // coordinate y
    private final String type;
    protected String state; // Trạng thái hiện tại (idle, open, etc.)

    public Entities(String type, int x, int y) { // new Entities("type", x, y)
        this.x = x;
        this.y = y;
        this.type = type;
        this.state = "idle";
    }
    public Entities(String type, String state, int x, int y) { // new Entities("type", " run", x ,y ),
        this.x = x;
        this.y = y;
        this.type = type;
        this.state = state;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public String getType() { return type; }
    public String getState() { return state; }
    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setState(String state) { this.state = state; }

}
