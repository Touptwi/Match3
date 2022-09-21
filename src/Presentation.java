import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        JPanel GridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(grid.getRows(), grid.getColumns()));

        for (int y = 0; y < grid.getColumns(); y++) {
            for (int x = 0; x < grid.getRows(); x++) {
                ImageIcon tileImage = new ImageIcon(getClass().getResource(grid.getTile(new Point(x, y)).getType().name()) + ".png");
                gridPanel.add(new JLabel(tileImage));
            }

            this.gridPanel = GridPanel;
            this.add(this.gridPanel, BorderLayout.CENTER);
        }
    }
}
