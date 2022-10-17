package spellManager;

import java.awt.Color;

import Grid.GridModel.Type;

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
		this.action = new SpellEffect() {
			
			@Override
			public void action() { }
			
		};
	}
	
	public int getCurrentCharge() { return currentCharge; }
	
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
	
}
