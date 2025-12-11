package entities;

import graphics.AnimationProvider;
import entities.characters.Player;
import entities.items.Chest;

/**
 * EntityFactory - tạo các entity đã được cấu hình sẵn (đăng ký animation via provider)
 */
public class EntityFactory {

    private final AnimationProvider provider;

    public EntityFactory(AnimationProvider provider) {
        this.provider = provider;
    }

    public Player createPlayer(int x, int y) {
        Player p = new Player(x, y);
        p.registerAnimations(provider);
        return p;
    }

    public Chest createChest(int x, int y) {
        Chest c = new Chest(x, y);
        c.registerAnimations(provider);
        return c;
    }
}
