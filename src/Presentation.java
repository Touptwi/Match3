import javax.swing.*;
import java.awt.*;

public class Presentation extends JFrame {

    private Controller controller;

    private JPanel gridPanel;

    public Presentation(Controller controller) {
        this.controller = controller;
        setupWindow();
    }

    private void setupWindow() {
        this.setTitle("Jewel Falls");
        this.setPreferredSize(new Dimension(1000, 800));

        setupGrid();

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setupGrid() {
        Grid grid = this.controller.getAbstraction().getGame().getGrid();
        JPanel GridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics gridPanelGraphics) {
                super.paintComponent(gridPanelGraphics);
                gridPanelGraphics.setColor(Color.BLACK);

                for (int i=0; i<=grid.getRows(); i++) {
                    int y = i*this.getHeight()/grid.getRows();
                    gridPanelGraphics.drawLine(0,y,this.getWidth(),y);
                }
                for (int j=0; j<grid.getColumns(); j++) {
                    int x = j*this.getWidth()/grid.getColumns();
                    gridPanelGraphics.drawLine(x,0,x, this.getHeight());
                }
            }
        };
        
        this.gridPanel = GridPanel;
        this.add(this.gridPanel, BorderLayout.CENTER);
    }
}
