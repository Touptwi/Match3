import java.awt.*;
import java.util.ArrayList;

public class Grid {

    private final int rows, columns;
    private ArrayList<ArrayList<Tile>> grid;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new ArrayList<>(this.columns);
        initGrid();
    }

    private void initGrid() {
        for (ArrayList<Tile> column : this.grid) {
            column = new ArrayList<>(this.rows);
        }
    }

    private Point getCoords(Tile tile) {
        for (ArrayList<Tile> column : this.grid) {
            for (Tile Tile : column) {
                if (Tile == tile) {
                    return new Point(this.grid.indexOf(column), column.indexOf(Tile));
                }
            }
        }
        System.out.print("Grid.getCoords() error: Tile not find");
        return new Point(-1, -1);
    }

    private Tile getTile(Point Coords) {
        if(Coords.x>=0 && Coords.y>=0 && Coords.x<this.columns && Coords.y<this.rows) {
            return this.grid.get(Coords.x).get(Coords.y);
        }
        System.out.print("Grid.getTile() error: Out of grid");
        return null;
    }

    private ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        Point tileCoords = this.getCoords(tile);
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y+1)));
        neighbors.add(this.getTile(new Point(tileCoords.x+1, tileCoords.y)));
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y-1)));
        neighbors.add(this.getTile(new Point(tileCoords.x-1, tileCoords.y)));
        return neighbors;
    }
}