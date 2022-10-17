package spellManager;

import java.awt.Graphics;

import javax.swing.JComponent;

import game.Game;

public class SpellManager extends JComponent {
	
	private SpellManagerModel model;
	private SpellManagerView view;
	
	private Game game;
	
	public SpellManager(Game game) {
		this.model = new SpellManagerModel();
		this.view = new SpellManagerView();
		
		this.game = game;
	}
	
	//Getters
	public SpellManagerModel getModel() { return this.model; }
	public SpellManagerView getView() {return this.view; }
	public Game getGame() { return this.game; }
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		view.paint(g, this);
	}
	
	
}
