package spellManager;



import java.awt.Color;
import java.util.Random;

import Grid.Grid;
import Grid.GridModel;
import Grid.GridModel.Type;
import game.Game;


public class SpellManagerModel {
	
	private SpellManager controller;
	
	private Spell blueSpell;
	private Spell redSpell;
	private Spell yellowSpell;
	private Spell greenSpell;
	private Spell purpleSpell;
	
	
	
	public SpellManagerModel(SpellManager controller) {
		
		this.controller = controller;
		
		blueSpell = new Spell(15, Type.BLUE_JEWEL, game -> blueSpellEffect(game));
		redSpell = new Spell(20, Type.RED_JEWEL, game -> redSpellEffect(game));
		yellowSpell = new Spell(20, Type.YELLOW_JEWEL, game -> {
			try {
				yellowSpellEffect(game);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		greenSpell = new Spell(40, Type.GREEN_JEWEL, game -> greenSpellEffect(game));
		purpleSpell = new Spell(50, Type.PURPLE_JEWEL, game -> purpleSpellEffect(game));
		
	}
	
	private void blueSpellEffect(Game game) {
		Grid grid = game.getModel().getGrid();
		
		int i = new Random().nextInt(grid.getRows() - 1);
		int j = new Random().nextInt(grid.getColumns() - 1);
		
		grid.getModel().destroyRow(i);
		grid.getModel().destroyColumn(j);
		
		game.getModel().incrementScore(grid.getRows()*10 + grid.getColumns()*10);
		
		grid.getModel().checkFlyingTiles();

	}
	private void greenSpellEffect(Game game) {
			
	}
	private void redSpellEffect(Game game) {
		
	}
	private void yellowSpellEffect(Game game) throws InterruptedException {
			game.getModel().addTime(10);
	}
	private void purpleSpellEffect(Game game) {
		
	}
	
	
	public Spell getBlueSpell() {return blueSpell;}
	public Spell getRedSpell() {return redSpell;}
	public Spell getYellowSpell() {return yellowSpell;}
	public Spell getGreenSpell() {return greenSpell;}
	public Spell getPurpleSpell() {return purpleSpell;}
	
	private Spell getSpellByColor(Type color) {
		switch(color) {
		
		case BLUE_JEWEL:
			return blueSpell;
		case RED_JEWEL:
			return redSpell;
		case YELLOW_JEWEL:
			return yellowSpell;
		case GREEN_JEWEL:
			return greenSpell;
		case PURPLE_JEWEL:
			return purpleSpell;
		
		default:
			System.out.println("Error on getSpellByColor");
			return null;
		}
	}
	
	private Spell getSpellByColor(Color color) {
		if (color == Color.BLUE)
			return blueSpell;
		if (color == Color.GREEN)
			return greenSpell;
		if (color == Color.RED)
			return redSpell;
		if (color == Color.YELLOW)
			return yellowSpell;
		if (color == new Color(128,0,128))
			return purpleSpell;
		
		System.out.println("getSpellByColor error : invalid color");
		return null;
	}
	
	public void incrementChargeOfSpell(int increment, Type color) {
		Spell spell = getSpellByColor(color);
		spell.incrementCharge(increment);
	}
	
	public void activateSpell(Color color) {
		Spell spell = this.getSpellByColor(color);
		
		if(spell.isCharged()) {
			
			spell.setCurrentCharge(0);
			spell.doAction(controller.getGame());
			controller.updateView();
		}
	}
}
