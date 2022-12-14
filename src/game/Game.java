package game;

import java.awt.Graphics;

import javax.swing.JComponent;

import spellManager.SpellManager;


/** Class Game.
 * 
 * Representation of a single match3 game.
 * Contains all necessary data for a game, like score and Timer.
 * Also manage a Grid and a SpellManager.
 *
 */
public class Game extends JComponent{

    private GameModel model;
    private GameView view;

    public Game(int timer, float volume) {
        this.model = new GameModel(this, timer);
        this.view = new GameView(this, volume);
    }

    public Game(float volume) {
        this.model = new GameModel(this);
        this.view = new GameView(this, volume);
    }

    public GameModel getModel() {return this.model;}
    public GameView getView() {return this.view;}
    
    public SpellManager getSpellManager() { return this.model.getSpellManager(); }
    
    public void scoreChanged() {
    	view.updateScoreLabel();
    	view.repaint();
    }
    public void updateTimer() {
    	view.updateTimerLabel();
    }
    
    public void updateSpellManager() {
    	model.getSpellManager().updateView();
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	view.paint(g);
    }

	public void endGame() {
		view.endGame();
		
	}
}
