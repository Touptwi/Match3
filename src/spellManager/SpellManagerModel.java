package spellManager;

import java.awt.Color;

import Grid.GridModel.Type;

public class SpellManagerModel {
	
	private Spell blueSpell;
	private Spell redSpell;
	private Spell yellowSpell;
	private Spell greenSpell;
	private Spell purpleSpell;
	
	public SpellManagerModel() {
		
		blueSpell = new Spell(50, Type.BLUE_JEWEL);
		redSpell = new Spell(50, Type.RED_JEWEL);
		yellowSpell = new Spell(50, Type.YELLOW_JEWEL);
		greenSpell = new Spell(50, Type.GREEN_JEWEL);
		purpleSpell = new Spell(50, Type.PURPLE_JEWEL);
		
	}
	
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
	
	public void incrementChargeOfSpell(int increment, Type color) {
		Spell spell = getSpellByColor(color);
		spell.incrementCharge(increment);
	}
}
