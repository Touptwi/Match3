package game;
import Grid.Grid;
import SpellsBook.SpellsBook;

public class GameModel {
	
    private Grid grid;
    private int timer;
    private SpellsBook spellsBook;
    private int score;
    
    private Game controller;

    public GameModel(Game controller) {
    	this.controller = controller;
    	
    	score = 0;
        setupGrid(10,10);
        setupSpellsBook();
        startTimer();
    }

    private void setupGrid(int rows, int columns) {
        this.grid = new Grid(rows, columns, this);
    }

    private void setupSpellsBook() {
        this.spellsBook = new SpellsBook();
    }

    private void startTimer() {
        //TODO
    }

    public Grid getGrid() {return this.grid;}

    public int getTimer() {return this.timer;}

    public SpellsBook getSpellsBook() {return this.spellsBook;}
    
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
    public void incrementScore(int increment) { 
    	this.score += increment;
    	controller.scoreChanged();
    }
}
