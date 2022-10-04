import java.awt.*;

public abstract class Tile {

    private Grid grid;
    private Grid.Type type;

    public Tile(Grid grid, Grid.Type type) {
        this.grid = grid;
        this.type = type;
    }

    public Point getCoords() {return this.grid.getCoords(this);}
    public Grid.Type getType() {return this.type;}

    public void setType(Grid.Type newType) {this.type = newType;}
    public void moveTo(Grid.Direction Direction) {
        this.grid.moveTileTo(this, Direction);
    }
}
