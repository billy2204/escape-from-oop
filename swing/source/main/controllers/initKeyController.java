package controllers;

public class initKeyController {
    private InputController inputController;

    public initKeyController(InputController inputController) {
        this.inputController = inputController;
    }
    public void update() {
        if (inputController.isRequestingUp()) {
            System.out.println("Moving Up");
        }
        if (inputController.isRequestingDown()) {
            System.out.println("Moving Down");
        }
        if (inputController.isRequestingLeft()) {
            System.out.println("Moving Left");
        }
        if (inputController.isRequestingRight()) {
            System.out.println("Moving Right");
        }
        // if (inputController.isRelease()) {
        //     System.out.println("No Movement");
        // }
        
    }   
    public static void main(String[] args) {
        Input input = new Input();
        // Tạo keyController với Input
        keyController inputController = new keyController(input);
        // Tạo game
        initKeyController game = new initKeyController(inputController);
        // Tạo JFrame để nhận keyboard input
        javax.swing.JFrame frame = new javax.swing.JFrame("Test Game");
        frame.addKeyListener(input);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Game loop
        while (true) {
            game.update();
            try {
                Thread.sleep(100); // 10 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
