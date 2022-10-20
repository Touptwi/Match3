package Grid;

public class Jewel extends Tile {

    public Jewel(GridModel gridModel) {
        super(gridModel, null);
    }

    public Jewel(GridModel gridModel, GridModel.Type type) {
        super(gridModel, type);
    }
}