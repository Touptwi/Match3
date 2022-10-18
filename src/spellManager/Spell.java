package spellManager;

import Grid.GridModel.Type;
import game.Game;


/** class Spell
 * Describe a spell on the grid.
 * 
 * Contains a specific color, and an effect.
 *
 */
public class Spell {
	
	private Type color;
	private SpellEffect action;
	
	private int chargeNeeded, currentCharge;
	
	
	public Spell(int chargeNeeded, Type color, SpellEffect action) {
		
		this.chargeNeeded = chargeNeeded;
		this.currentCharge = 0;
		
		this.color = color;
		this.action = action;
	}
	
	public Spell(int chargeNeeded, Type color) {
		this.chargeNeeded = chargeNeeded;
		this.currentCharge = 0;
		
		this.color = color;
		
		// On instancie un SpellEffect null

		this.action = g -> {};
	}
	
	
	//Getters 
	public int getCurrentCharge() { return currentCharge; }
	public int getMaximumCharge() {return chargeNeeded;}
	
	public void setCurrentCharge(int newCharge) { 
		this.currentCharge = newCharge;
		if(this.currentCharge > this.chargeNeeded)
			this.currentCharge = this.chargeNeeded;
	}
	
	public void incrementCharge(int increment) {
		this.currentCharge += increment;
		
		if(this.currentCharge > this.chargeNeeded)
			this.currentCharge = this.chargeNeeded;
	}

	public float getRatio() {
		return (float)currentCharge/(float)chargeNeeded;
	}
	
	public boolean isCharged() {
		return (currentCharge == chargeNeeded);
	}
	
	public void doAction(Game game) {
		action.action(game);
	}
	
}
