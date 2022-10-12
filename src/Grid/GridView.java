package Grid;

import Grid.jewel.Jewel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridView {

    private Graphics2D g;
    private Grid grid;

    private Point cursorDraggingPoint;

    private Point hoveredTilePosition;

    private ArrayList<Tile> tempTiles = new ArrayList<>();
    private ArrayList<Point> tempTilePositions = new ArrayList<>();
    private ArrayList<Point> tempHidingPositions = new ArrayList<>();

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
        if(!tempTiles.isEmpty())
            drawTempTiles();
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

    public boolean isTempTilesEmpty() {return this.tempTiles.isEmpty();}

    public void movingTileAnimation(Tile Tile, Point oldPosition, Point newPosition) {
        Dimension jewelSize = this.grid.getModel().getJewelSize();
        Tile tempTile = new Jewel(grid.getModel(), Tile.getType());
        this.tempTiles.add(tempTile);
        Point tempTilePosition = new Point(oldPosition.x*jewelSize.width, oldPosition.y*jewelSize.height);
        this.tempTilePositions.add(tempTilePosition);
        this.tempHidingPositions.add(newPosition);
//        Tile.setType(null);
        Timer timer = new Timer(10, null);
        timer.addActionListener( e -> timerAction(tempTile, tempTilePosition, timer, Tile, newPosition, jewelSize));
        timer.start();
    }

    private void timerAction(Tile tempTile, Point tempTilePosition, Timer timer, Tile tile, Point newPosition, Dimension jewelSize) {
        if(tempTilePosition.x < newPosition.x*jewelSize.width)
            tempTilePosition.x +=jewelSize.width/3;
        else if(tempTilePosition.x > newPosition.x*jewelSize.width)
            tempTilePosition.x -=jewelSize.width/3;
        if(tempTilePosition.y < newPosition.y*jewelSize.height)
            tempTilePosition.y +=jewelSize.height/3;
        else if(tempTilePosition.y > newPosition.y*jewelSize.height)
            tempTilePosition.y -=jewelSize.height/3;
        this.grid.repaint();
        if(tempTilePosition.x == newPosition.x*jewelSize.width && tempTilePosition.y == newPosition.y*jewelSize.height) {
//            tile.setType(tempTile.getType());
            timer.stop();
            this.tempTiles.remove(tempTile);
            this.tempTilePositions.remove(tempTilePosition);
            this.tempHidingPositions.remove(newPosition);
        }
    }

    private void drawTempTiles() {
        for(Tile tile : this.tempTiles) {
            Point tempHidingPosition = new Point(this.tempHidingPositions.get(this.tempTiles.indexOf(tile)).x*this.grid.getModel().getJewelSize().width,
                    this.tempHidingPositions.get(this.tempTiles.indexOf(tile)).y*this.grid.getModel().getJewelSize().height);
            this.g.setColor(Color.WHITE);
            this.g.fillRect(tempHidingPosition.x, tempHidingPosition.y, this.grid.getModel().getJewelSize().width, this.grid.getModel().getJewelSize().height);
        }
        for(Tile tile : this.tempTiles) {
            drawTile(tile, this.tempTilePositions.get(this.tempTiles.indexOf(tile)));
        }
    }
}
