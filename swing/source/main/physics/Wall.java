    
package physics;
import java.awt.Color;
import java.awt.Graphics2D;


public class Wall {
    protected final int mapWidth = 1024;
    protected final int mapHeight = 512;
    private final byte WALL = 1;
    private final byte EMPTY = 0;
    private final byte GATE = 2;
    private final byte TRAP = 3;
    protected byte[][] map;
    public boolean isWall = true;
    private boolean CanRender = true;
    public Wall() {
        map = new byte[mapWidth][mapHeight];
        // Tự setup một số pixel tường để test (hiện màu đỏ khi new)
        setWall();
    }

    // Đặt tường tại vị trí (x, y)
    public void setWall() {

        // ========= Room 1 ========
        setBorder();
        setBlock(0,289,288,343);
        setBlock(288, 240, 304  , 397);
        setBlock(304, 240, 520, 262);
        setBlock(304,350,520,397);
        setBlock(520, 262,508,288);
        setBlock(508,331,520,350);
        setGate(508,288,520,331);




        // ========= Room 2 ========
        setBlock(289, 0, 303, 101);
        setBlock(543, 0, 557, 101);
        setBlock(289,101,557,145);
        //setGate(390,100, 453,145);
    

        // ========= Room 3 ========
        setBlock( 690, 0, 703, 202);
        setBlock( 690, 263, 1024, 326);
        setGate(690, 203, 703, 262);

        // ========= Decorators ========
        //995 444 1023 508
        setTrap(995, 444, 1023, 508);
    }

    public void setBorder(){
        for (int x = 0; x < mapWidth; x++) {
            map[x][0] = WALL; // Top border
            map[x][mapHeight - 1] = WALL; // Bottom border
        }
        for (int y = 0; y < mapHeight; y++) {
            map[0][y] = WALL; // Left border
            map[mapWidth - 1][y] = WALL; // Right border
        }
    }
    public void setBlock(int x1, int y1, int x2, int y2){
        int startX = Math.min(x1, x2);
        int endX   = Math.max(x1, x2);
        int startY = Math.min(y1, y2);
        int endY   = Math.max(y1, y2);
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                // Kiểm tra bounds (nếu cần) để tránh lỗi ArrayOutOfBounds
                if (i >= 0 && i < map.length && j >= 0 && j < map[0].length) {
                    map[i][j] = WALL;
                }
            }
        }
    }
    public void setGate(int x1, int y1, int x2, int y2){
        int startX = Math.min(x1, x2);
        int endX   = Math.max(x1, x2);
        int startY = Math.min(y1, y2);
        int endY   = Math.max(y1, y2);
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                // Kiểm tra bounds (nếu cần) để tránh lỗi ArrayOutOfBounds
                if (i >= 0 && i < map.length && j >= 0 && j < map[0].length) {
                    map[i][j] = GATE;
                }
            }
        }
    }
    public void setTrap(int x1, int y1, int x2, int y2){
        int startX = Math.min(x1, x2);
        int endX   = Math.max(x1, x2);
        int startY = Math.min(y1, y2);
        int endY   = Math.max(y1, y2);
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                // Kiểm tra bounds (nếu cần) để tránh lỗi ArrayOutOfBounds
                if (i >= 0 && i < map.length && j >= 0 && j < map[0].length) {
                    map[i][j] = TRAP;
                }
            }
        }
    }

    // Kiểm tra vị trí (x, y) có phải tường không
    public boolean isWall(int x, int y) {
        if (x < 0 || x >= mapWidth || y < 0 || y >= mapHeight) return true;
        return map[x][y] == WALL;
    }

    public boolean canRender() {
        return CanRender;
    }
    // Hàm render: vẽ các pixel tường dựa trên boolean map
    public void render(Graphics2D g) {
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (map[x][y] == WALL) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, 1, 1); // Vẽ pixel màu đỏ tại vị trí tường
                }
                else if (map[x][y] == GATE) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x, y, 1, 1); // Vẽ pixel màu xanh lá tại vị trí cổng
                    
                }
                else if (map[x][y] == TRAP) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, 1, 1); // Vẽ pixel màu xanh dương tại vị trí bẫy
                }
            }
        }
    }
}
