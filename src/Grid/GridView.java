package Grid;

import java.awt.*;

public class GridView {

    private Graphics2D g;
    private Grid grid;

    private Point cursorPoint;

    public void paint(Graphics2D g, Grid grid) {
        this.g = g;
        this.grid = grid;
        this.g.setColor(Color.WHITE);
        this.g.fillRect(0,0, grid.getWidth(), grid.getHeight());
        drawGrid();
        if (cursorPoint!=null)
            drawSelectedTile();
    }

    private void drawGrid() {
        for (int y = 0; y < this.grid.getModel().getColumns(); y++) {
            for (int x = 0; x < this.grid.getModel().getRows(); x++) {
                drawTile(this.grid.getGridTable().get(x).get(y),
                        new Point(x*this.grid.getModel().getJewelSize().width,
                                y*this.grid.getModel().getJewelSize().height));
            }
        }
    }

    private void drawSelectedTile() {
        drawTile(this.grid.getSelectedTile(),
                new Point(this.cursorPoint.x-this.grid.getModel().getJewelSize().width/2,
                        this.cursorPoint.y-this.grid.getModel().getJewelSize().height/2));
    }

    private void drawTile(Tile Tile, Point Position) {
        if(Tile.getType()!=null)
            this.g.drawImage(this.grid.getJewelImages().get(Tile.getType().ordinal()), Position.x, Position.y,
                            this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height, this.grid);
    }

    public void setCursorPoint(Point newCursorpoint) {this.cursorPoint = newCursorpoint;}
    
    

}
