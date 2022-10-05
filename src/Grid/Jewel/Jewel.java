package Grid.Jewel;

import Grid.GridModel;
import Grid.Tile;

import java.util.Random;

public class Jewel extends Tile {

    public Jewel(GridModel gridModel) {
        super(gridModel, GridModel.Type.values()[new Random().nextInt(5)]);
    }

    public Jewel(GridModel gridModel, GridModel.Type type) {
    	super(gridModel, type);
    }
    
}
