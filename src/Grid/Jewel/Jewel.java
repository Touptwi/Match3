package Grid.Jewel;

import Grid.GridModel;
import Grid.Tile;

import java.util.Random;


public class Jewel extends Tile {

    public Jewel(GridModel gridModel) {
        super(gridModel, GridModel.Type.values()[new Random().nextInt(5)]);
    }

    public Jewel(Grid grid, Grid.Type type) {
    	super(grid, type);
    }
    
}
