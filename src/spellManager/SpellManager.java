package spellManager;

import java.awt.Graphics;

import javax.swing.JComponent;

import Grid.GridModel.Type;
import game.Game;


/** Class SpellManager.
 * 
 * @author mouge
 *
 */
public class SpellManager extends JComponent {
	
	private SpellManagerModel model;
	private SpellManagerView view;
	
	private Game game;
	
	public SpellManager(Game game) {
		this.model = new SpellManagerModel(this);
		this.view = new SpellManagerView(this);
		
		this.game = game;
	}
	
	//Getters
	public SpellManagerModel getModel() { return this.model; }
	public SpellManagerView getView() {return this.view; }
	public Game getGame() { return this.game; }
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
	}

	public void updateView() {
		repaint();
		
	}

	
	public void incrementChargeOfSpell(int increment, Type matchColor) {
		this.model.incrementChargeOfSpell(increment, matchColor);
		
		//Once model is updated, we notify the view to repaint itself
		this.view.updateSpellBars();
	}
	
	
}
