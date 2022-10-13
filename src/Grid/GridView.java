package Grid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GridView {

    private Graphics2D g;
    private Grid grid;

    private Point cursorDraggingPoint;

    private Point hoveredTilePosition;

    private ArrayList<Tile> tempTiles = new ArrayList<>();
    private ArrayList<Point> tempTilePositions = new ArrayList<>();
    private ArrayList<Point> tempHidingPositions = new ArrayList<>();

    private BufferedImage background;

    public GridView() {
        try {this.background = ImageIO.read(ClassLoader.getSystemResource("Images/WaterFall Background.jpg"));
        } catch (IOException e) {e.fillInStackTrace();}
    }

    public void paint(Graphics2D g, Grid grid) {
        this.g = g;
        this.grid = grid;
//        this.g.setColor(Color.WHITE);
//        this.g.fillRect(0,0, grid.getWidth(), grid.getHeight());
        this.g.drawImage(this.background, 0,0,this.grid);
        if(this.hoveredTilePosition !=null)
            drawHoveredTile();
        drawGrid();
        if (this.cursorDraggingPoint !=null)
            drawSelectedTile();
        if(!this.tempTiles.isEmpty())
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
        this.g.setColor(new Color(0,0,0,133));
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
        newPosition.move(newPosition.x*jewelSize.width, newPosition.y*jewelSize.height);
        this.tempTilePositions.add(tempTilePosition);
        this.tempHidingPositions.add(newPosition);
        Timer timer = new Timer(50, null);
        Dimension jewelSizePart = new Dimension(jewelSize.width/3, jewelSize.height/3);
        timer.addActionListener( e -> timerAction(tempTile, tempTilePosition, timer, newPosition, jewelSizePart));
        timer.start();
    }

    private void timerAction(Tile tempTile, Point tempTilePosition, Timer timer, Point newPosition, Dimension jewelSizePart) {
        if(tempTilePosition.x < newPosition.x)
            tempTilePosition.x +=jewelSizePart.width;
        else if(tempTilePosition.x > newPosition.x)
            tempTilePosition.x -=jewelSizePart.width;
        if(tempTilePosition.y < newPosition.y)
            tempTilePosition.y +=jewelSizePart.height;
        else if(tempTilePosition.y > newPosition.y)
            tempTilePosition.y -=jewelSizePart.height;
        this.grid.repaint();
        if(tempTilePosition.x == newPosition.x && tempTilePosition.y == newPosition.y) {
            timer.stop();
            this.tempTiles.remove(tempTile);
            this.tempTilePositions.remove(tempTilePosition);
            this.tempHidingPositions.remove(newPosition);
            if(isTempTilesEmpty())
                this.grid.getModel().goBackToModel();
        }
    }

    private void drawTempTiles() {
        for(Tile tile : this.tempTiles) {
            Point tempHidingPosition = this.tempHidingPositions.get(this.tempTiles.indexOf(tile));
            Dimension jewelSize = this.grid.getModel().getJewelSize();
            BufferedImage tempSubImage = this.background.getSubimage(tempHidingPosition.x, tempHidingPosition.y, jewelSize.width, jewelSize.height);
            this.g.drawImage(tempSubImage, tempHidingPosition.x, tempHidingPosition.y, this.grid);
        }
        for(Tile tile : this.tempTiles) {
            drawTile(tile, this.tempTilePositions.get(this.tempTiles.indexOf(tile)));
        }
    }
}
