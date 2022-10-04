import java.awt.*;
import java.util.ArrayList;

public class Grid {

    private final int rows, columns;
    private ArrayList<ArrayList<Tile>> gridTable;
    public enum Direction {NORTH, EAST, SOUTH, WEST}

    public enum Type {RED_JEWEL, GREEN_JEWEL, BLUE_JEWEL, PURPLE_JEWEL, YELLOW_JEWEL}

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.gridTable = new ArrayList<>();
        createGridTable();
    }

    private void createGridTable() {
        for (int i=0; i<this.columns; i++) {
            this.gridTable.add(new ArrayList<>(this.rows));
            for (int j=0; j<this.rows; j++) {
                this.gridTable.get(i).add(new Jewel(this));
            }
        }
    }

    public Point getCoords(Tile tile) {
        for (ArrayList<Tile> column : this.gridTable) {
            for (Tile Tile : column) {
                if (Tile == tile) {
                    return new Point(this.gridTable.indexOf(column), column.indexOf(Tile));
                }
            }
        }
        System.out.print("Grid.getCoords() error: Tile not find");
        return new Point(-1, -1);
    }

    public Tile getTile(Point Coords) {
        if(Coords.x>=0 && Coords.y>=0 && Coords.x<this.columns && Coords.y<this.rows) {
            return this.gridTable.get(Coords.x).get(Coords.y);
        }
        System.out.print("Grid.getTile() error: Out of grid");
        return null;
    }

    public ArrayList<Tile> getNeighbors(Tile tile) {
        ArrayList<Tile> neighbors = new ArrayList<>();
        Point tileCoords = this.getCoords(tile);
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y+1)));
        neighbors.add(this.getTile(new Point(tileCoords.x+1, tileCoords.y)));
        neighbors.add(this.getTile(new Point(tileCoords.x, tileCoords.y-1)));
        neighbors.add(this.getTile(new Point(tileCoords.x-1, tileCoords.y)));
        return neighbors;
    }

    public ArrayList<ArrayList<Tile>> getGridTable() {return this.gridTable;}
    public int getColumns() {return this.columns;}
    public int getRows() {return this.rows;}

    private void switchTiles(Tile Tile1, Tile Tile2) {
        if(Tile1!=null && Tile2!=null) {
            Type temp = Tile1.getType();
            Tile1.setType(Tile2.getType());
            Tile2.setType(temp);
        }
    }

    public void moveTileTo(Tile Tile, Direction direction) {
        if(direction.equals(Direction.NORTH)){
            switchTiles(Tile, getNeighbors(Tile).get(0));
        } else if (direction.equals(Direction.EAST)) {
            switchTiles(Tile, getNeighbors(Tile).get(1));
        } else if (direction.equals(Direction.SOUTH)) {
            switchTiles(Tile, getNeighbors(Tile).get(2));
        } else if (direction.equals(Direction.WEST)) {
            switchTiles(Tile, getNeighbors(Tile).get(3));
        }
    }
}
