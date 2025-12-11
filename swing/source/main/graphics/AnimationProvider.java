package graphics;

import graphics.AnimationController;

/**
 * AnimationProvider - interface for obtaining animations for entities.
 * Allows dependency injection and easier testing.
 */
public interface AnimationProvider {
    AnimationController createPlayerAnimator(String state);
    AnimationController createChestAnimator(String state);
    AnimationController createCoinAnimator(String state);
}
