public class Abstraction {

    private Controller controller;
    private Game game;

    public Abstraction(Controller controller) {
        this.controller = controller;
        setupGame();
    }

    private void setupGame() {
        this.game = new Game();
    }

    public Game getGame() {return this.game;}
}
