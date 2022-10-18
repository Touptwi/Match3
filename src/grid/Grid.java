package grid;

import javax.swing.*;

import game.GameModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Grid extends JComponent {

    private GridModel model;
    private GridView view;

    private GameModel game;

    public Grid(int rows, int columns, GameModel game) {
        this.model = new GridModel(rows, columns, this);
        this.view = new GridView();

        this.game = game;
        setupMouseListener();
    }

    public GridModel getModel() {return this.model;}
    public GridView getView() {return this.view;}
    public GameModel getGame() {return this.game; }
    public ArrayList<ArrayList<Tile>> getGridTable() {return this.model.getGridTable();}
    public Tile getSelectedTile() {return this.model.getSelectedTile();}
    public List<BufferedImage> getJewelImages() {return this.model.getJewelImages();}
    public void checkMatch3To(Tile tile) {this.model.checkMatch3To(tile);}
    public void checkFlyingTiles() {this.model.checkFlyingTiles();}

    //Setup View
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.view.paint((Graphics2D)g, this);
    }
    //Layout
    @Override
    public Dimension getSize(){return new Dimension(this.model.getJewelSize().width*this.model.getColumns(), this.model.getJewelSize().height*this.model.getRows());}
    @Override
    public Dimension getPreferredSize(){return getSize();}

    //MouseListener to make a move
    private void setupMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //Catch the first Tile selected
                getModel().setSelectedTile(getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height)));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                //Catch the second Tile selected to make a move
                Tile tileUnderMouse = getTileUnderMouse(e);
                System.out.print("("+ tileUnderMouse.getX() +", "+ tileUnderMouse.getY() +")\n");
                Tile pointedTile = getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height));
                getModel().switchTiles(getSelectedTile(), pointedTile);
                //Check both new changes
                checkMatch3To(getSelectedTile());
                checkMatch3To(pointedTile);

                getView().setCursorDraggingPoint(null);
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                //Catch the point where the drag start
                getView().setCursorDraggingPoint(e.getPoint());
                handleMouseMoved(e);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                handleMouseMoved(e);
                repaint();
            }

        });
    }

    //Return the Tile under the mouse
    public Tile getTileUnderMouse(MouseEvent e) {

        Point p = e.getPoint();
        Dimension jewelSize = getModel().getJewelSize();

        return getModel().getTile(new Point(p.x/jewelSize.width,
                p.y/jewelSize.height));

    }

    //Update which tile is hovered
    private void handleMouseMoved(MouseEvent e) {
        Tile tileUnderMouse = getTileUnderMouse(e);
        if(tileUnderMouse!=null)
            getView().setHoveredTilePosition(tileUnderMouse.getCoords());
        else
            getView().setHoveredTilePosition(null);
    }
}
