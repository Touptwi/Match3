package game;
public class Game {

    private GameModel game;
    private GameView view;

    public Game() {
        this.game = new GameModel(this);
        this.view = new GameView(this);
    }

    public GameModel getGame() {return this.game;}
    public GameView getView() {return this.view;}
    
    public void scoreChanged() {
    	view.repaint();
    }
}
