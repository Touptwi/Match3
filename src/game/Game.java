package game;

import java.awt.Graphics;

import javax.swing.JComponent;

public class Game extends JComponent{

    private GameModel game;
    private GameView view;

    public Game() {
        this.game = new GameModel(this);
        this.view = new GameView(this);
    }

    public GameModel getGame() {return this.game;}
    public GameView getView() {return this.view;}
    
    public void scoreChanged() {
    	view.updateScoreLabel();
    	view.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	view.paint(g);
    }
}
