package Grid;

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

    //Set up an awful Listener
    private void setupMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                Tile tileUnderMouse = getTileUnderMouse(e);
                System.out.print("Echange entre ("+ tileUnderMouse.getX() + ", "+ tileUnderMouse.getY() +") et ");
//                System.out.print("Echange entre ("+e.getPoint().x/getModel().getJewelSize().width+", "+e.getPoint().y/getModel().getJewelSize().height+") et ");
                getModel().setSelectedTile(getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height)));

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                Tile tileUnderMouse = getTileUnderMouse(e);
//                System.out.print("("+e.getPoint().x/getModel().getJewelSize().width+", "+e.getPoint().y/getModel().getJewelSize().height+")\n");
                System.out.print("("+ tileUnderMouse.getX() +", "+ tileUnderMouse.getY() +")\n");
                Tile pointedTile = getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height));
                getModel().switchTiles(getSelectedTile(), pointedTile);
                checkMatch3To(getSelectedTile());
                checkMatch3To(pointedTile);
//                getView().movingTileAnimation(getSelectedTile(), getModel().getCoords(pointedTile), getModel().getCoords(getSelectedTile()));
                getView().setCursorDraggingPoint(null);
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
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

    public Tile getTileUnderMouse(MouseEvent e) {

        Point p = e.getPoint();
        Dimension jewelSize = getModel().getJewelSize();

        return getModel().getTile(new Point(p.x/jewelSize.width,
                p.y/jewelSize.height));

    }

    private void handleMouseMoved(MouseEvent e) {
        Tile tileUnderMouse = getTileUnderMouse(e);
        if(tileUnderMouse!=null)
            getView().setHoveredTilePosition(tileUnderMouse.getCoords());
        else
            getView().setHoveredTilePosition(null);
    }

}
