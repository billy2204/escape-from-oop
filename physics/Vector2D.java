package physics;

public class Vector2D {
    public float x, y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // --- CÁC HÀM CŨ (GIỮ NGUYÊN) ---
    public void add(Vector2D v) { x += v.x; y += v.y; }
    public void subtract(Vector2D v) { x -= v.x; y -= v.y; }
    public void multiply(float scalar) { x *= scalar; y *= scalar; }
    public void normalize() {
        float len = length();
        if (len != 0) { x /= len; y /= len; }
    }
    public void limit(float max) {
        if (length() > max) { normalize(); multiply(max); }
    }
    public float length() { return (float) Math.sqrt(x * x + y * y); }
    public Vector2D copy() { return new Vector2D(x, y); }

    // Static helpers
    // distance between two vectors
    public static float distance(Vector2D v1, Vector2D v2) {
        float dx = v1.x - v2.x;
        float dy = v1.y - v2.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    // Hàm trừ 2 vector trả về vector mới
    public static Vector2D subtract(Vector2D v1, Vector2D v2) {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }
}