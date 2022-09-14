public class Abstraction {

    private Controller controller;
    private Game game;

    public Abstraction(Controller controller) {
        this.controller = controller;

    }

    private void setupGame() {
        this.game = new Game();
    }
}
