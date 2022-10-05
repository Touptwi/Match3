package Grid;

import java.awt.*;

public abstract class Tile {

    private GridModel gridModel;
    private GridModel.Type type;

    public Tile(GridModel gridModel, GridModel.Type type) {
        this.gridModel = gridModel;
        this.type = type;
    }

    public Tile(GridModel gridModel) {
    	this.gridModel = gridModel;
    }
    
    public Point getCoords() {return this.gridModel.getCoords(this);}
    public GridModel.Type getType() {return this.type;}

    public void setType(GridModel.Type newType) {this.type = newType;}
    
    
    public void moveTo(GridModel.Direction Direction) {
        this.gridModel.moveTileTo(this, Direction);
    }
}
