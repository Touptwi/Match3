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

    public GameView(Game controller, float volume) {
        this.controller = controller;
        setupWindow();
        try {
            //Load the music
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Eric Skiff - A Night Of Dizzy Spells.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            //Change the music Volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);

            clip.start();
            System.out.println("Let's play Music !");
        } catch (Exception e) {System.out.println("Can't play Music");}
    }

    public JPanel getSpellsPanel() {return spellsPanel; }
    public JLabel getScoreLabel() { return scoreLabel; }
    

    private void setupWindow() {
        this.setTitle("Jewels Falls");
    //    this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("Images/Jewels/Jewels Falls Icon.png")).getImage());
        this.setPreferredSize(new Dimension(1000, 800));
        //Set the windows at the center of the screen
        this.setLocationRelativeTo(null);
        this.setLocation(this.getX()-500, this.getY()-400);

        setupGrid();
        setupSpellsPanel();

        setupMainPanel();

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	private void setupScorePanel() {

		
		scoreLabel = new JLabel();
        int score = this.controller.getModel().getScore();
        scoreLabel.setText("Score : " + score);
        System.out.println("Point " + scoreLabel.getX() + ","+ scoreLabel.getY());

	}

    private void setupMainPanel() {
        JSplitPane MainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        MainPanel.setLeftComponent(this.gridPanel);
        MainPanel.setRightComponent(this.spellsPanel);
        MainPanel.setDividerLocation(0.75);
        MainPanel.setDividerSize(0);
        
        this.spellsPanel.add(this.scoreLabel, BorderLayout.CENTER);
        
        this.MainPanel = MainPanel;
        this.add(this.MainPanel);
    }

    public void setupGrid() {
        this.gridPanel = new JPanel();
        this.gridPanel.add(this.controller.getModel().getGrid());
    }

    private void setupSpellsPanel(){
        this.spellsPanel = new JPanel();
        
        this.setupScorePanel();
        
        this.spellsPanel.add(this.scoreLabel, BorderLayout.NORTH);
        this.spellsPanel.add(this.controller.getSpellManager().getView().getPanel(), BorderLayout.CENTER);
    }
    
    
    public void updateScoreLabel() {

    	int score = this.controller.getModel().getScore();
    	this.scoreLabel.setText("Score : " + score);

    }
    
    public void paint(Graphics g) {
    	super.paint(g);
    	
    	// La grid se paint deja de son cote, pas besoin d'appeler GridView.paint
    	
    	g.setColor(Color.BLUE);
    	System.out.println(scoreLabel.getText());
    	Point absolutePosition = SwingUtilities.convertPoint(spellsPanel, 
    														 new Point(scoreLabel.getX(), scoreLabel.getY()),
    														 MainPanel);
    	//g.drawString(scoreLabel.getText(), absolutePosition.x , absolutePosition.y); 
    }
}
