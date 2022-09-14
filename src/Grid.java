import java.util.ArrayList;

public class Grid {

    private int rows, columns;
    private ArrayList grid;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new ArrayList<>(columns);
        initGrid();
    }

    private void initGrid() {
        for (Object column : this.grid) {
            column = new ArrayList<Tile>(this.rows);
        }
    }
}
