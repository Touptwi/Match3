package Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GridView {
    public void paint(Graphics2D g, Grid grid) {
        for (int y = 0; y < grid.getModel().getColumns(); y++) {
            for (int x = 0; x < grid.getModel().getRows(); x++) {
                String imagePath = "Grid/Jewel/" + grid.getModel().getTile(new Point(x, y)).getType().name() + ".png";

                try {
                    g.drawImage(ImageIO.read(new File(imagePath)), x * 75, y * 75, 75, 75, grid);
                } catch (IOException e) {
                    System.out.println("Can't load image at " + imagePath);
                }
            }
        }
    }
}
