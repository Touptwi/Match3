package game;
import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private Game controller;

    private JSplitPane MainPanel;
    private JPanel gridPanel;
    private JPanel spellsPanel;
    private JLabel scoreLabel;

    public GameView(Game controller) {
        this.controller = controller;
        setupWindow();
    }

    private void setupWindow() {
        this.setTitle("Jewel Falls");
        this.setPreferredSize(new Dimension(1000, 800));


        setupGrid();
        setupSpellsBook();
        setupScorePanel();
        
        setupMainPanel();
        

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	private void setupScorePanel() {
		
		scoreLabel = new JLabel();
        int score = this.controller.getGame().getScore();
        scoreLabel.setText("Score : " + score);
        
        
	}

    private void setupMainPanel() {
        JSplitPane MainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        
        MainPanel.setLeftComponent(this.gridPanel);
        MainPanel.setRightComponent(this.spellsPanel);
        MainPanel.setDividerLocation(0.75);
        MainPanel.setDividerSize(0);
        
        spellsPanel.add(scoreLabel, BorderLayout.CENTER);
        
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
