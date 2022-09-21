import javax.swing.*;
import java.awt.*;

public class Presentation extends JFrame {

    private Controller controller;

    private JSplitPane MainPanel;
    private JPanel gridPanel;
    private JPanel spellsPanel;

    public Presentation(Controller controller) {
        this.controller = controller;
        setupWindow();
    }

    private void setupWindow() {
        this.setTitle("Jewel Falls");
        this.setPreferredSize(new Dimension(1000, 800));


        setupGrid();
        setupSpellsBook();
        setupMainPanel();

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupMainPanel() {
        JSplitPane MainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        MainPanel.setLeftComponent(this.gridPanel);
        MainPanel.setRightComponent(this.spellsPanel);
        MainPanel.setDividerLocation(0.75);
        MainPanel.setDividerSize(0);

        this.MainPanel = MainPanel;
        this.add(this.MainPanel);
    }

    private void setupGrid() {
        Grid grid = this.controller.getAbstraction().getGame().getGrid();
        JPanel GridPanel = new JPanel();
        GridPanel.setLayout(new GridLayout(grid.getRows(), grid.getColumns()));

        for (int y = 0; y < grid.getColumns(); y++) {
            for (int x = 0; x < grid.getRows(); x++) {
                ImageIcon tileImage = new ImageIcon(getClass().getResource(grid.getTile(new Point(x, y)).getType().name()+".png"));
                ImageIcon tileImageScaled = new ImageIcon(tileImage.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
                GridPanel.add(new JLabel(tileImageScaled));
            }

            this.gridPanel = GridPanel;
        }
    }

    private void setupSpellsBook(){
        JPanel SpellsPanel = new JPanel();

        //TODO

        this.spellsPanel = SpellsPanel;
    }
}
