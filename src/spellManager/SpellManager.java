package spellManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JProgressBar;

import grid.GridModel.Type;
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
		this.view = new SpellManagerView(this, mouseListener);
		
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
		view.updateSpellBars();
		repaint();
		
	}

	
	public void incrementChargeOfSpell(int increment, Type matchColor) {
		this.model.incrementChargeOfSpell(increment, matchColor);
		
		//Once model is updated, we notify the view to repaint itself
		this.view.updateSpellBars();
	}
	
	private MouseListener mouseListener = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) { }
			
			@Override
			public void mousePressed(MouseEvent e) { }
			
			@Override
			public void mouseExited(MouseEvent e) { }
			
			@Override
			public void mouseEntered(MouseEvent e) { }
			
			//le mouselistener ne sert que pour les jprogressBar
			@Override
			public void mouseClicked(MouseEvent e) {
				JProgressBar progressBar = (JProgressBar)e.getComponent();
				Color color = progressBar.getForeground();
				
				model.activateSpell(color);
				
			}
		};
	
}
