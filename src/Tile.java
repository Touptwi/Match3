import java.awt.*;

public abstract class Tile {

    private Grid grid;

    public enum Type {RED, GREEN, BLUE, PURPLE, YELLOW}
    private Type type;

    public Tile(Grid grid, Type type) {
        this.grid = grid;
        this.type = type;
    }

    public Point getCoords() {return this.grid.getCoords(this);}
    public Type getType() {return this.type;}
}
