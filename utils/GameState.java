package utils; // Hoặc package nào bạn muốn

public enum GameState {
    PLAYING, MENU, QUIT;

    public static GameState state = MENU; // Mặc định vào game là Menu
}