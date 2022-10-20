package game;
import java.util.Timer;
import java.util.TimerTask;

import Grid.Grid;
import Grid.GridModel.Type;
import spellManager.SpellManager;

public class GameModel {
	
    private Grid grid;
    private SpellManager spellManager;
    
    private int timer;
    private int currentTime;
    private int score;
    
    private Timer clock;
    
    private Game controller;

    public GameModel(Game controller, int timer) {
    	this.controller = controller;
    	this.timer = timer;
    	
    	clock = new Timer();
    	currentTime = timer;
    	
    	clock.scheduleAtFixedRate(updateClock, 1000, 1000);

    	
    	score = 0;
        setupGrid(9,9);
        setupSpellManager();
        startTimer();
    }
    public GameModel(Game controller) {
        this.controller = controller;
        this.timer = -1;

        score = 0;
        setupGrid(9,9);
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
    public int getClock() {return this.currentTime;}
    
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
	
	// Each seconds, timer decreases
	private TimerTask updateClock = new TimerTask() {
		
		@Override
		public void run() {
			currentTime--;
			controller.updateTimer();
			
			if (currentTime == 0) {
				cancel();
				if (!(timer == -1))
					controller.endGame();
			}
		}
	};

	public void addTime(int i) {
		currentTime += i;
		controller.updateTimer();
		
	}
}
