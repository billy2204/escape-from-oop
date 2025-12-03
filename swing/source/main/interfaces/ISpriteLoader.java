package interfaces;

import java.awt.image.BufferedImage;

/**
 * Interface for loading sprites from different sources
 */
public interface ISpriteLoader {
    BufferedImage[] loadSprites(String path);
    BufferedImage loadSprite(String path);
}
