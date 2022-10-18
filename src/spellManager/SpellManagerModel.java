package spellManager;


import grid.GridModel.Type;

public class SpellManagerModel {
	
	private SpellManager controller;
	
	private Spell blueSpell;
	private Spell redSpell;
	private Spell yellowSpell;
	private Spell greenSpell;
	private Spell purpleSpell;
	
	public SpellManagerModel(SpellManager controller) {
		
		this.controller = controller;
		
		blueSpell = new Spell(50, Type.BLUE_JEWEL);
		redSpell = new Spell(50, Type.RED_JEWEL);
		yellowSpell = new Spell(50, Type.YELLOW_JEWEL);
		greenSpell = new Spell(50, Type.GREEN_JEWEL);
		purpleSpell = new Spell(50, Type.PURPLE_JEWEL);
		
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
	
	public void incrementChargeOfSpell(int increment, Type color) {
		Spell spell = getSpellByColor(color);
		spell.incrementCharge(increment);
		
		
		
	}
}
