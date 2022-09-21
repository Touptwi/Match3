import java.util.Random;

public class Jewel extends Tile {

    public Jewel(Grid grid) {
        super(grid, Type.values()[new Random().nextInt(5)]);
    }


}
