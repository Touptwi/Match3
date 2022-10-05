package Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Grid extends JComponent {

    private GridModel model;
    private GridView view;

    public Grid(int rows, int columns) {
        this.model = new GridModel(rows, columns);
        this.view = new GridView();
        setupMouseListener();
    }

    public GridModel getModel() {return this.model;}
    public GridView getView() {return this.view;}
    public ArrayList<ArrayList<Tile>> getGridTable() {return this.model.getGridTable();}
    public Tile getSelectedTile() {return this.model.getSelectedTile();}

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
                System.out.print("Echange entre ("+e.getPoint().x/getModel().getJewelSize().width+", "+e.getPoint().y/getModel().getJewelSize().height+") et ");
                getModel().setSelectedTile(getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height)));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                System.out.print("("+e.getPoint().x/getModel().getJewelSize().width+", "+e.getPoint().y/getModel().getJewelSize().height+")\n");
                getModel().switchTiles(getSelectedTile(),
                        getModel().getTile(new Point(e.getPoint().x/getModel().getJewelSize().width, e.getPoint().y/getModel().getJewelSize().height)));
                repaint();
            }
        });
    }
}
