package graphics;

/**
 * DefaultAnimationProvider - default implementation delegating to AnimationFactory
 */
public class DefaultAnimationProvider implements AnimationProvider {

    @Override
    public AnimationController createPlayerAnimator(String state) {
        return AnimationFactory.createPlayerAnimator(state);
    }

    @Override
    public AnimationController createChestAnimator(String state) {
        return AnimationFactory.createChestAnimator(state);
    }

    @Override
    public AnimationController createCoinAnimator(String state) {
        return AnimationFactory.createCoinAnimator(state);
    }
    @Override
    public AnimationController createDoorAnimator(String state) {
        return AnimationFactory.createDoorAnimator(state);
    }
}
