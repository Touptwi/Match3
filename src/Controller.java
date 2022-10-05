public class Controller {

    private Game game;
    private View view;

    public Controller() {
        this.game = new Game();
        this.view = new View(this);
    }

    public Game getGame() {return this.game;}
    public View getView() {return this.view;}
}
