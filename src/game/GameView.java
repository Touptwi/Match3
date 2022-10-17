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

    public JPanel getSpellsPanel() {return spellsPanel; }
    public JLabel getScoreLabel() { return scoreLabel; }
    
    private void setupWindow() {
        this.setTitle("Jewel Falls");
        this.setPreferredSize(new Dimension(1000, 800));


        setupGrid();
        setupSpellsPanel();
        
        setupMainPanel();
        

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	private void setupScorePanel() {
		
		scoreLabel = new JLabel();
        int score = this.controller.getGame().getScore();
        scoreLabel.setText("Score : " + score);
        System.out.println("Point " + scoreLabel.getX() + ","+ scoreLabel.getY());
        
        
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

    private void setupSpellsPanel(){
        this.spellsPanel = new JPanel();
        
        this.setupScorePanel();
        
        spellsPanel.add(scoreLabel, BorderLayout.NORTH);
        spellsPanel.add(this.controller.getSpellManager(), BorderLayout.CENTER);

        
    }
    
    
    public void updateScoreLabel() {
    	int score = controller.getGame().getScore();
    	scoreLabel.setText("Score : " + score);
    }
    
  
    /**
    public void paint(Graphics g) {
    	super.paint(g);
    	// La grid se paint deja de son cote, pas besoin d'appeler GridView.paint
    	
    /*	g.setColor(Color.BLUE);
    	System.out.println(scoreLabel.getText());
    	Point absolutePosition = SwingUtilities.convertPoint(spellsPanel, 
    														 new Point(scoreLabel.getX(), scoreLabel.getY()),
    														 MainPanel);
    	//g.drawString(scoreLabel.getText(), absolutePosition.x , absolutePosition.y); 
    }
    
	**/
    
    
}
