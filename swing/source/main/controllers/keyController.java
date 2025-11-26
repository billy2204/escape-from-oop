public class keyController implements InputController {
    private Input input;
    
    public keyController(Input input) {
        this.input = input;
    }
    @Override
    public boolean isRequestingUp() {  
        return input.isPressed(java.awt.event.KeyEvent.VK_W)
            || input.isPressed(java.awt.event.KeyEvent.VK_UP);
    }
    @Override
    public boolean isRequestingDown() {
        return input.isPressed(java.awt.event.KeyEvent.VK_S)
            || input.isPressed(java.awt.event.KeyEvent.VK_DOWN);
    }
    @Override
    public boolean isRequestingLeft() {
        return input.isPressed(java.awt.event.KeyEvent.VK_A)
            || input.isPressed(java.awt.event.KeyEvent.VK_LEFT);
    }
    @Override
    public boolean isRequestingRight() {
        return input.isPressed(java.awt.event.KeyEvent.VK_D)
            || input.isPressed(java.awt.event.KeyEvent.VK_RIGHT);
    }
    @Override
    public boolean isRelease() {
        return !isRequestingUp() && !isRequestingDown()
            && !isRequestingLeft() && !isRequestingRight();
    }
    
}
