package game;
import javax.swing.*;
import java.awt.*;

import javax.sound.sampled.*;

public class GameView extends JFrame {

    private Game controller;

    private JSplitPane MainPanel;
    private JPanel gridPanel;
    private JPanel spellsPanel;
    private JLabel scoreLabel;

    private Clip clip;

    public GameView(Game controller) {
        this.controller = controller;
        setupWindow();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Eric Skiff - A Night Of Dizzy Spells.wav"));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);

            //Change the music Volume
            FloatControl gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);

            this.clip.start();
            System.out.println("Let's play Music !");
        } catch (Exception e) {System.out.println("Can't play Music");;}
    }

    public JPanel getSpellsPanel() {return spellsPanel; }
    public JLabel getScoreLabel() { return scoreLabel; }
    
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

    private void setupSpellsBook(){
        JPanel SpellsPanel = new JPanel();
        
        //TODO

        this.spellsPanel = SpellsPanel;
    }
    
    
    public void updateScoreLabel() {
    	int score = controller.getGame().getScore();
    	scoreLabel.setText("Score : " + score);
    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	// La grid se paint deja de son cote, pas besoin d'appeler GridView.paint
    	
    	g.setColor(Color.BLUE);
    	System.out.println(scoreLabel.getText());
    	Point absolutePosition = SwingUtilities.convertPoint(spellsPanel, 
    														 new Point(scoreLabel.getX(), scoreLabel.getY()),
    														 MainPanel);
    	g.drawString(scoreLabel.getText(), absolutePosition.x , absolutePosition.y);
    }
    
    
    
}
