import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private Controller controller;

    private JSplitPane MainPanel;
    private JPanel gridPanel;
    private JPanel spellsPanel;

    public View(Controller controller) {
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

    public void setupGrid() {
        this.gridPanel = new JPanel();
        this.gridPanel.add(this.controller.getGame().getGrid());
    }

    private void setupSpellsBook(){
        JPanel SpellsPanel = new JPanel();

        //TODO

        this.spellsPanel = SpellsPanel;
    }
}
