public class Game {

    private Grid grid;
    private int timer;
    private SpellsBook spellsBook;

    public Game() {
        setupGrid(10,10);
        setupSpellsBook();
        startTimer();
    }

    private void setupGrid(int rows, int columns) {
        this.grid = new Grid(rows, columns);
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
}
