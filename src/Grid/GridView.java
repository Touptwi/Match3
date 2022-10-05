package Grid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GridView {

    private Graphics2D g;
    private Grid grid;

    private Point cursorPoint;

    public void paint(Graphics2D g, Grid grid) {
        this.g = g;
        this.grid = grid;
        drawGrid();
        if (cursorPoint!=null)
            drawSelectedTile();
    }

    private void drawGrid() {
        for (int y = 0; y < this.grid.getModel().getColumns(); y++) {
            for (int x = 0; x < this.grid.getModel().getRows(); x++) {
                String imagePath = "Grid/Jewel/" + this.grid.getModel().getTile(new Point(x, y)).getType().name() + ".png";
                try {

                    this.g.drawImage(ImageIO.read(new File(imagePath)), x*this.grid.getModel().getJewelSize().width, y*this.grid.getModel().getJewelSize().height,
                            this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height, this.grid);
                } catch (IOException e) {System.out.print("Can't load image at " + imagePath);}

            }
        }
    }

    private void drawSelectedTile() {
        String imagePath = "Grid/Jewel/" + this.grid.getModel().getSelectedTile().getType().name() + ".png";
        try {
            this.g.drawImage(ImageIO.read(new File(imagePath)), this.cursorPoint.x-this.grid.getModel().getJewelSize().width/2, this.cursorPoint.y-this.grid.getModel().getJewelSize().height/2,
                    this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height, this.grid);
        } catch (IOException e) {System.out.print("Can't load image at " + imagePath);}
    }

    public void setCursorPoint(Point newCursorpoint) {this.cursorPoint = newCursorpoint;}
}
