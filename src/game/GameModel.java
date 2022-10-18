package game;
import grid.Grid;
import grid.GridModel.Type;
import spellManager.SpellManager;

public class GameModel {
	
    private Grid grid;
    private SpellManager spellManager;
    
    private int timer;
    private int score;
    
    private Game controller;

    public GameModel(Game controller, int timer) {
    	this.controller = controller;
    	this.timer = timer;
    	
    	score = 0;
        setupGrid(10,10);
        setupSpellManager();
        startTimer();
    }
    public GameModel(Game controller) {
        this.controller = controller;
        this.timer = -1;

        score = 0;
        setupGrid(10,10);
        setupSpellManager();
        startTimer();
    }

    private void setupGrid(int rows, int columns) {
        this.grid = new Grid(rows, columns, this);
    }

    private void setupSpellManager() {
        this.spellManager = new SpellManager(this.controller);
    }

    private void startTimer() {
        if(this.timer != -1) {
            //TODO
        }
    }

    public Grid getGrid() {return this.grid;}
    public SpellManager getSpellManager() {return this.spellManager; }
    public int getTimer() {return this.timer;}

    
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
    public void incrementScore(int increment) { 
    	this.score += increment;
    	controller.scoreChanged();
    }

	public void notifyMatch(int increment, Type matchColor) {
		incrementScore(increment);
		spellManager.incrementChargeOfSpell(increment, matchColor);
		
		controller.updateSpellManager();
	}
}
