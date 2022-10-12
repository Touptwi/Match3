package Grid.jewel;

import Grid.GridModel;
import Grid.Tile;

import java.util.Random;

public class Jewel extends Tile {

    public Jewel(GridModel gridModel) {
        super(gridModel, null);
    }

    public Jewel(GridModel gridModel, GridModel.Type type) {
    	super(gridModel, type);
    }
    
}
