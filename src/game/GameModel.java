package game;
import Grid.Grid;
import SpellsBook.SpellsBook;
import spellManager.SpellManager;

public class GameModel {
	
    private Grid grid;
    private SpellManager spellManager;
    
    private int timer;
    private int score;
    
    private Game controller;

    public GameModel(Game controller) {
    	this.controller = controller;
    	
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
        //TODO
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
}
