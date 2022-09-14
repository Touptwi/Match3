public class Game {

    private Grid grid;
    private int Timer;
    private SpellsBook spellsBook;

    public Game() {
        setupGrid();
        setupSpellsBook();
        startTimer();
    }

    private void setupGrid() {
        this.grid = new Grid();
    }

    private void setupSpellsBook() {
        this.spellsBook = new SpellsBook();
    }

    private void startTimer() {
        //TODO
    }
}
