package Grid;

import java.awt.*;

public class GridView {

    private Graphics2D g;
    private Grid grid;

    private Point cursorDraggingPoint;

    private Point hoveredTilePosition;

    public void paint(Graphics2D g, Grid grid) {
        this.g = g;
        this.grid = grid;
        this.g.setColor(Color.WHITE);
        this.g.fillRect(0,0, grid.getWidth(), grid.getHeight());
        if(hoveredTilePosition !=null)
            drawHoveredTile();
        drawGrid();
        if (cursorDraggingPoint !=null)
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

    private void drawTile(Tile Tile, Point Position) {
        if(Tile.getType()!=null)
            this.g.drawImage(this.grid.getJewelImages().get(Tile.getType().ordinal()), Position.x, Position.y,
                            this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height, this.grid);
    }

    private void drawHoveredTile() {
        this.g.setColor(Color.LIGHT_GRAY);
        this.g.fillRect(this.hoveredTilePosition.x*this.grid.getModel().getJewelSize().width,
                        this.hoveredTilePosition.y*this.grid.getModel().getJewelSize().height,
                    this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height);
    }

    private void drawSelectedTile() {
        drawTile(this.grid.getSelectedTile(),
                new Point(this.cursorDraggingPoint.x-this.grid.getModel().getJewelSize().width/2,
                        this.cursorDraggingPoint.y-this.grid.getModel().getJewelSize().height/2));
    }

    public void setCursorDraggingPoint(Point newCursorPoint) {this.cursorDraggingPoint = newCursorPoint;}
    
    public void setHoveredTilePosition(Point newTilePosition) {this.hoveredTilePosition = newTilePosition;}
}
